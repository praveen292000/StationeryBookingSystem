package com.cg.sbs.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.cg.sbs.dto.orders.OrderDetailsResponseDTO;
import com.cg.sbs.dto.orders.OrderUpdateRequest;
import com.cg.sbs.dto.orders.OrderRequest;
import com.cg.sbs.entity.Orders;
import com.cg.sbs.service.IOrderService;
import com.cg.sbs.util.Orders_Util;
import com.cg.sbs.util.Date_Util;

@Validated
@RequestMapping("/orders")
@RestController
public class Orders_Controller {

	@Autowired
	private IOrderService orderService;

	@Autowired
	private Orders_Util orderUtil;

	@Autowired
	private Date_Util dateUtil;

	@GetMapping("/get/{id}")
	public OrderDetailsResponseDTO fetchOrderDetails(@PathVariable("id") @NotNull(message="Order Id cannot be null") int id) {
		Orders order = orderService.viewOrder(id);
		return orderUtil.toDetails(order);
	}


	@PutMapping("/update")
	public OrderDetailsResponseDTO OrderUpdate(@RequestBody @Valid OrderUpdateRequest requestData) {
		Orders order = orderService.viewOrder(requestData.getOrderId());
		order.setTotalCost(requestData.getTotalCost());
		order.setTotalProducts(requestData.getTotalProducts());
		Orders updatedOrder = orderService.updateOrders(order);
		return orderUtil.toDetails(updatedOrder);
	}

	@GetMapping("/getByDate/{startDate}/{endDate}")
	public List<OrderDetailsResponseDTO> fetchOrderDetailsByDate(@PathVariable @NotBlank(message="startDate cannot be null")String startDate,@PathVariable @NotBlank(message="endDate cannot be null") String endDate) {
		LocalDate start = dateUtil.toLocalDate(startDate);
		LocalDate end = dateUtil.toLocalDate(endDate);
		List<Orders> order = orderService.viewOrders(start, end);
		return orderUtil.toDetailsList(order);
	}

	@DeleteMapping("/delete")
	public OrderDetailsResponseDTO deleteOrder(@RequestBody @Valid OrderRequest requestData) {
		Orders order = orderService.viewOrder(requestData.getOrderId());
		Orders deletedOrder = orderService.removeOrders(order);
		return orderUtil.toDetails(deletedOrder);
	}

	@GetMapping("/viewbycustomer/{id}")
	public List<OrderDetailsResponseDTO> viewOrder(@PathVariable @NotBlank(message="Order Id cannot be null") String id) {
		List<Orders> order = orderService.viewOrders(id);
		return orderUtil.toDetailsList(order);
	}

	@GetMapping("/totalcost")
	public double getTotalCost(@RequestBody @Valid OrderRequest requestData) {
		Orders order = orderService.viewOrder(requestData.getOrderId());
		return orderService.totalCost(order);
	}


}