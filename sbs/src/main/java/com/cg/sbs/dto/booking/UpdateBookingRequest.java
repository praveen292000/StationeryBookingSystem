package com.cg.sbs.dto.booking;

import javax.validation.constraints.NotNull;

public class UpdateBookingRequest {

	private String bookingStatus;
	
	@NotNull(message="Order id cannot be null")
	private int bookingId;

	public UpdateBookingRequest() {
	
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}


}
