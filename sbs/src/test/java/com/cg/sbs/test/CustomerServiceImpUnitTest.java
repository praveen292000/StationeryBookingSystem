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

import com.cg.sbs.entity.Address;
import com.cg.sbs.entity.Customer;
import com.cg.sbs.entity.Cart;
import com.cg.sbs.exception.CustomerNotFoundException;
import com.cg.sbs.exception.InvalidCustomerException;
import com.cg.sbs.repository.IAddressRepository;
import com.cg.sbs.repository.ICartRepository;
import com.cg.sbs.repository.ICustomerRepository;
import com.cg.sbs.service.CustomerServiceImpl;
import com.cg.sbs.util.Cart_Util;

@ExtendWith(MockitoExtension.class)

public class CustomerServiceImpUnitTest {
	@Mock
	ICustomerRepository customerRepository;
	@Mock
	IAddressRepository addressRepository;
	@Mock
	ICartRepository cartRepository;
	
	@Mock
	Cart_Util cartUtil;

	@Spy
	@InjectMocks
	CustomerServiceImpl customerService;

	/*
	 * test to add new customer
	 */
	@Test

	 void addCustomerTest_1() {
		String mobile="0123456789";
		Customer customer = Mockito.mock(Customer.class);
		Customer customerSaved = Mockito.mock(Customer.class);
		Address address=Mockito.mock(Address.class);
		Mockito.doNothing().when(customerService).validateCustomer(customer);
		Mockito.when(customer.getMobileNumber()).thenReturn(mobile);
		Mockito.doNothing().when(customerService).validatePhone(mobile);
		Mockito.when(customer.getAddress()).thenReturn(address);
		Mockito.when(addressRepository.save(address)).thenReturn(address);
		Mockito.when(customerRepository.save(customer)).thenReturn(customerSaved);
		Cart cart=Mockito.mock(Cart.class);
		Mockito.when(cartUtil.getCart()).thenReturn(cart);
		Mockito.when(cartRepository.save(cart)).thenReturn(cart);
		Customer result = customerService.addCustomer(customer);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(customerSaved, result);
		Mockito.verify(customerService).validatePhone(mobile);

	}



	/*
	 * test to update details of an existing customer from the list
	 */
	@Test

	 void updateCustomerTest_1() {
		String id = "1";
		Customer customer = Mockito.mock(Customer.class);
		Mockito.doNothing().when(customerService).validateCustomer(customer);
		Customer customerSaved = Mockito.mock(Customer.class);
		Mockito.when(customer.getCustomerId()).thenReturn(id);
		Mockito.when(customerRepository.existsById(id)).thenReturn(true);
		Mockito.when(customerRepository.save(customer)).thenReturn(customerSaved);
		Customer result = customerService.updateCustomer(customer);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(customerSaved, result);

	}

	/*
	 * test to update details of an existing customer from the list
	 */
	@Test
	 void updateCustomerTest_2() {
		String id = "1";
		Customer customer = Mockito.mock(Customer.class);
		Mockito.doNothing().when(customerService).validateCustomer(customer);
		Mockito.when(customer.getCustomerId()).thenReturn(id);
		Mockito.when(customerRepository.existsById(id)).thenReturn(false);
		Executable executable = () -> customerService.updateCustomer(customer);
		Assertions.assertThrows(CustomerNotFoundException.class, executable);

	}

	/*
	 * test to update details of an existing customer from the list
	 */
	@Test

	 void viewCustomerTest_1() {
		String id = "1";
		Customer customer = Mockito.mock(Customer.class);
		Optional<Customer> optionalSaved = Optional.of(customer);
		Mockito.when(customerRepository.findById(id)).thenReturn(optionalSaved);
		Customer result = customerService.viewCustomer(id);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(customer, result);

	}

	/*
	 * test to update details of an existing customer from the list
	 */
	@Test

	public void viewCustomerTest_2() {
		String id = "1";
		Optional<Customer> optionalSaved = Optional.empty();
		Mockito.when(customerRepository.findById(id)).thenReturn(optionalSaved);
		Executable executable = () -> customerService.viewCustomer(id);
		Assertions.assertThrows(CustomerNotFoundException.class, executable);

	}

	/*
	 * test to check if the customer exists or not
	 */
	@Test

	public void validateCustomer_1() {
		Customer customer = null;
		Executable executable = () -> customerService.validateCustomer(customer);
		Assertions.assertThrows(InvalidCustomerException.class, executable);
	}

}