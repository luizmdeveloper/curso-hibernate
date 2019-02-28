package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.modelo.Funcionario;
import com.algaworks.curso.jpa2.modelo.Sexo;
import com.algaworks.curso.jpa2.service.FuncionarioService;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class FuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Funcionario funcionario;
	private List<Sexo> sexos;
	
	@Inject
	private FuncionarioService funcionarioService;
	
	@PostConstruct
	public void init() {
		this.limpar();
		this.sexos = Arrays.asList(Sexo.values());
	}
	
	public void salvar() {
		try {
			funcionarioService.salvar(funcionario);
			FacesUtil.addSuccessMessage("Funcionario salvo com sucesso");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		this.limpar();
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Sexo> getSexos() {
		return sexos;
	}

	private void limpar() {
		this.funcionario = new Funcionario();
	}

}