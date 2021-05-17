package com.cg.sbs.dto.booking;

import javax.validation.constraints.NotNull;

public class VieworDeleteBookingRequest {

	@NotNull(message="Order id cannot be null")
	private int bookingId;

	public VieworDeleteBookingRequest() {
		

	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

}
