package com.algaworks.curso.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.ModeloDAO;
import com.algaworks.curso.jpa2.modelo.Modelo;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class ModeloService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ModeloDAO modeloDAO;
	
	@Transactional
	public void salvar(Modelo modelo) throws NegocioException {
		
		if (modelo.getDescricao() == null || modelo.getDescricao().trim().equals("")) {
			throw new NegocioException("Descrição é obrigatório");
		}
		
		if (modelo.getFabricante() == null) {
			throw new NegocioException("Fabricante é obrigatório");
		}
		
		modeloDAO.salvar(modelo);
	}
}
