package com.coinup.exceptions;

public class ApplicationException extends RuntimeException {
	private static final long serialVersionUID = -2331548491261128848L;

	public ApplicationException() {}
	
	public ApplicationException(String message) {
		super(message);
	}
	
	public ApplicationException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public ApplicationException(Throwable throwable) {
		super(throwable);
	}
	
}
