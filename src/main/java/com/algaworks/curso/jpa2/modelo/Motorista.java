package com.algaworks.curso.jpa2.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("motorista")
public class Motorista extends Pessoa {

	@Column(name="numero_cnh")
	private String numeroCNH;
	
	@OneToMany(mappedBy="motorista")
	private List<Aluguel> alugueis;

	public String getNumeroCNH() {
		return numeroCNH;
	}

	public void setNumeroCNH(String numeroCNH) {
		this.numeroCNH = numeroCNH;
	}

	public List<Aluguel> getAlugueis() {
		return alugueis;
	}

	public void setAlugueis(List<Aluguel> alugueis) {
		this.alugueis = alugueis;
	}
	
	
}
