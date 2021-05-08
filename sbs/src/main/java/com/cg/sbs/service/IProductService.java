package com.cg.sbs.service;

import java.util.List;

import com.cg.sbs.entity.Product;

/*
* Author : Praveen K
* Description : This is IProductService
*/

public interface IProductService {

	Product searchProduct(String productId);

	Product addProduct(Product product);

	Product updateProduct(Product product);

	List<Product> viewAllProducts();

	Product deleteProduct(String productId);

}