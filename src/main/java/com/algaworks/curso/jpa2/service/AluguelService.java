package com.algaworks.curso.jpa2.service;

import java.io.Serializable;
import java.util.Calendar;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.AluguelDAO;
import com.algaworks.curso.jpa2.modelo.Aluguel;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class AluguelService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AluguelDAO aluguelDAO;
	
	@Transactional
	public void salvar(Aluguel aluguel) throws NegocioException {
		
		if (aluguel.getCarro() == null) {
			throw new NegocioException("Carro não selecionado");
		}
		
		aluguel.setDataPedido(Calendar.getInstance());
		
		aluguelDAO.salvar(aluguel);
	}

}
