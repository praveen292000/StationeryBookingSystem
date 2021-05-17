package com.cg.sbs.dto.product;

/*
* Author : Praveen K
* Description : This is AddProduct DTO
*/

public class AddProduct {

	private String Product_Name;

	private int Product_Quantity;

	private double Product_Price;

	private boolean Product_Availability;

	public AddProduct() {

	}

	public boolean isProduct_Availability() {
		return Product_Availability;
	}

	public void setProduct_Availability(boolean product_Availability) {
		Product_Availability = product_Availability;
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
