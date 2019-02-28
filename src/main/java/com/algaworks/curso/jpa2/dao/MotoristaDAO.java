package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.curso.jpa2.modelo.Motorista;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class MotoristaDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	
	public void salvar(Motorista motorista) {
		entityManager.merge(motorista);
	}
	
	public Motorista buscarPorCodigo(Long codigo){
		return entityManager.find(Motorista.class, codigo);
	}
	
	@SuppressWarnings("unchecked")
	public List<Motorista> buscarTodos(){
		return entityManager.createQuery("FROM Motorista").getResultList();
	}
	
	@Transactional	
	public void excluir(Long codigo) throws NegocioException {
		Motorista motoristaTemp = buscarPorCodigo(codigo);
		try {
			entityManager.remove(motoristaTemp);
			entityManager.flush();			
		} catch (PersistenceException e) {
			throw new NegocioException("Motorista não pode ser excluído!");
		}
	}

}
