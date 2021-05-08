package com.cg.sbs.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.sbs.entity.Customer;

/*
 *
 * Author : G V V Nagendra Prasad
 * Description : This is CustomerRepository 
 *
*/

public interface ICustomerRepository extends JpaRepository<Customer, String> {
	Customer findCustomerByCustomerId(String customerId);
}
