package com.cg.sbs.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.sbs.entity.Booking;
import com.cg.sbs.entity.Cart;

public interface IBookingRepository extends JpaRepository<Booking, Integer> {

	Booking findBookingDetailsByCart(Cart cart);

	List<Booking> findByCart(Cart cart);

}
