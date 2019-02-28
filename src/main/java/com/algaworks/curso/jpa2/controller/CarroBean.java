package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.AcessorioDAO;
import com.algaworks.curso.jpa2.dao.ModeloDAO;
import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.modelo.Modelo;
import com.algaworks.curso.jpa2.service.CarroService;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CarroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Carro carro;
	private List<Modelo> modelos;
	private List<Acessorio> acessorios;
	
	@Inject
	private CarroService carroService;
	
	@Inject
	private AcessorioDAO acessorioDAO;
	
	@Inject
	private ModeloDAO modeloDAO;
	
	@PostConstruct
	public void init() {
		this.limpar();

		this.modelos = modeloDAO.buscarTodos();
		this.acessorios = acessorioDAO.buscarTodos();
	}
	
	public void limpar(){
		this.carro = new Carro();
		this.carro.setAcessorios(new ArrayList<Acessorio>());
	}	
	
	public void salvar() {
		try {
			this.carroService.salvar(carro);
			FacesUtil.addSuccessMessage("Carro salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro desconhecido. Contatar o administrador");
		}
		this.limpar();
	}
	
	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public List<Acessorio> getAcessorios() {
		return acessorios;
	}
}
