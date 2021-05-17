package com.cg.sbs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sbs.entity.Product;
import com.cg.sbs.exception.InvalidProductException;
import com.cg.sbs.exception.InvalidProductIdException;
import com.cg.sbs.exception.InvalidProductNameException;
import com.cg.sbs.exception.ProductNotFoundException;
import com.cg.sbs.repository.IProductRepository;

/*
* Author : Praveen K
* Description : This is ProductServiceImplementaion.
*/

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository productRepository;
	

	/**
	 * Case: to View the list of all Products 
	 * Outcome: List of Products is viewed
	 */
	@Override
	public List<Product> viewAllProducts() {

		return productRepository.findAll();
	}


	/**
	 * Case: To Add the Products : product object is passed in the parameter
	 * Outcome: Products will be added
	 */
	@Override
	public Product addProduct(Product product) {
		validateProduct(product);
		Product productSaved = productRepository.save(product);
		return productSaved;

	}

	/**
	 *Case: To Update the Products : product object is passed in the parameter
	 * Outcome: Products will be updated.
	 */
	@Override
	public Product updateProduct(Product product) {

		validateProduct(product);
		boolean exists = productRepository.existsById(product.getProduct_Id());
		if (!exists) {
			throw new ProductNotFoundException("Product with id" + product.getProduct_Id() + "not present=");
		}
		Product updateProduct = productRepository.save(product);

		return updateProduct;
	}


	/**
	 * Case : To find the Products : productId is passed in the parameter
	 * Outcome: Products will be found.
	 */
	@Override
	public Product searchProduct(String productId) {

		Optional<Product> searchProduct = productRepository.findById(productId);
		if (!searchProduct.isPresent()) {
			throw new ProductNotFoundException("Product with ID:" + productId + "not present");
		}
		return searchProduct.get();
	}

	/**
	 * Case: To Delete the Products : productId is passed in the parameter
	 * Outcome: Products will be deleted.
	 */
	@Override
	public Product deleteProduct(String productId) {
		boolean exists = productRepository.existsById(productId);
		if (!exists) {
			throw new ProductNotFoundException("Product with Id " + productId + " not present");
		}
		Optional<Product> deleteProduct = productRepository.findById(productId);
	
		productRepository.deleteById(productId);

		return deleteProduct.get();

	}

	/**
	 * Case : Validates the Product input: product object is passed in the parameter
	 * Outcome : If the product is null, an InvalidProductException is thrown.
	 */
	public void validateProduct(Product product) {
		if (product == null) {
			throw new InvalidProductException("Product can't be null");
		}
		validateProductName(product.getProduct_Name());
		validateProductId(product.getProduct_Id());

	}

	/**
	 * Case : Validates the Product Id input: product_Id object is passed in the parameter
	 * Outcome : If the product_Id is null, an InvalidProductIdException is thrown.
	 */
	void validateProductId(String product_Id) {
		if (product_Id == null || product_Id.isEmpty()) {
			throw new InvalidProductIdException("Product Id can't be null or empty");
		}
	}

	/**
	 * Case : Validates the Product Name  input: product_Name object is passed in the parameter
	 * Outcome : If the product_Name is null, an InvalidProductNameException is thrown.
	 */
	void validateProductName(String product_Name) {
		if (product_Name == null || product_Name.isEmpty()) {
			throw new InvalidProductNameException("Product Name can't be null or empty");
		}
	}

}