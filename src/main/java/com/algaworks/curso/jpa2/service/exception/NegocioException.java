package com.algaworks.curso.jpa2.service.exception;

public class NegocioException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NegocioException(String mensagem) {
		super(mensagem);
	}

}
