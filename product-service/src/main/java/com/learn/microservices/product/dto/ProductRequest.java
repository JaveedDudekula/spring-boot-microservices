package com.learn.microservices.product.dto;

import java.math.BigDecimal;

public class ProductRequest {

	public String name;

	public String description;

	public BigDecimal price;
	
	public ProductRequest(String name, String description, BigDecimal price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public ProductRequest() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductRequest [name=" + name + ", description=" + description + ", price=" + price + "]";
	}
}
