package com.cg.sbs.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sbs.entity.Address;
import com.cg.sbs.entity.Cart;
import com.cg.sbs.entity.CartProduct;
import com.cg.sbs.entity.Customer;
import com.cg.sbs.entity.Product;
import com.cg.sbs.exception.CustomerNotFoundException;
import com.cg.sbs.exception.InvalidCustomerAddressException;
import com.cg.sbs.exception.InvalidCustomerException;
import com.cg.sbs.exception.InvalidCustomerPhoneNumberException;
import com.cg.sbs.repository.IAddressRepository;
import com.cg.sbs.repository.ICartProductRepository;
import com.cg.sbs.repository.ICartRepository;
import com.cg.sbs.repository.ICustomerRepository;
import com.cg.sbs.util.Cart_Util;

/*
 *
 * Author : G V V Nagendra Prasad
 * Description : This is customer service Implementation class
 *
*/


@Service
public class CustomerServiceImpl implements ICustomerService {
	
	
	@Autowired
	private ICustomerRepository customerRepository;

	@Autowired
	private IAddressRepository addressRepository;

	@Autowired
	private ICartRepository cartRepository;

	@Autowired
	private ICartProductRepository cartProductRepository;

	@Autowired
	private Cart_Util cartUtil;

	/*  this method generates customer id */


	public String generateId() {
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int randomNum = random.nextInt(10);
			builder.append(randomNum);
		}
		return builder.toString();
	}

	/*  this method adds customers  */

	@Override
	public Customer addCustomer(Customer customer) {
		validateCustomer(customer);
		validatePhone(customer.getMobileNumber());
		Address address = customer.getAddress();
		addressRepository.save(address);
		customer.setCustomerId(generateId());
		Customer saved = customerRepository.save(customer);
		Cart cart = cartUtil.getCart();
		cart.setCartId(generateId());
		cart.setCustomer(saved);
		cartRepository.save(cart);
		return saved;
	}


	/*  this method updates customers details in database  */


	@Override
	public Customer updateCustomer(Customer customer) {
		validateCustomer(customer);
		String customerId = customer.getCustomerId();
		boolean exist = customerRepository.existsById(customerId);
		if (!exist) {
			throw new CustomerNotFoundException("Customer doesn't exist for id =" + customer.getCustomerId());
		}

		Customer updateCustomer = customerRepository.save(customer);

		return updateCustomer;
	}

	/*  this method removes customers details in database  */

	@Override
	public Customer removeCustomer(Customer customer) {
		validateCustomer(customer);
		String customerId = customer.getCustomerId();
		boolean exist = customerRepository.existsById(customerId);
		if (!exist) {
			throw new CustomerNotFoundException("Customer doesn't exist for id =" + customer.getCustomerId());
		}
		Cart cart = cartRepository.findCartByCustomer(customer);
		List<CartProduct> cartProducts = cartProductRepository.findByCart(cart);
		for (CartProduct cartProduct : cartProducts) {
			cartProductRepository.deleteById(cartProduct.getId());
		}

		cartRepository.delete(cart);
		customerRepository.delete(customer);
		return customer;
	}


	/*  this method displays customer details in database by giving Id  */

	@Override
	public Customer viewCustomer(String id) {
		Optional<Customer> viewCustomer = customerRepository.findById(id);
		if (!viewCustomer.isPresent()) {
			throw new CustomerNotFoundException("Customer doesn't exist for id =" + id);
		}
		return viewCustomer.get();
	}


	/*  this method displays all the customer details in database  */

	@Override
	public List<Customer> viewAllCustomer() {

		return customerRepository.findAll();
	}

	public void validateCustomer(Customer customer) {
		if (customer == null) {
			throw new InvalidCustomerException("Customer cannot be null");
		}

	}

	public void validatePhone(String phoneNumber) {
		if (phoneNumber == null) {
			throw new InvalidCustomerPhoneNumberException("Customer Phone Number cannot be null");
		}
	}

	public void validateAddress(String address) {
		if (address == null) {
			throw new InvalidCustomerAddressException("Customer Address cannot be null");
		}
	}
	
	
}
