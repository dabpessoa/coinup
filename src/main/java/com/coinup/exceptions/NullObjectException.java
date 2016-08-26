package com.coinup.exceptions;

public class NullObjectException extends ApplicationException {
	private static final long serialVersionUID = 6327349409788249894L;

	public NullObjectException() {}
	
	public NullObjectException(String message) {
		super(message);
	}
	
	public NullObjectException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
