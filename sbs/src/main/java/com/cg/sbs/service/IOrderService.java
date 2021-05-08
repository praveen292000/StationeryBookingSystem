package com.cg.sbs.service;
import java.time.LocalDate;
import java.util.List;

import com.cg.sbs.entity.Orders;

public interface IOrderService {

	Orders addOrders(Orders order);
	
	Orders updateOrders(Orders order);
	
	Orders removeOrders(Orders order);
	
	Orders viewOrder(int orderId);
	
	List<Orders> viewOrders(LocalDate startDate,LocalDate endDate);
	
	List<Orders> viewOrders(String custId);
	
	double totalCost(Orders order);
}
