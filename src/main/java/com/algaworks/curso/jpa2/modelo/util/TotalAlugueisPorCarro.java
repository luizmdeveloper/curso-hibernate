package com.algaworks.curso.jpa2.modelo.util;

import java.io.Serializable;

public class TotalAlugueisPorCarro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String placa;
	private Long quantidadeTotalAlugueis;
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public Long getQuantidadeTotalAlugueis() {
		return quantidadeTotalAlugueis;
	}
	
	public void setQuantidadeTotalAlugueis(Long quantidadeTotalAlugueis) {
		this.quantidadeTotalAlugueis = quantidadeTotalAlugueis;
	}
}
