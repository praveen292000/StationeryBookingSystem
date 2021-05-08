package com.cg.sbs.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.sbs.entity.Orders;
import com.cg.sbs.entity.Booking;

public interface IOrdersRepository extends JpaRepository<Orders, Integer> {

	List<Orders> findByBooking(Booking booking);

	Orders findOrdersByBooking(Booking booking);

	@Query("from Orders where orderDate between :startDate and :endDate")
	List<Orders> findOrdersBetweenDates(@Param("startDate") LocalDateTime startDate,
			@Param("endDate") LocalDateTime endDate);
}
