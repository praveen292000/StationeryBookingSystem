package com.cg.sbs.dto.product;

/*
* Author : Praveen K
* Description : This is UpdateProduct
*/
public class UpdateProduct {
	
	private String product_Id;

	private String product_Name;

	private int product_Quantity;

	private double product_Price;
	
	private boolean product_Availability;

	public UpdateProduct() {

	}

	public String getProduct_Id() {
		return product_Id;
	}

	public void setProduct_Id(String product_Id) {
		this.product_Id = product_Id;
	}

	public String getProduct_Name() {
		return product_Name;
	}

	public void setProduct_Name(String product_Name) {
		this.product_Name = product_Name;
	}

	public int getProduct_Quantity() {
		return product_Quantity;
	}

	public void setProduct_Quantity(int product_Quantity) {
		this.product_Quantity = product_Quantity;
	}

	public double getProduct_Price() {
		return product_Price;
	}

	public void setProduct_Price(double product_Price) {
		this.product_Price = product_Price;
	}

	public boolean isProduct_Availability() {
		return product_Availability;
	}

	public void setProduct_Availability(boolean product_Availability) {
		this.product_Availability = product_Availability;
	}
	

}
