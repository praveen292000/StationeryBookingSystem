package com.cg.sbs.exception;

public class OrderDoesNotExistException extends RuntimeException {

	public OrderDoesNotExistException(String message) {
		super(message);
	}

}
