package com.cg.sbs.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

import com.cg.sbs.dto.booking.AddBookingRequest;
import com.cg.sbs.dto.booking.VieworDeleteBookingRequest;
import com.cg.sbs.dto.booking.BookingResponse;
import com.cg.sbs.dto.booking.UpdateBookingRequest;
import com.cg.sbs.entity.Customer;
import com.cg.sbs.entity.Cart;
import com.cg.sbs.entity.Booking;
import com.cg.sbs.service.CartServiceImpl;
import com.cg.sbs.service.CustomerServiceImpl;
import com.cg.sbs.util.Booking_Util;
import com.cg.sbs.service.IBookingService;

@Validated
@RequestMapping("/booking")
@RestController
public class Booking_Controller {

	@Autowired
	private IBookingService bookingService;

	@Autowired
	private CartServiceImpl cartService;

	@Autowired
	private CustomerServiceImpl customerService;

	@Autowired
	private Booking_Util bookingUtil;

	@PostMapping("/add")
	public BookingResponse addBooking(@RequestBody @Valid AddBookingRequest request) {
		Booking booking = new Booking();
		Cart cart = cartService.findCartByCustomer(request.getCustomerId());
		booking.setCart(cart);
		return bookingUtil.toBookingResponse(bookingService.addBooking(booking));
	}

	@PutMapping("/update")
	public BookingResponse updateBooking(@RequestBody @Valid UpdateBookingRequest request) {
		Booking booking = bookingService.viewBooking(request.getBookingId());
		booking.setBookingStatus(request.getBookingStatus());
		return bookingUtil.toBookingResponse(bookingService.updateBooking(booking));
	}

	@DeleteMapping("/delete")
	public BookingResponse deleteBooking(@RequestBody @Valid VieworDeleteBookingRequest request) {
		Booking booking = bookingService.viewBooking(request.getBookingId());
		return bookingUtil.toBookingResponse(bookingService.removeBooking(booking));
	}

	@GetMapping("/view/{id}")
	public BookingResponse viewBooking(@PathVariable @NotNull(message="bookingId cannot be null") Integer id ) {
		return bookingUtil.toBookingResponse(bookingService.viewBooking(id));
	}

	@GetMapping("/viewbycustomerid/{id}")
	public List<BookingResponse> viewAllBookingByCustomer(@PathVariable @NotBlank(message="customerId cannot be null") String id) {

		Customer customer = customerService.viewCustomer(id);
		return bookingUtil.toBookingResponseList(bookingService.viewAllBooking(customer));
	}
	

}