package com.ksjimen.autos.exception;

public class InternalLoginException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InternalLoginException(String responseException) {
		super(responseException);
	}

}
