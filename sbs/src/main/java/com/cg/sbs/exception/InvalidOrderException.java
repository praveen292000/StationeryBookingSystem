package com.cg.sbs.exception;

public class InvalidOrderException extends RuntimeException {
	public InvalidOrderException(String msg) {
		super(msg);
	}

}