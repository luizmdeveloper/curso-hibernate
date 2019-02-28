package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.CarroDAO;
import com.algaworks.curso.jpa2.dao.MotoristaDAO;
import com.algaworks.curso.jpa2.modelo.Aluguel;
import com.algaworks.curso.jpa2.modelo.ApoliceSeguro;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.modelo.Motorista;
import com.algaworks.curso.jpa2.service.AluguelService;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class AluguelBean implements Serializable {

	private static final long serialVersionUID = 1L;	
	private List<Motorista> motoristas;
	private List<Carro> carros;
	private Aluguel aluguel;
	
	@Inject
	private AluguelService aluguelService;
	
	@Inject
	private CarroDAO carroDAO;
	
	@Inject
	private MotoristaDAO motoristaDAO;
	
	@PostConstruct
	public void init() {
		this.limpar();	
		
		this.carros = carroDAO.buscarTodos();
		this.motoristas = motoristaDAO.buscarTodos();
	}
	
	public void limpar() {
		this.aluguel = new Aluguel();
		this.aluguel.setApoliceSeguro(new ApoliceSeguro());
	}
	
	public void salvar(){
		try {
			this.aluguelService.salvar(aluguel);
			FacesUtil.addSuccessMessage("Aluguel salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
		this.limpar();
	}
	
	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}
	
	public List<Carro> getCarros() {
		return carros;
	}

	public List<Motorista> getMotoristas() {
		return motoristas;
	}	
}
