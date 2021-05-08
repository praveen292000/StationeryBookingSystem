package com.cg.sbs.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sbs.entity.Orders;
import com.cg.sbs.entity.Customer;
import com.cg.sbs.entity.Cart;
import com.cg.sbs.entity.Product;
import com.cg.sbs.entity.Booking;
import com.cg.sbs.exception.OrderDoesNotExistException;
import com.cg.sbs.exception.InvalidOrderException;
import com.cg.sbs.repository.IBookingRepository;
import com.cg.sbs.repository.ICartRepository;
import com.cg.sbs.repository.ICustomerRepository;
import com.cg.sbs.repository.IOrdersRepository;

@Service
public class OrderServiceImpl implements IOrderService {
	
	@Autowired
	private IOrdersRepository ordersRepository;

	@Autowired
	private ICustomerRepository customerRepository;

	@Autowired
	private ICartRepository cartRepository;

	@Autowired
	private IBookingRepository bookingRepository;

	@Autowired
	public LocalDateTime currentDateTime() {
		return LocalDateTime.now();
	}

	
	@Override
	public Orders addOrders(Orders order) {
		validateOrder(order);
		LocalDateTime currentDateTime = currentDateTime();
		order.setOrderDate(currentDateTime);
		Booking booking = order.getBooking();
		double totalCost = 0;

		List<Product> products = booking.getProducts();
		for (Product product : products) {
			totalCost = totalCost + (product.getProduct_Price()* product.getProduct_Quantity());
		}
		
		order.setTotalProducts(products.size());
		order.setTotalCost(totalCost);
		
		return ordersRepository.save(order);
	}


	@Override
	public Orders updateOrders(Orders order) {
		validateOrder(order);
		int orderId = order.getOrderId();
		boolean exist = ordersRepository.existsById(orderId);
		if (!exist) {
			throw new OrderDoesNotExistException("Order doesn't exist for id =" + order.getOrderId());
		}
		return ordersRepository.save(order);
	}


	@Override
	public Orders removeOrders(Orders order) {
		validateOrder(order);
		int orderId = order.getOrderId();
		boolean exist = ordersRepository.existsById(orderId);
		if (!exist) {
			throw new OrderDoesNotExistException("Order doesn't exist for id =" + order.getOrderId());
		}

		ordersRepository.delete(order);
		return order;
	}


	@Override
	public Orders viewOrder(int orderId) {
		Optional<Orders> viewOrder = ordersRepository.findById(orderId);
		if (!viewOrder.isPresent()) {
			throw new OrderDoesNotExistException("Bill doesn't exist for id =" + orderId);
		}
		return viewOrder.get();
	}


	@Override
	public List<Orders> viewOrders(LocalDate startDate, LocalDate endDate) {
		LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.MIN);
		LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.MAX);
		List<Orders> orders = ordersRepository.findOrdersBetweenDates(startDateTime, endDateTime);
		if(orders.isEmpty()) {
			throw new OrderDoesNotExistException("Order doesn't exist for =" + startDate + " to " + endDate);
		}
		return orders;

	}


	@Override
	public List<Orders> viewOrders(String custId) {
		List<Orders> orders=new ArrayList<>();
		Customer customer = customerRepository.findCustomerByCustomerId(custId);
		Cart cart = cartRepository.findCartByCustomer(customer);
		List<Booking> bookings = bookingRepository.findByCart(cart);
		for(Booking booking:bookings)
		{
			orders.add(ordersRepository.findOrdersByBooking(booking));
		}
		return orders;
	}

	

	@Override
	public double totalCost(Orders order) {
		return order.getTotalCost();
	}


	public void validateOrder(Orders orders) {
		if (orders == null) {
			throw new InvalidOrderException("Order cannot be null");
		}
	}

}
