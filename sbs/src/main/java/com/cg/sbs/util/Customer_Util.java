package com.cg.sbs.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cg.sbs.dto.customer.CustomerDetails;
import com.cg.sbs.entity.Customer;


/*
 *
 * Author : G V V Nagendra Prasad
 * Description : This is Customer util
 *
*/

@Component
public class Customer_Util {
	public Customer getCustomer() {
		return new Customer();
	}

	
	public CustomerDetails customerDetails(Customer customer) {
		
		CustomerDetails details= new CustomerDetails();
	    details.setFirstName(customer.getFirstName());
	    details.setLastName(customer.getLastName());
	    details.setGender(customer.getGender());
	    return details;
	}
	public List<CustomerDetails> customerDetailsList(List<Customer> list){
		List<CustomerDetails> cd=new ArrayList<>();
		for(Customer customer:list) {
			cd.add(customerDetails(customer));
		}
		return cd;
	}
}
