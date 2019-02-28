package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.curso.jpa2.modelo.Modelo;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;
public class ModeloDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	
	public void salvar(Modelo modelo) {
		entityManager.merge(modelo);
	}
	
	public Modelo buscarPorCodigo(Long codigo) {
		return entityManager.find(Modelo.class, codigo);
	}
	
	@SuppressWarnings("unchecked")
	public List<Modelo> buscarTodos() {
		return entityManager.createQuery("from Modelo").getResultList();
	}
	
	@Transactional
	public void excluir(Long codigo) throws NegocioException{
		Modelo modeloTmp = buscarPorCodigo(codigo);
		try {
			entityManager.remove(modeloTmp);
			entityManager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Modelo de carro não pode ser excluído!");
		}
	}	

}
