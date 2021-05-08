package com.cg.sbs.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import com.cg.sbs.entity.Orders;
import com.cg.sbs.entity.Product;
import com.cg.sbs.entity.Booking;
import com.cg.sbs.exception.OrderDoesNotExistException;
import com.cg.sbs.exception.InvalidOrderException;
import com.cg.sbs.repository.IOrdersRepository;
import com.cg.sbs.service.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OrdersServiceImpUnitTest {
	@Mock
	IOrdersRepository ordersRepository;
	@Spy
	@InjectMocks
	OrderServiceImpl ordersService;

	@Test
	public void addOrderTest_1() {
		Orders order = Mockito.mock(Orders.class);
		Orders orderSaved = Mockito.mock(Orders.class);
		Mockito.doNothing().when(ordersService).validateOrder(order);
		Product product1 = new Product();
		product1.setProduct_Price(5);
		Product product2 = new Product();
		product2.setProduct_Price(10);
		
		List<Product> product = new ArrayList<>();
		product.add(product1);
		product.add(product2);
		Booking book = mock(Booking.class);
		when(book.getProducts()).thenReturn(product);
		when(order.getBooking()).thenReturn(book);
		Mockito.when(ordersRepository.save(order)).thenReturn(orderSaved);
		Orders result = ordersService.addOrders(order);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(orderSaved, result);
		verify(ordersService).validateOrder(order);
		verify(order).setTotalProducts(2);
		verify(ordersRepository).save(order);

	}


	@Test
	public void updateOrderTest_1() {
		int id = 1;
		Orders order = Mockito.mock(Orders.class);
		Orders orderSaved = Mockito.mock(Orders.class);
		Mockito.doNothing().when(ordersService).validateOrder(order);
		Mockito.when(order.getOrderId()).thenReturn(id);
		Mockito.when(ordersRepository.existsById(id)).thenReturn(true);
		Mockito.when(ordersRepository.save(order)).thenReturn(orderSaved);
		Orders result = ordersService.updateOrders(order);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(orderSaved, result);
		Mockito.verify(ordersRepository).existsById(1);

	}

	/*
	 * Updating order with exception
	 */

	@Test
	public void updateOrderTest_2() {
		int id = 1;
		Orders orders = Mockito.mock(Orders.class);
		Mockito.doNothing().when(ordersService).validateOrder(orders);
		Mockito.when(orders.getOrderId()).thenReturn(id);
		Mockito.when(ordersRepository.existsById(id)).thenReturn(false);
		Executable executable = () -> ordersService.updateOrders(orders);
		Assertions.assertThrows(OrderDoesNotExistException.class, executable);
		Mockito.verify(ordersRepository).existsById(1);

	}
	

	@Test
	public void removeOrderTest_1() {
		int id = 1;
		Orders orders = Mockito.mock(Orders.class);
		Mockito.doNothing().when(ordersService).validateOrder(orders);
		Mockito.when(orders.getOrderId()).thenReturn(id);
		Mockito.when(ordersRepository.existsById(id)).thenReturn(true);
		Orders result = ordersService.removeOrders(orders);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(orders, result);
		Mockito.verify(ordersRepository).existsById(1);

	}

	/*
	 * Removing order with exception
	 */

	@Test
	public void removeOrderTest_2() {
		int id = 1;
		Orders orders = Mockito.mock(Orders.class);
		Mockito.doNothing().when(ordersService).validateOrder(orders);
		Mockito.when(orders.getOrderId()).thenReturn(id);
		Mockito.when(ordersRepository.existsById(id)).thenReturn(false);
		Executable executable = () -> ordersService.removeOrders(orders);
		Assertions.assertThrows(OrderDoesNotExistException.class, executable);
		Mockito.verify(ordersRepository).existsById(1);

	}

	/*
	 *viewing order without exception
	 */

	@Test
	public void viewOrderTest_1() {
		Orders orders = Mockito.mock(Orders.class);
		int id = 1;
		Optional<Orders> optionOrders = Optional.of(orders);
		Mockito.when(ordersRepository.findById(id)).thenReturn(optionOrders);
		Orders result = ordersService.viewOrder(id);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(orders, result);
		Mockito.verify(ordersRepository).findById(1);
	}
	/*
	 *viewing order with exception
	 */

	@Test
	public void viewOrderTest_2() {
		int id = 1;
		Orders orders = Mockito.mock(Orders.class);
		Optional<Orders> optionOrders = Optional.empty();
		Mockito.when(ordersRepository.findById(id)).thenReturn(optionOrders);
		Executable executable = () -> ordersService.viewOrder(id);
		Assertions.assertThrows(OrderDoesNotExistException.class, executable);
	}

	/*
	 * Adding the cost of products in the booking and get total cost
	 */

	@Test
	public void totalCostTest_1() {
		Orders orders = Mockito.mock(Orders.class);
		Mockito.when(orders.getTotalCost()).thenReturn(15.0);
		double result = ordersService.totalCost(orders);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(15, result);

	}
	/*
	 * Validating the order
	 */

	@Test
	public void validateOrder_1() {
		Orders orders = null;
		Executable executable = () -> ordersService.validateOrder(orders);
		Assertions.assertThrows(InvalidOrderException.class, executable);
	}

}

