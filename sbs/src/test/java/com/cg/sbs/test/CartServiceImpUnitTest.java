package com.cg.sbs.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.sbs.entity.CartProduct;
import com.cg.sbs.entity.Cart;
import com.cg.sbs.entity.Product;
import com.cg.sbs.exception.InvalidCartException;

import com.cg.sbs.repository.ICartProductRepository;
import com.cg.sbs.repository.ICartRepository;
import com.cg.sbs.repository.IProductRepository;
import com.cg.sbs.service.CartServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CartServiceImpUnitTest {

	@Mock
	ICartRepository cartRepository;

	@Mock
	IProductRepository productRepository;

	@Mock
	ICartProductRepository cartProductRepository;

	@Spy
	@InjectMocks
	CartServiceImpl cartService;

	/**
	 * scenario when Product exist in cart before , Products list is not null
	 */
	@Test
	void addProductToCartTest_1() {
		Cart cart = Mockito.mock(Cart.class);
		Product product = Mockito.mock(Product.class);
		Mockito.doNothing().when(cartService).validateCart(cart);
		Mockito.doReturn(cart).when(cartService).increaseQuantity(cart, product, 1);
		Cart result = cartService.addProductToCart(cart, product);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(cart, result);
		Mockito.verify(cartService).increaseQuantity(cart, product, 1);
		Mockito.verify(cartService).validateCart(cart);
	}

	/**
	 * scenario , cart is null
	 */

	@Test
	void addProductToCartTest_2() {
		Cart cart = Mockito.mock(Cart.class);
		Mockito.doThrow(InvalidCartException.class).when(cartService).validateCart(cart);
		Executable executable = () -> cartService.validateCart(cart);
		Assertions.assertThrows(InvalidCartException.class, executable);

	}



	@Test
	void removeTest_1() {
		String cartProductId = "string";
		Cart cart = Mockito.mock(Cart.class);
		Mockito.doNothing().when(cartService).validateCart(cart);
//		CartItem cartItem=Mockito.mock(CartItem.class);
		Product product = Mockito.mock(Product.class);
		Mockito.when(CartProduct.id(cart, product)).thenReturn(cartProductId);
		Cart result = cartService.removeProduct(cart, product);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(cart, result);
		Mockito.verify(cartService).validateCart(cart);
	}

	@Test
	void clearCartTest_1() {
		Cart cart = Mockito.mock(Cart.class);
		Mockito.doNothing().when(cartService).validateCart(cart);
		Cart result = cartService.clearCart(cart);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(cart, result);
		Mockito.verify(cartService).validateCart(cart);

	}

	@Test
	public void validateCartTest() {
		Cart cart = null;
		Executable executable = () -> cartService.validateCart(cart);
		Assertions.assertThrows(InvalidCartException.class, executable);
	}

}

