package com.cg.sbs.dto.orders;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderUpdateRequest {
	@NotNull(message="Order id cannot be null")
	private int orderId;

	@NotNull
	@Min(value=1,message="Total product quantity cannot be less than zero")
	private int totalProducts;
	@NotNull
	private double totalCost;


	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getTotalProducts() {
		return totalProducts;
	}

	public void setTotalProducts(int totalProducts) {
		this.totalProducts = totalProducts;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

}
