package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.modelo.Motorista;
import com.algaworks.curso.jpa2.modelo.Sexo;
import com.algaworks.curso.jpa2.service.MotoristaService;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class MotoristaBean implements Serializable {

	private static final long serialVersionUID = 1L;	
	private List<Sexo> sexos;
	private Motorista motorista;
	
	@Inject
	private MotoristaService motoristaService;
	
	@PostConstruct
	public void init() {
		this.limpar();
		this.sexos = Arrays.asList(Sexo.values());
	}
	
	public void limpar() {
		this.motorista = new Motorista();
	}
	
	public void salvar() {
		try {
			motoristaService.salvar(motorista);
			FacesUtil.addSuccessMessage("Motorista cadastrado com sucesso");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
		this.limpar();
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public List<Sexo> getSexos() {
		return sexos;
	}
}
