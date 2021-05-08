package com.cg.sbs.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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

import com.cg.sbs.dto.customer.AddCustomer;
import com.cg.sbs.dto.customer.CustomerDetails;
import com.cg.sbs.dto.customer.RemoveCustomerRequest;
import com.cg.sbs.dto.customer.UpdateCustomerRequest;
import com.cg.sbs.dto.product.ProductDetails;
import com.cg.sbs.entity.Address;
import com.cg.sbs.entity.Customer;
import com.cg.sbs.service.ICustomerService;
import com.cg.sbs.util.Address_Util;
import com.cg.sbs.util.Customer_Util;


/*
 *
 * Author : G V V Nagendra Prasad
 * Description : This is Customer Controller
 *
*/



@Validated
@RequestMapping("/customers")
@RestController
public class Customer_Controller {

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private Customer_Util customerUtil;

	@Autowired
	private Address_Util addressUtil;




	/**
	 * Case : Adding the customer input: Customer Object is passed in the
	 * Outcome: Add customer and customer details, and generate
	 * customer Id
	 */
	@PostMapping("/add")
	public CustomerDetails addCustomer(@RequestBody @Valid AddCustomer request) {
		Customer customer = customerUtil.getCustomer();
		customer.setAge(request.getAge());
		customer.setFirstName(request.getFirstName());
		customer.setLastName(request.getLastName());
		customer.setGender(request.getGender());
		customer.setMobileNumber(request.getMobileNumber());
		customer.setEmail(request.getEmail());
		Address address = addressUtil.getAddress();
		address.setAddressId(addressUtil.generateId());
		address.setArea(request.getArea());
		address.setStreetNo(request.getStreetNo());
		address.setBuildingName(request.getBuildingName());
		address.setCity(request.getCity());
		address.setCountry(request.getCountry());
		address.setState(request.getState());
		address.setPincode(request.getPincode());
		customer.setAddress(address);
		return customerUtil.customerDetails(customerService.addCustomer(customer));
	}




	/**
	 * Case : updating the customer input: Customer Object is passed in the
	 * Outcome: this will update customer and customer details of exiexisting Users
	 */
	@PutMapping("/update")
	public CustomerDetails updateCustomer(@RequestBody @Valid UpdateCustomerRequest request) {

		Customer customer = customerService.viewCustomer(request.getId());
		customer.setAge(request.getAge());
		customer.setFirstName(request.getFirstName());
		customer.setLastName(request.getLastName());
		customer.setMobileNumber(request.getMobileNumber());
		customer.setEmail(request.getEmail());
		return customerUtil.customerDetails(customerService.updateCustomer(customer));
	}




	/**
	 * Case : deleting the customer 
	 * Outcome: Deleting the customer and customer details from database
	 */
	@DeleteMapping("/delete")
	public CustomerDetails deleteCustomer(@RequestBody @Valid RemoveCustomerRequest request) {
		Customer customer = customerService.viewCustomer(request.getId());
		return customerUtil.customerDetails(customerService.removeCustomer(customer));
	}




	/**
	 * Case : view  customer input: Customer Id has to be passed
	 * Outcome: it will display customer in the database 
	 *If CustomerId is null,then InvalidCustomerException is thrown,
	 *If CustomerId is not found,then CustomerNotFoundException is thrown.
	 */
	@GetMapping("/view/{id}")
	public CustomerDetails viewCustomer(@PathVariable @NotBlank String id) {


		return customerUtil.customerDetails(customerService.viewCustomer(id));
	}

	/**
	 * Case :  view-all customer
	 * Outcome: all the customers in the database will be displayed 
	 */
	@GetMapping("/view-all")
	public List<CustomerDetails> viewAllProducts() {

		return customerUtil.customerDetailsList(customerService.viewAllCustomer());
	}
}
