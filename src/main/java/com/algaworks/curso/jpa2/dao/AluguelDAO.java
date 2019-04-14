package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.algaworks.curso.jpa2.modelo.Aluguel;
import com.algaworks.curso.jpa2.modelo.Modelo;

public class AluguelDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	
	public void salvar(Aluguel aluguel) {
		entityManager.merge(aluguel);
	}

	public List<Aluguel> buscarPorDataEntregaEModeloCarro(Date dataEntrega, Modelo modelo) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Aluguel> criteria = builder.createQuery(Aluguel.class);
		Root<Aluguel> a = criteria.from(Aluguel.class);
		criteria.select(a);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (dataEntrega != null) {
			ParameterExpression<Date> dataEntregaInicial = builder.parameter(Date.class, "dataEntregaInicial");
			ParameterExpression<Date> dataEntregaFinal = builder.parameter(Date.class, "dataEntregaFinal");
			
			predicates.add(builder.between(a.<Date>get("dataEntrega"), dataEntregaInicial, dataEntregaFinal));
		}
		
		if (modelo != null) {
			ParameterExpression<Modelo> modeloParameterExpression = builder.parameter(Modelo.class, "modelo");
			predicates.add(builder.equal(a.<Modelo>get("carro").get("modelo"), modeloParameterExpression));
		}
		
		criteria.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Aluguel> query = entityManager.createQuery(criteria);
		
		if (dataEntrega != null) {
			query.setParameter("dataEntregaInicial", gerarDataEntregaInicial(dataEntrega));
			query.setParameter("dataEntregaFinal", gerarDataEntregaFinal(dataEntrega));
		}
		
		if (modelo != null) {
			query.setParameter("modelo", modelo);
		}
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Aluguel> buscarPorDataEntregaEModeloCarroCriteria(Date dataEntrega, Modelo modelo) {
		Session session = this.entityManager.unwrap(Session.class);		
		Criteria criteria = session.createCriteria(Aluguel.class);
		
		if (dataEntrega != null) {
			criteria.add(Restrictions.between("dataEntrega", gerarDataEntregaInicial(dataEntrega), gerarDataEntregaFinal(dataEntrega)));
		}
		
		if (modelo != null) {
			criteria.createAlias("carro", "c");
			criteria.add(Restrictions.eq("c.modelo", modelo));
		}
		
		return criteria.list();
	}
	
	private Date gerarDataEntregaInicial(Date dataEntrega) {
		Calendar dataEntregaInicial = Calendar.getInstance();
		dataEntregaInicial.setTime(dataEntrega);
		dataEntregaInicial.set(Calendar.HOUR_OF_DAY, 0);
		dataEntregaInicial.set(Calendar.MINUTE, 0);
		dataEntregaInicial.set(Calendar.SECOND, 0);			

		return dataEntregaInicial.getTime();
	}
	
	private Date gerarDataEntregaFinal(Date dataEntrega) {
		Calendar dataEntregaFinal = Calendar.getInstance();
		dataEntregaFinal.setTime(dataEntrega);
		dataEntregaFinal.set(Calendar.HOUR_OF_DAY, 23);
		dataEntregaFinal.set(Calendar.MINUTE, 59);
		dataEntregaFinal.set(Calendar.SECOND, 59);
		
		return dataEntregaFinal.getTime();
	}
}
