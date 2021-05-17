package com.cg.sbs.service;

import java.util.List;

import com.cg.sbs.entity.Cart;
import com.cg.sbs.entity.CartProduct;
import com.cg.sbs.entity.Product;

public interface ICartService {
	
	Cart addProductToCart(Cart cart,Product product);
	
	Cart increaseQuantity(Cart cart,Product product,int quantity);
	
	Cart reduceQuantity(Cart cart,Product product,int quantity);
	
	Cart removeProduct(Cart cart,Product product);
	
	Cart clearCart(Cart cart);
	
	Cart findCartByCustomer(String customerId);
	
	List<CartProduct> findCartProductsByCart(Cart cart);
	
	CartProduct findCartProduct(Cart cart, Product product);

}
