package com.cg.sbs.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sbs.constant.BookingStatus;
import com.cg.sbs.entity.Orders;
import com.cg.sbs.entity.Customer;
import com.cg.sbs.entity.Cart;
import com.cg.sbs.entity.Product;
import com.cg.sbs.entity.Booking;
import com.cg.sbs.exception.AddBookingException;
import com.cg.sbs.exception.InvalidBookingException;
import com.cg.sbs.exception.BookingNotFoundException;

import com.cg.sbs.repository.IBookingRepository;
import com.cg.sbs.repository.ICartProductRepository;
import com.cg.sbs.repository.ICartRepository;
import com.cg.sbs.repository.IOrdersRepository;
import com.cg.sbs.util.Orders_Util;

@Service
public class BookingServiceImpl implements IBookingService {
	
	@Autowired
	private IBookingRepository bookingRepository;

	@Autowired
	private ICartService cartService;

	@Autowired
	private IOrderService orderService;

	@Autowired
	private ICartRepository cartRepository;

	@Autowired
	private ICartProductRepository cartProductRepository;

	@Autowired
	private IOrdersRepository orderRepository;

	@Autowired
	private Orders_Util orderUtil;

	public LocalDateTime currentDateTime() {
		return LocalDateTime.now();
	}
	/*
	 Add Booking:
	 Booking object is passed in the parameter.Booking should be added.
	 If the booking is not added, an exception is thrown.
	 */
	@Transactional
	@Override
	public Booking addBooking(Booking booking) {
		validateBooking(booking);
		Cart cart = booking.getCart();
		List<Product> product = cartProductRepository.findProductsByCart(cart);
		if (product.isEmpty()) {
			throw new AddBookingException("Booking can't be done..The cart is empty");
		}		
		booking.setProducts(product);
		booking.setBookingDate(currentDateTime());
		booking.setBookingStatus(BookingStatus.CREATED);
		booking = bookingRepository.save(booking);

		cartService.clearCart(cart);

		Orders order = orderUtil.getOrders();
		order.setBooking(booking);
		orderService.addOrders(order);

		return booking;
	}
	
	/*
	 * Update Booking:
	 * booking object is passed in the parameter. Booking should be updated.
	 * If the booking is not updated, an exception is thrown.
	 */
	@Override
	public Booking updateBooking(Booking booking) {
		validateBooking(booking);
		int bookingId = booking.getBookingId();
		boolean exist = bookingRepository.existsById(bookingId);
		if (!exist) {
			throw new BookingNotFoundException("Order doesn't exist for id =" + booking.getBookingId());
		}
		return bookingRepository.save(booking);
	}

	
	/*
	 * Remove Booking:
	 * booking object is passed in the parameter. Booking should be removed.
	 * If the booking is not removed, an exception is thrown.
	 */
	@Override
	public Booking removeBooking(Booking booking) {
		validateBooking(booking);
		int bookingId = booking.getBookingId();
		boolean exist = bookingRepository.existsById(bookingId);
		if (!exist) {
			throw new BookingNotFoundException("Order doesn't exist for id =" + booking.getBookingId());
		}
		Orders order = orderRepository.findOrdersByBooking(booking);
		orderRepository.delete(order);
		bookingRepository.delete(booking);
		return booking;
	}

	/*
	 * View Booking:
	 * bookingId object is passed in the parameter. Booking should be viewed.
	 * If the booking is not viewed, an exception is thrown.
	 */
	@Override
	public Booking viewBooking(int bookingId) {
		Optional<Booking> optionBooking = bookingRepository.findById(bookingId);
		if (!optionBooking.isPresent()) {
			throw new BookingNotFoundException("Booking not found for id=" + bookingId);
		}
		return optionBooking.get();
	}


	/*
	 * List of all booking from the customer
	 * customer object is passed in the parameter. List of all Bookings is returned
	 */
	@Override
	public List<Booking> viewAllBooking(Customer customer) {
		Cart cart = cartRepository.findCartByCustomer(customer);
		List<Booking> bookingList = bookingRepository.findByCart(cart);
		return bookingList;

	}

	public void validateBooking(Booking booking) {
		if (booking == null) {
			throw new InvalidBookingException("Booking Cannot be null");
		}

	}


	}
