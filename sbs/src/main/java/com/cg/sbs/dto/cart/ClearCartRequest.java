package com.cg.sbs.dto.cart;

import javax.validation.constraints.NotBlank;

public class ClearCartRequest {

	@NotBlank
	private String customerId;

	public ClearCartRequest() {

	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
