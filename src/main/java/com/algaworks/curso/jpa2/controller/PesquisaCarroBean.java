package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.CarroDAO;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Carro carroSelecionado;
	private List<Carro> carros;
	
	@Inject
	private CarroDAO carroDAO;
	
	@PostConstruct
	public void init() {
		carros = carroDAO.buscarTodos();
	}
	
	public void excluir() {
		try {
			carroDAO.excluir(carroSelecionado.getCodigo());
			this.carros.remove(carroSelecionado);
			FacesUtil.addSuccessMessage("Carro excluído com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void buscarAcessoriosDoCarro() {
		carroSelecionado = carroDAO.buscarAcessorioDoCarro(carroSelecionado.getCodigo());
	}

	public Carro getCarroSelecionado() {
		return carroSelecionado;
	}

	public void setCarroSelecionado(Carro carroSelecionado) {
		this.carroSelecionado = carroSelecionado;
	}

	public List<Carro> getCarros() {
		return carros;
	}

}
