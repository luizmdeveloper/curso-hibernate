package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class FabricanteDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	
	public void salvar(Fabricante fabricante) {
		entityManager.merge(fabricante);
	}

	@SuppressWarnings("unchecked")
	public List<Fabricante> buscarTodos() {
		return entityManager.createQuery("from Fabricante").getResultList();		
	}

	@Transactional
	public void excluir(Fabricante fabricanteSelecionado) throws NegocioException {
		Fabricante fabricanteTemp = buscarPorCodigo(fabricanteSelecionado.getCodigo());
		
		try {
			entityManager.remove(fabricanteTemp);
			entityManager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Não foi possível excluir o fabricante!");
		}
	}

	public Fabricante buscarPorCodigo(Long codigo) {
		return entityManager.find(Fabricante.class, codigo);
	}

}
