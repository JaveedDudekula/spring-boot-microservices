package com.learn.microservices.order.dto;

import java.math.BigDecimal;

public class OrderResponse {

	private Long id;

	private String orderNumber;

	private String skuCode;

	private BigDecimal price;

	private Integer quantity;

	public OrderResponse(Long id, String orderNumber, String skuCode, BigDecimal price, Integer quantity) {
		this.id = id;
		this.orderNumber = orderNumber;
		this.skuCode = skuCode;
		this.price = price;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		return "OrderResponse [id=" + id + ", orderNumber=" + orderNumber + ", skuCode=" + skuCode + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}
}
