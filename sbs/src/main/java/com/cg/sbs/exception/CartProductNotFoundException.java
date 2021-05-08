package com.cg.sbs.exception;

public class CartProductNotFoundException extends RuntimeException{
	public CartProductNotFoundException(String msg) {
		super(msg);
	}
}