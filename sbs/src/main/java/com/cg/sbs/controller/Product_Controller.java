package com.cg.sbs.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.sbs.dto.product.AddProduct;
import com.cg.sbs.dto.product.DeleteProduct;
import com.cg.sbs.dto.product.ProductDetails;
import com.cg.sbs.dto.product.SearchProduct;
import com.cg.sbs.dto.product.UpdateProduct;
import com.cg.sbs.entity.Product;
import com.cg.sbs.service.IProductService;
import com.cg.sbs.util.Product_Util;

/*
* Author : Praveen K
* Description : This is Product Controller
*/

@Validated
@RestController
@RequestMapping("/products")
public class Product_Controller {

	@Autowired
	private IProductService productService;

	@Autowired
	private Product_Util productUtil;

	
	/*
	 Case: GetMapping viewAllProducts
	 Outcome: Displays all the Products available in the database.
	 */
	@GetMapping("/view-all")
	public List<ProductDetails> viewAllProducts() {

		return productUtil.toProductDetailsList(productService.viewAllProducts());
	}

	/*
	 Case: GetMapping searchProduct
	 Outcome: Requests SearchProduct DTO and displays the Product in the database.
	 If ProductId is null,then InvalidProductException is thrown,
	 If ProductId is not found,then ProductNotFoundException is thrown.
	 */
	@GetMapping("/search")
	public ProductDetails searchProduct(@Valid @RequestBody SearchProduct request) {
	return productUtil.toProductDetails(productService.searchProduct(request.getProduct_Id()));

	}

	/*
	 Case: PostMapping addProduct
	 Outcome: Requests AddProduct DTO and add the Product in the database.
	 */	
	@PostMapping("/add")
	public ProductDetails addProduct(@Valid @RequestBody AddProduct request) {

		Product product = productUtil.getProduct();
		product.setProduct_Id(productUtil.generateId());
		product.setProduct_Name(request.getProduct_Name());
		product.setProduct_Price(request.getProduct_Price());
		product.setProduct_Quantity(request.getProduct_Quantity());
		product.setProduct_Availability(request.isProduct_Availability());
		product = productService.addProduct(product);
		return productUtil.toProductDetails(product);

	}

	/*
	 Case: PutMapping updateProduct
	 Outcome: Requests UpdateProduct DTO and updates the Product in the database.
	 If ProductId is null,then InvalidProductException is thrown,
	 If ProductId is not found,then ProductNotFoundException is thrown.
	 */
	@PutMapping("/update")
	public ProductDetails updateProduct(@RequestBody @Valid UpdateProduct request) {
	
		Product product = productService.searchProduct(request.getProduct_Id());
		product.setProduct_Name(request.getProduct_Name());
		product.setProduct_Price(request.getProduct_Price());
		product.setProduct_Quantity(request.getProduct_Quantity());
		product.setProduct_Availability(request.isProduct_Availability());
		return productUtil.toProductDetails(productService.updateProduct(product));
	}

	/*
	 Case: DeleteMapping deleteProduct
	 Outcome: Requests DeleteProduct DTO and deletes the Product in the database.
	 If ProductId is not found,then ProductNotFoundException is thrown.
	 */
	@DeleteMapping("/delete")
	public ProductDetails deleteProduct(@Valid @RequestBody DeleteProduct request) {
		return productUtil.toProductDetails(productService.deleteProduct(request.getProduct_Id()));

	}

}