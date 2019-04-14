package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.CarroDAO;
import com.algaworks.curso.jpa2.modelo.Carro;

@Named
@ViewScoped
public class PesquisaCarroPorFabricanteBean implements Serializable {

	private static final long serialVersionUID = 1L;	
	private List<Carro> carros = new ArrayList<Carro>();
	private String nomeFabricante = "";
	
	@Inject
	private CarroDAO carroDAO;
	
	public void buscar() {
		this.carros = this.carroDAO.buscarPorNomeFabricante(this.nomeFabricante);
	}

	public String getNomeFabricante() {
		return nomeFabricante;
	}

	public void setNomeFabricante(String nomeFabricante) {
		this.nomeFabricante = nomeFabricante;
	}

	public List<Carro> getCarros() {
		return carros;
	}
}
