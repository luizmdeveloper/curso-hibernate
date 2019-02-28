package com.algaworks.curso.jpa2.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="apolice_seguro")
public class ApoliceSeguro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Codigo;
	
	private BigDecimal valorFranquia;
	private Boolean protecaoTerceiro;
	private Boolean protecaoCausasNaturais;
	private Boolean protecaoRoubo;
	
	public Long getCodigo() {
		return Codigo;
	}
	
	public void setCodigo(Long codigo) {
		Codigo = codigo;
	}
	
	public BigDecimal getValorFranquia() {
		return valorFranquia;
	}
	
	public void setValorFranquia(BigDecimal valorFranquia) {
		this.valorFranquia = valorFranquia;
	}
	
	public Boolean getProtecaoTerceiro() {
		return protecaoTerceiro;
	}
	
	public void setProtecaoTerceiro(Boolean protecaoTerceiro) {
		this.protecaoTerceiro = protecaoTerceiro;
	}
	
	public Boolean getProtecaoCausasNaturais() {
		return protecaoCausasNaturais;
	}
	
	public void setProtecaoCausasNaturais(Boolean protecaoCausasNaturais) {
		this.protecaoCausasNaturais = protecaoCausasNaturais;
	}
	
	public Boolean getProtecaoRoubo() {
		return protecaoRoubo;
	}
	
	public void setProtecaoRoubo(Boolean protecaoRoubo) {
		this.protecaoRoubo = protecaoRoubo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Codigo == null) ? 0 : Codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApoliceSeguro other = (ApoliceSeguro) obj;
		if (Codigo == null) {
			if (other.Codigo != null)
				return false;
		} else if (!Codigo.equals(other.Codigo))
			return false;
		return true;
	}
}
