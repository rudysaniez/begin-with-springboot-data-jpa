package com.me.work.api.jpa.exception;

public class AlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AlreadyExistException() {
		super();
	}
	
	public AlreadyExistException(String message) {
		super(message);
	}
	
	public AlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public AlreadyExistException(Throwable cause) {
		super(cause);
	}
}
