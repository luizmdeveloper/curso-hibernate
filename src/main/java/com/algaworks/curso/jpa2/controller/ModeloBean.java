package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.FabricanteDAO;
import com.algaworks.curso.jpa2.modelo.Categoria;
import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.modelo.Modelo;
import com.algaworks.curso.jpa2.service.ModeloService;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ModeloBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Modelo modelo; 
	private List<Fabricante> fabricantes = new ArrayList<Fabricante>();
	private List<Categoria> categorias;
		
	@Inject
	private ModeloService modeloService;
	
	@Inject
	private FabricanteDAO fabricanteDAO;


	@PostConstruct
	public void init() {
		this.limpar();
		this.fabricantes = this.fabricanteDAO.buscarTodos();
		this.categorias = Arrays.asList(Categoria.values());
	}
	
	public void limpar() {
		this.modelo = new Modelo();
	}
	
	public void salvar() {
		try {		
			this.modeloService.salvar(modelo);
			FacesUtil.addSuccessMessage("Modelo salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
		this.limpar();
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}
}
