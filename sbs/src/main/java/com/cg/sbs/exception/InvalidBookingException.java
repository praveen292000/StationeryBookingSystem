package com.cg.sbs.exception;

public class InvalidBookingException extends RuntimeException {
	public InvalidBookingException(String message) {
		super(message);
	}
}