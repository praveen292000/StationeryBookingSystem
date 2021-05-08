package com.cg.sbs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.sbs.dto.cart.CartDetails;
import com.cg.sbs.dto.cart.CartRequest;
import com.cg.sbs.dto.cart.ChangeQuantityRequest;
import com.cg.sbs.dto.cart.ClearCartRequest;
import com.cg.sbs.dto.cart.FindCartRequest;
import com.cg.sbs.entity.Cart;
import com.cg.sbs.entity.Product;
import com.cg.sbs.service.ICartService;
import com.cg.sbs.service.IProductService;
import com.cg.sbs.util.Cart_Util;

@Validated
@RequestMapping("/cart")
@RestController
public class Cart_Controller {
	
	@Autowired
	private ICartService cartService;

	@Autowired
	private Cart_Util cartUtil;

	@Autowired
	private IProductService productService;
	
	/*
	 Case: PostMapping addProduct to cart 
	 Outcome: Requests AddProductto cart DTO and add the Product in the database.
	 */	
	@PostMapping("/addproducttocart")
	public CartDetails addProductToCart(@RequestBody @Valid CartRequest request) {
		Cart cart = cartService.findCartByCustomer(request.getCustomerId());
		Product product = productService.searchProduct(request.getProductId());
		return cartUtil.toCartDetails(cartService.addProductToCart(cart, product));
	}
	
     /*
	 Case: PutMapping increase quantity 
	 Outcome: Requests Update quantity DTO and updates the cart  in the database. 
	 */
	@PutMapping("/increasequantity")
	public CartDetails increaseQuantity(@RequestBody @Valid ChangeQuantityRequest request) {
		Cart cart = cartService.findCartByCustomer(request.getCustomerId());
		Product product = productService.searchProduct(request.getProductId());
		return cartUtil.toCartDetails(cartService.increaseQuantity(cart, product, request.getQuantity()));
	}
	
	/*
	 Case: PutMapping reduce quantity 
	 Outcome: Request change quantity DTO and updates the  cart in the database.
	 */
	@PutMapping("/reducequantity")
	public CartDetails reduceQuantity(@RequestBody @Valid ChangeQuantityRequest request) {
		Cart cart = cartService.findCartByCustomer(request.getCustomerId());
		Product product = productService.searchProduct(request.getProductId());
		return cartUtil.toCartDetails(cartService.reduceQuantity(cart, product, request.getQuantity()));
	}
	
		/*
	 Case: DeleteMapping remove  product 
	 Outcome: Requests  remove product  DTO and deletes the Product in the database.
	 */
	@DeleteMapping("/removeproduct")
	public CartDetails removeProduct(@RequestBody @Valid CartRequest request) {
		Cart cart = cartService.findCartByCustomer(request.getCustomerId());
		Product product = productService.searchProduct(request.getProductId());
		return cartUtil.toCartDetails(cartService.removeProduct(cart, product));
	}

	/*
	 Case: DeleteMapping clearcart request 
	 Outcome: Clears all the cart details available in the database.
	 */
	@Transactional
	@DeleteMapping("/clear")
	public CartDetails clearCart(@RequestBody @Valid ClearCartRequest request) {
		Cart cart = cartService.findCartByCustomer(request.getCustomerId());
		return cartUtil.toCartDetails(cartService.clearCart(cart));
	}
	
	/*
	 Case: GetMapping  find the cart request 
	 Outcome: Displays all the  cart details  available in the database.
	 */
	@GetMapping("/find")
	public CartDetails findCart(@RequestBody @Valid FindCartRequest request) {
		return cartUtil.toCartDetails(cartService.findCartByCustomer(request.getCustomerId()));

	}

}
