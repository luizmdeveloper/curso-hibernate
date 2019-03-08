package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class AcessorioDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	
	public void salvar(Acessorio acessorio) {
		entityManager.merge(acessorio);
	}
	
	@SuppressWarnings("unchecked")
	public List<Acessorio> buscarTodos(){
		return entityManager.createQuery("from Acessorio").getResultList();
	}
	
	@Transactional
	public void excluir(Long codigo) throws NegocioException {
		Acessorio acessorioTmp = buscarPorCodigo(codigo);
		
		try {
			entityManager.remove(acessorioTmp);
			entityManager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Não foi possível excluir acessorio!");
		}
	}
	
	public Acessorio buscarPorCodigo(Long codigo) {
		return entityManager.find(Acessorio.class, codigo);
	}

	@SuppressWarnings("unchecked")
	public List<Acessorio> buscarTodosPaginacao(int first, int pageSize) {
		return entityManager.createQuery("FROM Acessorio a")
				.setFirstResult(first)
				.setMaxResults(pageSize)
				.getResultList();
	}

	public Long buscarTotalRegistro() {
		return entityManager.createQuery("SELECT COUNT(a) FROM Acessorio a", Long.class).getSingleResult();
	}

}
