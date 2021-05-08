package com.cg.sbs.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.cg.sbs.dto.product.ProductDetails;
import com.cg.sbs.entity.Product;

/*
* Author : Praveen K
* Description : This is Product_Util.
*/
@Component
public class Product_Util {

	public Product getProduct() {
		return new Product();
	}

	public ProductDetails toProductDetails(Product product) {
		ProductDetails id = new ProductDetails();
		id.setProduct_Id(product.getProduct_Id());
		id.setProduct_Name(product.getProduct_Name());
		id.setProduct_Price(product.getProduct_Price());
		id.setProduct_Quantity(product.getProduct_Quantity());
		id.setProduct_Availability(product.isProduct_Availability());

		return id;

	}

	public String generateId() {
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int randomNum = random.nextInt(10);
			builder.append(randomNum);
		}
		return builder.toString();

	}

	public List<ProductDetails> toProductDetailsList(List<Product> list) {
		List<ProductDetails> result = new ArrayList<>();
		for (Product product : list) {
			result.add(toProductDetails(product));
		}
		return result;
	}

}
