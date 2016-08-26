package com.coinup.exceptions;

public class CaixaAbertoNoPeriodoException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public CaixaAbertoNoPeriodoException() {}
	
	public CaixaAbertoNoPeriodoException(String message) {
		super(message);
	}
	
	public CaixaAbertoNoPeriodoException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
}
