package com.learn.microservices.order.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.learn.microservices.order.client.InventoryClient;
import com.learn.microservices.order.dto.OrderRequest;
import com.learn.microservices.order.dto.OrderResponse;
import com.learn.microservices.order.exceptions.OrderNotFoundException;
import com.learn.microservices.order.exceptions.ProductNotInStockException;
import com.learn.microservices.order.model.Order;
import com.learn.microservices.order.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {

	private final OrderRepository orderRepository;

	private final InventoryClient inventoryClient;

	public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient) {
		this.orderRepository = orderRepository;
		this.inventoryClient = inventoryClient;
	}

	public void placeOrder(OrderRequest orderRequest) {

		var isProductInStock = inventoryClient.isInStock(orderRequest.getSkuCode(), orderRequest.getQuantity());
		if (isProductInStock) {
			Order order = new Order();
			order.setOrderNumber(UUID.randomUUID().toString());
			order.setSkuCode(orderRequest.getSkuCode());
			order.setPrice(orderRequest.getPrice());
			order.setQuantity(orderRequest.getQuantity());
			orderRepository.save(order);
			log.info("Order placed successfully");
		} else {
			log.error("Product with SkuCode " + orderRequest.getSkuCode() + " is not in stock");
			throw new ProductNotInStockException(
					"Product with SkuCode " + orderRequest.getSkuCode() + " is not in stock");
		}
	}

	public List<OrderResponse> getAllOrders() {
		return orderRepository.findAll().stream().map(order -> new OrderResponse(order.getId(), order.getOrderNumber(),
				order.getSkuCode(), order.getPrice(), order.getQuantity())).collect(Collectors.toList());
	}

	public OrderResponse getOrderById(Long id) {
		Optional<Order> optionalOrder = orderRepository.findById(id);
		if (optionalOrder.isPresent()) {
			Order order = optionalOrder.get();
			return new OrderResponse(order.getId(), order.getOrderNumber(), order.getSkuCode(), order.getPrice(),
					order.getQuantity());
		} else {
			log.error("Order not found with id: " + id);
			throw new OrderNotFoundException("Order not found with id: " + id);
		}
	}

	public void updateOrderById(Long id, OrderRequest orderRequest) {
		Optional<Order> optionalOrder = orderRepository.findById(id);
		if (optionalOrder.isPresent()) {
			Order existingOrder = optionalOrder.get();
			existingOrder.setSkuCode(orderRequest.getSkuCode());
			existingOrder.setPrice(orderRequest.getPrice());
			existingOrder.setQuantity(orderRequest.getQuantity());
			orderRepository.save(existingOrder);
			log.info("Order updated successfully with id: " + id);
		} else {
			log.error("Order not found with id: " + id);
			throw new OrderNotFoundException("Order not found with id: " + id);
		}
	}

	public void deleteOrderById(Long id) {
		if (orderRepository.existsById(id)) {
			orderRepository.deleteById(id);
			log.info("Order deleted successfully with id: " + id);
		} else {
			log.error("Order not found with id: " + id);
			throw new OrderNotFoundException("Order not found with id: " + id);
		}
	}
}
