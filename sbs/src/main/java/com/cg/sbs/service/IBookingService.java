package com.cg.sbs.service;

import java.util.List;

import com.cg.sbs.entity.Booking;
import com.cg.sbs.entity.Customer;

public interface IBookingService {

	Booking addBooking(Booking booking);
	
	Booking updateBooking(Booking booking);
	
	Booking removeBooking(Booking booking);
	
	Booking viewBooking(int bookingId);
	
	List<Booking> viewAllBooking(Customer customer);
	
	
}