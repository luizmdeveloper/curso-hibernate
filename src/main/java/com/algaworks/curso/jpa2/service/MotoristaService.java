package com.algaworks.curso.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.MotoristaDAO;
import com.algaworks.curso.jpa2.modelo.Motorista;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class MotoristaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private MotoristaDAO motoristaDAO;
	
	@Transactional
	public void salvar(Motorista motorista) throws NegocioException {
		motoristaDAO.salvar(motorista);
	}

}
