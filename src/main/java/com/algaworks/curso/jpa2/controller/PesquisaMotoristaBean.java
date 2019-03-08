package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.MotoristaDAO;
import com.algaworks.curso.jpa2.modelo.Motorista;
import com.algaworks.curso.jpa2.modelolazy.LazyMotoristaDataModel;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaMotoristaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Motorista> motoristas;
	private Motorista motoristaSelecionado;
	private LazyMotoristaDataModel lazyMotoristas;
	
	@Inject
	private MotoristaDAO motoristaDAO;
	
	@PostConstruct
	public void init() {
		lazyMotoristas = new LazyMotoristaDataModel(motoristaDAO);
	}
	
	public void excluir() {
		try {
			motoristaDAO.excluir(motoristaSelecionado.getCodigo());
			this.motoristas.remove(motoristaSelecionado);
			FacesUtil.addSuccessMessage("Motorista " + motoristaSelecionado.getNome() + " excluído com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Motorista getMotoristaSelecionado() {
		return motoristaSelecionado;
	}

	public void setMotoristaSelecionado(Motorista motoristaSelecionado) {
		this.motoristaSelecionado = motoristaSelecionado;
	}

	public List<Motorista> getMotoristas() {
		return motoristas;
	}

	public LazyMotoristaDataModel getLazyMotoristas() {
		return lazyMotoristas;
	}
}
