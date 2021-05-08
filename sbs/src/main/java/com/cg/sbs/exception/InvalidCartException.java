package com.cg.sbs.exception;

public class InvalidCartException extends RuntimeException {
	public InvalidCartException(String msg)
	{
		super(msg);
	}
}
