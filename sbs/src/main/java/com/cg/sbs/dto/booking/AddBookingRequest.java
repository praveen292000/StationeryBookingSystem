package com.cg.sbs.dto.booking;

import javax.validation.constraints.NotBlank;

public class AddBookingRequest {
	@NotBlank(message="CustomerId cannot be null for OrderDetails")
	private String customerId;

	public AddBookingRequest() {
		
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
