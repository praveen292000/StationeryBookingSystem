package com.cg.sbs.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
* Author : Praveen K
* Description : This is Product Entity
*/

@Entity
@Table(name = "Products_tbl")
public class Product {

	@Id
	private String Product_Id;	
	private String Product_Name;
	private double Product_Price;
	private int Product_Quantity;
	private boolean Product_Availability;

	public Product() {

	}

	public Product(String product_Id, String product_Name, double product_Price, int product_Quantity,
			boolean product_Availability) {
		super();
		Product_Id = product_Id;
		Product_Name = product_Name;
		Product_Price = product_Price;
		Product_Quantity = product_Quantity;
		Product_Availability = product_Availability;
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

	@Override
	public String toString() {
		return "Product_Entity [Product_Id=" + Product_Id + ", Product_Name=" + Product_Name + ", Product_Price="
				+ Product_Price + ", Product_Quantity=" + Product_Quantity + ", Product_Availability="
				+ Product_Availability + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Product_Id == null) ? 0 : Product_Id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (Product_Id == null) {
			if (other.Product_Id != null)
				return false;
		} else if (!Product_Id.equals(other.Product_Id))
			return false;
		return true;
	}

}
