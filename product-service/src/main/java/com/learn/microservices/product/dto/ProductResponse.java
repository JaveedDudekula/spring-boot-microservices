package com.learn.microservices.product.dto;

import java.math.BigDecimal;

public class ProductResponse {

	private String id;

	public String name;

	public String description;

	public BigDecimal price;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public ProductResponse(String id, String name, String description, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductResponse [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ "]";
	}
}
