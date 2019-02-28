package com.algaworks.curso.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.FabricanteDAO;
import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class FabricanteService implements Serializable {

	@Inject
	private FabricanteDAO fabricanteDAO;

	private static final long serialVersionUID = 1L;
	
	@Transactional
	public void salvar(Fabricante fabricante) throws NegocioException {
		
		if (fabricante.getNome() == null || fabricante.getNome().trim().equals("")) {
			throw new NegocioException("Nome do fabricante é obrigatório!");
		}
		
		this.fabricanteDAO.salvar(fabricante);
	}

}
