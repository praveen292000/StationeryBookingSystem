package com.cg.sbs.test;

import static org.mockito.Mockito.doNothing;

import java.time.LocalDateTime;
import java.util.List;
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

import com.cg.sbs.entity.Booking;
import com.cg.sbs.entity.Cart;
import com.cg.sbs.entity.Orders;
import com.cg.sbs.entity.Product;
import com.cg.sbs.exception.AddBookingException;
import com.cg.sbs.exception.BookingNotFoundException;
import com.cg.sbs.exception.InvalidBookingException;
import com.cg.sbs.repository.IBookingRepository;
import com.cg.sbs.repository.ICartProductRepository;
import com.cg.sbs.repository.IOrdersRepository;
import com.cg.sbs.service.BookingServiceImpl;
import com.cg.sbs.service.CartServiceImpl;
import com.cg.sbs.service.OrderServiceImpl;
import com.cg.sbs.util.Orders_Util;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplUnitTest {

	@Mock
	IBookingRepository bookingRepository;

	@Mock
	CartServiceImpl cartService;
	@Mock
	OrderServiceImpl orderService;
	
	@Mock
	private Orders_Util orderUtil;

	@Mock
	ICartProductRepository cartProductRepository;
	@Mock
	IOrdersRepository orderRepository;

	@Spy
	@InjectMocks
	BookingServiceImpl bookingService;


	@Test
	public void addBookingTest_1() {
		Booking booking = Mockito.mock(Booking.class);
		doNothing().when(bookingService).validateBooking(booking);
		List<Product> products = Mockito.mock(List.class);
		Cart cart = Mockito.mock(Cart.class);
		Mockito.when(booking.getCart()).thenReturn(cart);		
		Mockito.when(cartProductRepository.findProductsByCart(cart)).thenReturn(products);
		Mockito.when(products.isEmpty()).thenReturn(false);
		LocalDateTime currentTime = LocalDateTime.now();
		Mockito.doReturn(currentTime).when(orderService).currentDateTime();
		Booking bookingSaved = Mockito.mock(Booking.class);
		Mockito.when(bookingRepository.save(booking)).thenReturn(bookingSaved);
		Orders order=Mockito.mock(Orders.class);
		Mockito.when(orderUtil.getOrders()).thenReturn(order);
		Mockito.when(orderService.addOrders(order)).thenReturn(order);
 		Booking result = bookingService.addBooking(booking);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result, bookingSaved);
		Mockito.verify(orderUtil).getOrders();
		Mockito.verify(bookingRepository).save(bookingSaved);
		Mockito.verify(bookingService).validateBooking(booking);
		Mockito.verify(booking).setProducts(products);
		Mockito.verify(booking).setBookingDate(currentTime);
	//	Mockito.verify(booking).setBookingStatus(booking.CREATED);
	}

	@Test
	public void addBookingTest_2() {
		Booking booking = Mockito.mock(Booking.class);
		doNothing().when(bookingService).validateBooking(booking);
		List<Product> product = Mockito.mock(List.class);
		Cart cart = Mockito.mock(Cart.class);
		Mockito.when(booking.getCart()).thenReturn(cart);
		Mockito.when(cartProductRepository.findProductsByCart(cart)).thenReturn(product);
		Mockito.when(product.isEmpty()).thenReturn(true);
		Executable executable = () -> bookingService.addBooking(booking);
		Assertions.assertThrows(AddBookingException.class, executable);

	}

	@Test
	public void updateBookingTest_1() {
		int id = 1;
		Booking booking = Mockito.mock(Booking.class);
		Mockito.doNothing().when(bookingService).validateBooking(booking);
		Booking bookingUpdated = Mockito.mock(Booking.class);
		Mockito.when(booking.getBookingId()).thenReturn(id);
		Mockito.when(bookingRepository.existsById(id)).thenReturn(true);
		Mockito.when(bookingRepository.save(booking)).thenReturn(bookingUpdated);
		Booking result = bookingService.updateBooking(booking);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(bookingUpdated, result);
		Mockito.verify(orderRepository).existsById(1);
		Mockito.verify(bookingRepository).save(booking);
	}

	@Test
	public void updateBookingTest_2() {
		int id = 1;
		Booking booking = Mockito.mock(Booking.class);
		Mockito.doNothing().when(bookingService).validateBooking(booking);
		Mockito.when(booking.getBookingId()).thenReturn(id);
		Mockito.when(bookingRepository.existsById(id)).thenReturn(false);
		Executable executable = () -> bookingService.updateBooking(booking);
		Assertions.assertThrows(BookingNotFoundException.class, executable);

	}

	@Test
	public void removeBookingTest_1() {
		int id = 1;
		Booking booking = Mockito.mock(Booking.class);
		doNothing().when(bookingService).validateBooking(booking);
		Mockito.when(booking.getBookingId()).thenReturn(id);
		Mockito.when(bookingRepository.existsById(id)).thenReturn(true);
		Orders order=Mockito.mock(Orders.class);
		Mockito.when(orderRepository.findOrdersByBooking(booking)).thenReturn(order);
		Booking result = bookingService.removeBooking(booking);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(booking, result);
		Mockito.verify(orderRepository).existsById(1);
	}

	@Test
	public void removeBookingTest_2() {
		int id = 1;
		Booking booking = Mockito.mock(Booking.class);
		Mockito.doNothing().when(bookingService).validateBooking(booking);
		Mockito.when(booking.getBookingId()).thenReturn(id);
		Mockito.when(bookingRepository.existsById(id)).thenReturn(false);
		Executable executable = () -> bookingService.removeBooking(booking);
		Assertions.assertThrows(BookingNotFoundException.class, executable);

	}


	@Test
	public void viewBookingTest_1() {
		Booking booking = Mockito.mock(Booking.class);
		int id = 1;
		Optional<Booking> optionBooking = Optional.of(booking);
		Mockito.when(bookingRepository.findById(id)).thenReturn(optionBooking);
		Booking result = bookingService.viewBooking(id);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(booking, result);
		Mockito.verify(orderRepository).findById(1);
	}

	@Test
	public void viewBookingTest_2() {
		int id = 1;
		Optional<Booking> optionBooking = Optional.empty();
		Mockito.when(bookingRepository.findById(id)).thenReturn(optionBooking);
		Executable executable = () -> bookingService.viewBooking(id);
		Assertions.assertThrows(BookingNotFoundException.class, executable);
	}



	@Test
	public void validateBookingTest() {
		Booking booking = null;
		Executable executable = () -> bookingService.validateBooking(booking);
		Assertions.assertThrows(InvalidBookingException.class, executable);
	}

}