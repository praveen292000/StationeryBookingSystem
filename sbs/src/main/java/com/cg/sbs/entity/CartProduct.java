package com.cg.sbs.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "cart_product")
@Entity
public class CartProduct {

	@Id
	private String id;

	@JoinColumn(name = "cart")
	@ManyToOne
	private Cart cart;

	@JoinColumn(name = "product")
	@ManyToOne
	private Product product;

	private int quantity;

	public CartProduct() {

	}

	public static String id(Cart cart, Product product) {
		return cart.getCartId() + "-" + product.getProduct_Id() + "-ci";

	}

	public CartProduct(Cart cart, Product product, int quantity) {
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
		this.id = id(cart, product);
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
