package com.cg.sbs.controller;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.sbs.exception.AddBookingException;
import com.cg.sbs.exception.OrderDoesNotExistException;
import com.cg.sbs.exception.CartProductNotFoundException;
import com.cg.sbs.exception.CustomerNotFoundException;
import com.cg.sbs.exception.InvalidOrderException;
import com.cg.sbs.exception.InvalidCartException;
import com.cg.sbs.exception.InvalidCustomerException;
import com.cg.sbs.exception.InvalidProductException;
import com.cg.sbs.exception.ProductNotFoundException;
import com.cg.sbs.exception.BookingNotFoundException;
import com.cg.sbs.exception.InvalidBookingException;


@RestControllerAdvice
public class CentralizeExceptionHandler {


	/**
	 * Catching Exception when order is invalid
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidOrderException.class)
	public String invalidOrderException(InvalidOrderException e) {
		return e.getMessage();
	}


	/**
	 *Catching Exception when cart is invalid
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidCartException.class)
	public String invalidCartException(InvalidCartException e) {
		return e.getMessage();
	}

	/**
	 *Catching Exception when customer is invalid
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidCustomerException.class)
	public String invalidCustomerException(InvalidCustomerException e) {
		return e.getMessage();
	}

	
	/**
	 *Catching Exception when product is invalid
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidProductException.class)
	public String invalidProductException(InvalidProductException e) {
		return e.getMessage();
	}

	/**
	 *Catching Exception when booking is invalid
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidBookingException.class)
	public String invalidBookingException(InvalidBookingException e) {
		return e.getMessage();
	}

	/**
	 *Catching Exception while creating a booking when the cart is null.	 
	 **/
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(AddBookingException.class)
	public String addBookingException(AddBookingException e) {
		return e.getMessage();
	}

	
	/**
	 *Catching Exception when the order does not exist.
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(OrderDoesNotExistException.class)
	public String orderDoesNotExistException(OrderDoesNotExistException e) {
		return e.getMessage();
	}
	
	/**
	 *Catching Exception when customer is not found
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(CustomerNotFoundException.class)
	public String customerNotFoundException(CustomerNotFoundException e) {
		return e.getMessage();
	}

	
	/**
	 *Catching Exception when product is not found
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ProductNotFoundException.class)
	public String productNotFoundException(ProductNotFoundException e) {
		return e.getMessage();
	}

	/**
	 *Catching Exception when booking not found
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(BookingNotFoundException.class)
	public String bookingNotFoundException(BookingNotFoundException e) {
		return e.getMessage();
	}
	
	/**
	 *Catching Exception when cart Product is not found
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(CartProductNotFoundException.class)
	public String cartProductNotFoundException(CartProductNotFoundException e) {
		return e.getMessage();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public String methodArgumentNotValidException(MethodArgumentNotValidException e) {
		return e.getBindingResult().getFieldError().getDefaultMessage();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public String constraintViolationException(ConstraintViolationException e) {
		return e.getMessage();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public String basicException(Exception e) {
		return e.getMessage();
	}
}