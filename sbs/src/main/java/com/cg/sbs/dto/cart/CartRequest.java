package com.cg.sbs.dto.cart;

import javax.validation.constraints.NotBlank;

public class CartRequest {

	@NotBlank
	private String customerId;
	@NotBlank
	private String productId;

	public CartRequest() {

	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

}
