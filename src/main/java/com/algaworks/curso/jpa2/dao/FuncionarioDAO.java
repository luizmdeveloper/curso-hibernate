package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.curso.jpa2.modelo.Funcionario;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class FuncionarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	
	public void salvar(Funcionario funcionario) {
		entityManager.merge(funcionario);
	}

	public Funcionario buscarPorCodigo(Long codigo) {
		return entityManager.find(Funcionario.class, codigo);
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> buscarTodos(){
		return entityManager.createQuery("FROM Funcionario").getResultList();
	}
	
	@Transactional
	public void excluir(Long codigo) throws NegocioException {
		Funcionario funcionarioTemp = buscarPorCodigo(codigo);
		try {
			entityManager.remove(funcionarioTemp);
			entityManager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Funcionário não é possível excluir!");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> buscarTodosPaginacao(int first, int pageSize) {
		return entityManager.createQuery("FROM Funcionario f")
				.setFirstResult(first)
				.setMaxResults(pageSize)
				.getResultList();
	}

	public Long buscarTotalRegistro() {
		return entityManager.createQuery("SELECT COUNT(f) FROM Funcionario f", Long.class).getSingleResult();
	}

}
