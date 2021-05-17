package com.cg.sbs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sbs.entity.Cart;
import com.cg.sbs.entity.CartProduct;
import com.cg.sbs.entity.Customer;
import com.cg.sbs.entity.Product;
import com.cg.sbs.exception.CartProductNotFoundException;
import com.cg.sbs.exception.InvalidCartException;
import com.cg.sbs.repository.ICartProductRepository;
import com.cg.sbs.repository.ICartRepository;

@Service
public class CartServiceImpl implements ICartService{

	@Autowired
	private ICartRepository cartRepository;

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private ICartProductRepository cartProductRepository;


	@Override
	public Cart addProductToCart(Cart cart, Product product) {
		validateCart(cart);
		increaseQuantity(cart, product, 1);
		return cart;
	}

	@Override
	public Cart increaseQuantity(Cart cart, Product product, int quantity) {
		validateCart(cart);
		String cartProductId = CartProduct.id(cart, product);
		Optional<CartProduct> optional = cartProductRepository.findById(cartProductId);
		if (!optional.isPresent()) {
			CartProduct cartProduct = new CartProduct(cart, product, quantity);
			cartProductRepository.save(cartProduct);
			return cart;
		}
		CartProduct cartProduct = optional.get();
		int existingQuantity = cartProduct.getQuantity();
		int updatedQuantity = existingQuantity + quantity;
		cartProduct.setQuantity(updatedQuantity);
		cartProductRepository.save(cartProduct);
		return cart;
	}

	@Override
	public Cart reduceQuantity(Cart cart, Product product, int quantity) {
		validateCart(cart);
		String cartProductId = CartProduct.id(cart, product);
		Optional<CartProduct> optional = cartProductRepository.findById(cartProductId);
		if (!optional.isPresent()) {
			throw new InvalidCartException("product not found in cart");
		}
		CartProduct cartProduct = optional.get();
		int existingQuantity = cartProduct.getQuantity();
		int updatedQuantity = existingQuantity - quantity;
		if (updatedQuantity <= 0) {
			cartProductRepository.delete(cartProduct);
			return cart;
		}
		cartProduct.setQuantity(updatedQuantity);
		cartProductRepository.save(cartProduct);
		return cart;
	}

	@Override
	public Cart removeProduct(Cart cart, Product product) {
		validateCart(cart);
		String cartProductId = CartProduct.id(cart, product);
		cartProductRepository.deleteById(cartProductId);
		return cart;
	}

	
	@Override
	public Cart clearCart(Cart cart) {
		validateCart(cart);
		cartProductRepository.deleteByCart(cart);
		return cart;
	}


	@Override
	public Cart findCartByCustomer(String customerId) {
		Customer customer = customerService.viewCustomer(customerId);
		Cart Cart = cartRepository.findCartByCustomer(customer);
		return Cart;
	}

	public void validateCart(Cart cart) {
		if (cart == null) {
			throw new InvalidCartException("Cart cannot be null");
		}
	}
	
	@Override
	public List<CartProduct> findCartProductsByCart(Cart cart){
		return cartProductRepository.findByCart(cart);
	}
	
	@Override
	public CartProduct findCartProduct(Cart cart, Product product){
		String id=CartProduct.id(cart,product);
		Optional<CartProduct> optional=cartProductRepository.findById(id);
	    if(!optional.isPresent()){
	    	throw new CartProductNotFoundException("cart product not found");
		}
	    return optional.get();
	}
	

}