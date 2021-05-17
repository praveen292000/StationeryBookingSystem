package com.cg.sbs.dto.cart;

import javax.validation.constraints.NotBlank;

public class FindCartRequest {

	@NotBlank
	private String customerId;

	public FindCartRequest() {

	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
