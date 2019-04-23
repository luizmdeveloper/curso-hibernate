package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.algaworks.curso.jpa2.modelo.Aluguel;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.modelo.util.TotalAlugueisPorCarro;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class CarroDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	
	public void salvar(Carro carro) {
		entityManager.merge(carro);
	}
	
	@SuppressWarnings("unchecked")
	public List<Carro> buscarTodos(){
		return entityManager.createQuery("from Carro").getResultList();
	}
	
	public Carro bucarPorCodigo(Long codigo) {
		return entityManager.find(Carro.class, codigo);
	}
	
	public Carro buscarAcessorioDoCarro(Long codigo) {
		return (Carro) entityManager.createQuery("SELECT c FROM Carro c JOIN c.acessorios a WHERE c.codigo = ?")
				.setParameter(1, codigo)
				.getSingleResult();
	}
	
	@Transactional	
	public void excluir(Long codigo) throws NegocioException {
		try {
			Carro carroTmp = bucarPorCodigo(codigo);
			entityManager.remove(carroTmp);
			entityManager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Não é possível excluir esse carro!");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Carro> buscarTodosPaginacao(int first, int pageSize) {
		return entityManager.createNamedQuery("Carro.buscarTodos")
					.setFirstResult(first)
					.setMaxResults(pageSize)
					.getResultList();
	}

	public Long buscarTotalRegistro() {
		return entityManager.createQuery("SELECT COUNT(c) FROM Carro c", Long.class).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Carro> buscarCarrosNuncaAlugados() {
		Session session = this.entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Carro.class);
		
		DetachedCriteria criteriaAlugueis = DetachedCriteria.forClass(Aluguel.class);
		criteriaAlugueis.setProjection(Property.forName("carro"));
		criteriaAlugueis.add(Restrictions.isNotNull("carro"));
		
		criteria.add(Property.forName("codigo").notIn(criteriaAlugueis));
		
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<TotalAlugueisPorCarro> buscarTotalDeAlugueisPorCarro() {
		Session session = this.entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Carro.class);
		criteria.createAlias("alugueis", "a");
		
		ProjectionList projectionList = Projections.projectionList()
											.add(Projections.groupProperty("placa").as("placa"))
											.add(Projections.count("a.codigo").as("quantidadeTotalAlugueis"));
		criteria.setProjection(projectionList);
		criteria.addOrder(Order.desc("quantidadeTotalAlugueis"));
		criteria.setResultTransformer(Transformers.aliasToBean(TotalAlugueisPorCarro.class));
				
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Carro> buscarPorNomeFabricante(String nomeFabricante) {
		Session session = this.entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Carro.class);		
		criteria.createAlias("modelo", "m");
		criteria.createAlias("m.fabricante", "f");
		
		if (nomeFabricante != null && nomeFabricante.equals("")) {
			criteria.add(Restrictions.ilike("f.nome", nomeFabricante, MatchMode.ANYWHERE));			
		}
		
		return criteria.list();
	}
}