package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.ModeloDAO;
import com.algaworks.curso.jpa2.modelo.Modelo;
import com.algaworks.curso.jpa2.modelolazy.LazyModeloDataModel;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaModeloBean implements Serializable {

	private static final long serialVersionUID = 1L;	
	private List<Modelo> modelos = new ArrayList<>();
	private LazyModeloDataModel lazyModelos;
	
	private Modelo modeloSelecionado;
	
	@Inject
	private ModeloDAO modeloDAO;
	
	@PostConstruct
	public void init() {
		lazyModelos = new LazyModeloDataModel(modeloDAO);
	}
	
	public void excluir() {
		try {
			modeloDAO.excluir(modeloSelecionado.getCodigo());
			this.modelos.remove(modeloSelecionado);
			FacesUtil.addSuccessMessage("Modelo " + modeloSelecionado.getDescricao() +" excluído com sucesso");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Modelo getModeloSelecionado() {
		return modeloSelecionado;
	}

	public void setModeloSelecionado(Modelo modeloSelecionado) {
		this.modeloSelecionado = modeloSelecionado;
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public LazyModeloDataModel getLazyModelos() {
		return lazyModelos;
	}
}
