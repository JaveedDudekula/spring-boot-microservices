package com.learn.microservices.order.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.learn.microservices.order.dto.OrderRequest;
import com.learn.microservices.order.dto.OrderResponse;
import com.learn.microservices.order.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String placeOrder(@RequestBody OrderRequest orderRequest) {
		orderService.placeOrder(orderRequest);
		return "Order placed successfully";
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<OrderResponse> getAllOrders() {
		return orderService.getAllOrders();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public OrderResponse getOrderById(@PathVariable Long id) {
		return orderService.getOrderById(id);
	}

	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String updateOrderById(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {
		orderService.updateOrderById(id, orderRequest);
		return "Order updated successfully with id: " + id;
	}

	@DeleteMapping("/remove/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteOrderById(@PathVariable Long id) {
		orderService.deleteOrderById(id);
		return "Order deleted successfully with id: " + id;
	}

}
