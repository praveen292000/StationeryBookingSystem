package com.cg.sbs.dto.cart;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ChangeQuantityRequest {
	
	@NotBlank
	private String customerId;
	@NotBlank
	private String productId;
	@Min(1)
	private int quantity;

	public ChangeQuantityRequest() {
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
