package com.cg.sbs.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.sbs.dto.cart.CartDetails;
import com.cg.sbs.dto.cart.CartProductDetails;
import com.cg.sbs.entity.Cart;
import com.cg.sbs.entity.CartProduct;
import com.cg.sbs.entity.Product;
import com.cg.sbs.service.CartServiceImpl;
import com.cg.sbs.service.ProductServiceImpl;

@Component
public class Cart_Util {

	@Autowired
	private CartServiceImpl cartService;
	
	@Autowired
	private ProductServiceImpl productService;
	
	
	
	public Cart getCart() {
		return new Cart();
	}
	
	public CartDetails toCartDetails(Cart cart)
	{
		CartDetails fcd=new CartDetails();
		fcd.setCustomerId(cart.getCustomer().getCustomerId());
		fcd.setFirstName(cart.getCustomer().getFirstName());
		List<CartProduct>cartProducts=cartService.findCartProductsByCart(cart);
		fcd.setProducts(toCartProductDeatilList(cartProducts));
		return fcd;
	}
	
	public CartProductDetails toCartProductDeatil(CartProduct cartProduct)
	{
		CartProductDetails Product=new CartProductDetails();
		Product product=cartProduct.getProduct();
		Product.setProduct_Id(product.getProduct_Id());
		Product.setProduct_Name(product.getProduct_Name());
		Product.setProduct_Quantity(cartProduct.getQuantity());
		Product.setProduct_Price(cartProduct.getQuantity()*product.getProduct_Price());
		return Product;
	}
	
	public List<CartProductDetails> toCartProductDeatilList(List<CartProduct>cartProducts)
	{
		List<CartProductDetails> list=new ArrayList<>();
		for(CartProduct cartProduct:cartProducts)
		{
			list.add(toCartProductDeatil(cartProduct));
		}
		return list;
	}
	public String generateId() {
		StringBuilder builder= new StringBuilder();
		Random random= new Random();
		for(int i=0; i<10; i++) {
			int randomNum=random.nextInt(10);
			builder.append(randomNum);
		}
		return builder.toString();
	}
}

