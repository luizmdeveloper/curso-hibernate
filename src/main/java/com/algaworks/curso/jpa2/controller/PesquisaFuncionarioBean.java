package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.FuncionarioDAO;
import com.algaworks.curso.jpa2.modelo.Funcionario;
import com.algaworks.curso.jpa2.service.exception.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaFuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Funcionario> funcionarios;
	private Funcionario funcionarioSelecionado;
	
	@Inject
	private FuncionarioDAO funcionarioDAO;
	
	@PostConstruct
	public void init() {
		this.funcionarios = funcionarioDAO.buscarTodos();
	}
	
	public void excluir() {
		try {
			funcionarioDAO.excluir(funcionarioSelecionado.getCodigo());
			this.funcionarios.remove(funcionarioSelecionado);
			FacesUtil.addSuccessMessage("Funcionário " + funcionarioSelecionado.getNome() + " excluído com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
}
