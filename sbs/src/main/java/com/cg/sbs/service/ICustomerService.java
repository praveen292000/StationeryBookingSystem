package com.cg.sbs.service;
import java.util.List;

import com.cg.sbs.entity.Customer;


/*
 *
 * Author : G V V Nagendra Prasad
 * Description : This is Customer service interface
 *
*/


public interface ICustomerService {

	 Customer addCustomer(Customer customer);
	
	 Customer updateCustomer(Customer customer);
	 
	 Customer removeCustomer(Customer customer);
	 
	 Customer viewCustomer(String id);
	 
	 List<Customer> viewAllCustomer();
}
