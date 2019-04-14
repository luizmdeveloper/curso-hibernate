package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.CarroDAO;
import com.algaworks.curso.jpa2.modelo.Carro;

@Named
@ViewScoped
public class PesquisaCarroNaoAlugadosBean implements Serializable {

	private static final long serialVersionUID = 1L;	
	private List<Carro> carros;
	
	@Inject
	private CarroDAO carroDAO;
	
	@PostConstruct
	public void init() {
		this.carros = new ArrayList<Carro>(); 
	}
	
	public void buscar() {
		this.carros = carroDAO.buscarCarrosNuncaAlugados();
	}

	public List<Carro> getCarros() {
		return carros;
	}
}
