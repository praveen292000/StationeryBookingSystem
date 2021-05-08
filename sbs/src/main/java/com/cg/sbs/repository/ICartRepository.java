package com.cg.sbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.sbs.entity.Cart;
import com.cg.sbs.entity.Customer;

public interface ICartRepository extends JpaRepository<Cart, String>{
	
	Cart findCartByCustomer(Customer customer);

}
