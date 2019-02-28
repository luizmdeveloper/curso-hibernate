package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.service.AcessorioService;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class AcessorioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AcessorioService acessorioService;
	
	private Acessorio acessorio;
	
	@PostConstruct
	public void init() {
		limpar();
	}
	
	public void limpar() {
		this.acessorio = new Acessorio();
	}
	
	public void salvar() {
		try {
			this.acessorioService.salvar(acessorio);
			FacesUtil.addSuccessMessage("Acessório salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
		limpar();
	}

	public Acessorio getAcessorio() {
		return acessorio;
	}

	public void setAcessorio(Acessorio acessorio) {
		this.acessorio = acessorio;
	}

}
