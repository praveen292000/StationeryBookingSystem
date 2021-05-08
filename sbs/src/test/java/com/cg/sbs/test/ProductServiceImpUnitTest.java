package com.cg.sbs.test;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.sbs.entity.Product;
import com.cg.sbs.exception.ProductNotFoundException;
import com.cg.sbs.repository.IProductRepository;
import com.cg.sbs.service.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
class ProductServiceImpUnitTest {

	@Mock
	IProductRepository productRepository;

	@Spy
	@InjectMocks
	ProductServiceImpl productService;

	/*
	 * test to add product
	 */
	@Test
	public void addProductTest() {
		Product product = Mockito.mock(Product.class);

		Mockito.doNothing().when(productService).validateProduct(product);
		Product productSaved = Mockito.mock(Product.class);
		Mockito.when(productRepository.save(product)).thenReturn(productSaved);
		Product result = productService.addProduct(product);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(productSaved, result);
		Mockito.verify(productRepository).save(product);
	}

	/*
	 * test to search product
	 */
	@Test
	public void searchProductTest_1() {

		String Id = "1";
		Product product = Mockito.mock(Product.class);
		Optional<Product> optionalSaved = Optional.of(product);
		Mockito.when(productRepository.findById(Id)).thenReturn(optionalSaved);
		Product result = productService.searchProduct(Id);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(product, result);

	}

	@Test
	public void searchProductTest_2() {
		String id = "1";
		Optional<Product> optionalSaved = Optional.empty();
		Mockito.when(productRepository.findById(id)).thenReturn(optionalSaved);
		Executable executable = () -> productService.searchProduct(id);
		Assertions.assertThrows(ProductNotFoundException.class, executable);
	}

	/*
	 * test to update product
	 */
	@Test
	public void updateProductTest_1() {
		String productId = "1";
		Product product = Mockito.mock(Product.class);
		Mockito.doNothing().when(productService).validateProduct(product);
		Product productUpdated = Mockito.mock(Product.class);
		Mockito.when(product.getProduct_Id()).thenReturn(productId);
		Mockito.when(productRepository.existsById(productId)).thenReturn(true);
		Mockito.when(productRepository.save(product)).thenReturn(productUpdated);
		Product result = productService.updateProduct(product);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(productUpdated, result);
	}

	@Test
	public void updateProductTest_2() {
		String productId = "1";
		Product product = Mockito.mock(Product.class);
		Mockito.doNothing().when(productService).validateProduct(product);
		Mockito.when(product.getProduct_Id()).thenReturn(productId);
		Mockito.when(productRepository.existsById(productId)).thenReturn(false);
		Executable executable = () -> productService.updateProduct(product);
		Assertions.assertThrows(ProductNotFoundException.class, executable);
	}

	/*
	 * test to delete product
	 */
	@Test
	public void deleteProductTest_1() {
		String productId = "1";
		Product product = Mockito.mock(Product.class);
		Optional<Product> productOption = Optional.of(product);
		Mockito.when(productRepository.existsById(productId)).thenReturn(true);
		Mockito.when(productRepository.findById(productId)).thenReturn(productOption);
		Product result = productService.deleteProduct(productId);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(product, result);
	}

	@Test
	public void deleteProductTest_2() {
		String productId = "1";
		Mockito.when(productRepository.existsById(productId)).thenReturn(false);
		Executable executable = () -> productService.deleteProduct(productId);
		Assertions.assertThrows(ProductNotFoundException.class, executable);
	}

}