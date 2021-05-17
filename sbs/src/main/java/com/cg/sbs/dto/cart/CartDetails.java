package com.cg.sbs.dto.cart;

import java.util.List;

public class CartDetails {

	private String customerId;
	private String firstName;
	private List<CartProductDetails> products;

	public CartDetails() {

	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public List<CartProductDetails> getProducts() {
		return products;
	}

	public void setProducts(List<CartProductDetails> products) {
		this.products = products;
	}

}
