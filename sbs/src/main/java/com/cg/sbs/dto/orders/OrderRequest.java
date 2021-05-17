package com.cg.sbs.dto.orders;

import javax.validation.constraints.NotNull;

public class OrderRequest {
	@NotNull(message="Order id cannot be null")
	private int orderId;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

}