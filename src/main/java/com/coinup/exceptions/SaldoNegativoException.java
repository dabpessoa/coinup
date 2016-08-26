package com.coinup.exceptions;

public class SaldoNegativoException extends ApplicationException {
	private static final long serialVersionUID = -2175510626543043124L;

	public SaldoNegativoException() {}
	
	public SaldoNegativoException(String message) {
		super(message);
	}
	
	public SaldoNegativoException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
