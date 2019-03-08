package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.curso.jpa2.modelo.Carro;
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

}
