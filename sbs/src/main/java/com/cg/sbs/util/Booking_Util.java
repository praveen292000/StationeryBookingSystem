package com.cg.sbs.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.sbs.dto.booking.BookingResponse;
import com.cg.sbs.entity.Product;
import com.cg.sbs.entity.Booking;
@Component
public class Booking_Util {

	@Autowired
	private Date_Util dateUtil;

	public Booking getOrderDeatils() {
		return new Booking();
	}

	public BookingResponse toBookingResponse(Booking booking) {
		BookingResponse book = new BookingResponse();
		List<String> productName = new ArrayList<>();
		book.setBookingId(booking.getBookingId());
		book.setCustomerId(booking.getCart().getCustomer().getCustomerId());
		book.setFirstName(booking.getCart().getCustomer().getFirstName());
		book.setBookingStatus(booking.getBookingStatus());
		String dateText = dateUtil.toText(booking.getBookingDate());
		book.setBookingDate(dateText);

		for (Product product : booking.getProducts()) {
			productName.add(product.getProduct_Name());
		}
		book.setProductName(productName);

		return book;
	}

	public List<BookingResponse> toBookingResponseList(List<Booking> bookings) {
		List<BookingResponse> bookingResponses = new ArrayList<>();
		for (Booking booking : bookings) {
			bookingResponses.add(toBookingResponse(booking));
		}
		return bookingResponses;
	}

}
