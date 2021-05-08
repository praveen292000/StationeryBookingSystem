package com.cg.sbs.dto.cart;

public class CartProductDetails {
	
	private String Product_Id;
	private String Product_Name;
	private int Product_Quantity;
	private double Product_Price;
	
	
	public CartProductDetails() {

	}

	public String getProduct_Id() {
		return Product_Id;
	}

	public void setProduct_Id(String product_Id) {
		Product_Id = product_Id;
	}

	public String getProduct_Name() {
		return Product_Name;
	}

	public void setProduct_Name(String product_Name) {
		Product_Name = product_Name;
	}

	public int getProduct_Quantity() {
		return Product_Quantity;
	}

	public void setProduct_Quantity(int product_Quantity) {
		Product_Quantity = product_Quantity;
	}

	public double getProduct_Price() {
		return Product_Price;
	}

	public void setProduct_Price(double product_Price) {
		Product_Price = product_Price;
	}

	
	
}
