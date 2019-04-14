package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.CarroDAO;
import com.algaworks.curso.jpa2.modelo.util.TotalAlugueisPorCarro;

@Named
@ViewScoped
public class RelatorioTotaisAlugueisPorCarro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<TotalAlugueisPorCarro> totaisAlugueiPorCarro = new ArrayList<TotalAlugueisPorCarro>();
	
	@Inject
	private CarroDAO carroDAO;
	
	public void buscar() {
		this.totaisAlugueiPorCarro = this.carroDAO.buscarTotalDeAlugueisPorCarro();
	}

	public List<TotalAlugueisPorCarro> getTotaisAlugueiPorCarro() {
		return totaisAlugueiPorCarro;
	}
}
