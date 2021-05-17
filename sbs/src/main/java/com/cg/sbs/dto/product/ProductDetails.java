package com.cg.sbs.dto.product;

/*
* Author : Praveen K
* Description : This is ProductDetails DTO
*/
public class ProductDetails {

	private String Product_Id;
	private String Product_Name;
	private double Product_Price;
	private int Product_Quantity;
	private boolean Product_Availability;

	public ProductDetails() {

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

	public double getProduct_Price() {
		return Product_Price;
	}

	public void setProduct_Price(double product_Price) {
		Product_Price = product_Price;
	}

	public int getProduct_Quantity() {
		return Product_Quantity;
	}

	public void setProduct_Quantity(int product_Quantity) {
		Product_Quantity = product_Quantity;
	}

	public boolean isProduct_Availability() {
		return Product_Availability;
	}

	public void setProduct_Availability(boolean product_Availability) {
		Product_Availability = product_Availability;
	}

}
