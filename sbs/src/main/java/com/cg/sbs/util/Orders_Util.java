package com.cg.sbs.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.sbs.dto.orders.OrderDetailsResponseDTO;
import com.cg.sbs.entity.Orders;

@Component
public class Orders_Util {
	@Autowired
	private Date_Util dateUtil;

	public OrderDetailsResponseDTO toDetails(Orders order) {
		OrderDetailsResponseDTO details = new OrderDetailsResponseDTO();

		details.setOrderId(order.getOrderId());
		details.setOrderStatus(order.getBooking().getBookingStatus());
		String dateText = dateUtil.toText(order.getOrderDate());
		details.setOrderDate(dateText);
		details.setTotalProducts(order.getTotalProducts());
		details.setTotalCost(order.getTotalCost());
		return details;
	}

	public Orders getOrders() {
		return new Orders();
	}

	public List<OrderDetailsResponseDTO> toDetailsList(List<Orders> orders) {
		List<OrderDetailsResponseDTO> od = new ArrayList<>();
		for (Orders order : orders) {
			od.add(toDetails(order));
		}
		return od;
	}

}
