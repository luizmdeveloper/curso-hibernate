package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.AluguelDAO;
import com.algaworks.curso.jpa2.dao.ModeloDAO;
import com.algaworks.curso.jpa2.modelo.Aluguel;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.modelo.Modelo;

@Named
@ViewScoped
public class PesquisaAluguelBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Aluguel aluguel;
	private Carro carro;
	private List<Modelo> modelos;	
	private List<Aluguel> alugueis;
	
	@Inject
	private ModeloDAO modeloDAO;
	
	@Inject
	private AluguelDAO aluguelDAO;
	
	@PostConstruct
	public void init() {
		this.aluguel = new Aluguel();
		this.carro = new Carro();
		this.carro.setModelo(new Modelo());
		this.alugueis = new ArrayList<Aluguel>();
		this.modelos = modeloDAO.buscarTodos();
	}
	
	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public void pesquisar() {
		this.alugueis = aluguelDAO.buscarPorDataEntregaEModeloCarro(this.aluguel.getDataEntrega(), this.carro.getModelo());
	}
	
	public void pesquisarCriteria() {
		this.alugueis = aluguelDAO.buscarPorDataEntregaEModeloCarroCriteria(this.aluguel.getDataEntrega(), this.carro.getModelo());
	}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public List<Aluguel> getAlugueis() {
		return alugueis;
	}
}
