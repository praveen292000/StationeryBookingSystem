package com.cg.sbs.dto.product;

import javax.validation.constraints.NotBlank;

/*
* Author : Praveen K
* Description : This is SearchProduct
*/
public class SearchProduct {
	@NotBlank(message = "ProductId can't be null")
	private String Product_Id;

	public String getProduct_Id() {
		return Product_Id;
	}

	public void setProduct_Id(String product_Id) {
		Product_Id = product_Id;
	}

}
