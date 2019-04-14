package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.AluguelDAO;
import com.algaworks.curso.jpa2.modelo.Mes;

@Named
@ViewScoped
public class RelatorioAluguelMesBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Mes[] meses;
	private Mes mesSelecionado;
	private BigDecimal totalDoMes;
	
	@Inject
	private AluguelDAO aluguelDAO;
	
	@PostConstruct
	public void init() {
		this.meses = Mes.values(); 
	}
	
	public void buscarTotalAlugueisNoMes() {
		System.out.println(">>>>> mes selecionado: " + this.mesSelecionado.getMes());
		this.totalDoMes = this.aluguelDAO.buscarTotdalAlugueisMesDe(this.mesSelecionado.getMes());
	}
	
	public Mes getMesSelecionado() {
		return mesSelecionado;
	}
	
	public void setMesSelecionado(Mes mesSelecionado) {
		this.mesSelecionado = mesSelecionado;
	}
	
	public Mes[] getMeses() {
		return meses;
	}

	public BigDecimal getTotalDoMes() {
		return totalDoMes;
	}
}
