package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.AcessorioDAO;
import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaAcessorioBean implements Serializable {

	private static final long serialVersionUID = 1L;	
	private List<Acessorio> acessorios = new ArrayList<>();
	private Acessorio acessorioSelecionado;
	
	@Inject
	private AcessorioDAO acessorioDAO;
	
	@PostConstruct
	public void init() {
		this.acessorios = acessorioDAO.buscarTodos();
	}
	
	public void excluir() {
		try {
			acessorioDAO.excluir(acessorioSelecionado.getCodigo());
			this.acessorios.remove(acessorioSelecionado);
			FacesUtil.addSuccessMessage("Acessório " + acessorioSelecionado.getDescricao() + " excluído com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Acessorio getAcessorioSelecionado() {
		return acessorioSelecionado;
	}

	public void setAcessorioSelecionado(Acessorio acessorioSelecionado) {
		this.acessorioSelecionado = acessorioSelecionado;
	}

	public List<Acessorio> getAcessorios() {
		return acessorios;
	}
}
