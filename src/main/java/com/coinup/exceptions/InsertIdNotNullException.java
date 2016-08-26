package com.coinup.exceptions;

public class InsertIdNotNullException extends ApplicationException {
	private static final long serialVersionUID = -7667496593491348636L;

	public InsertIdNotNullException() {}
	
	public InsertIdNotNullException(String message) {
		super(message);
	}
	
	public InsertIdNotNullException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
