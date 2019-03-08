package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.FabricanteDAO;
import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.modelolazy.LazyFabricanteDataModel;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaFabricanteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Fabricante> fabricantes = new ArrayList<Fabricante>();
	private Fabricante fabricanteSelecionado;
	private LazyFabricanteDataModel lazyFabricantes;
	
	@Inject
	private FabricanteDAO fabricanteDAO;
	
	@PostConstruct
	public void init() {
		lazyFabricantes = new LazyFabricanteDataModel(fabricanteDAO);
	}

	public void excluir() {
		try {
			fabricanteDAO.excluir(fabricanteSelecionado);
			this.fabricantes.remove(fabricanteSelecionado);
			FacesUtil.addSuccessMessage("Fabricante " + fabricanteSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	
	public Fabricante getFabricanteSelecionado() {
		return fabricanteSelecionado;
	}
	
	public void setFabricanteSelecionado(Fabricante fabricanteSelecionado) {
		this.fabricanteSelecionado = fabricanteSelecionado;
	}

	public LazyFabricanteDataModel getLazyFabricantes() {
		return lazyFabricantes;
	}
}
