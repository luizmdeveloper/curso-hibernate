package com.algaworks.curso.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.FuncionarioDAO;
import com.algaworks.curso.jpa2.modelo.Funcionario;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class FuncionarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FuncionarioDAO funcionarioDAO;
	
	@Transactional
	public void salvar(Funcionario funcionario) throws NegocioException {		
		funcionarioDAO.salvar(funcionario);
	}
}
