package com.learn.microservices.order.dto;

import java.math.BigDecimal;

public class OrderRequest {
	
	private String skuCode;

	private BigDecimal price;

	private Integer quantity;

	public OrderRequest(String skuCode, BigDecimal price, Integer quantity) {
		this.skuCode = skuCode;
		this.price = price;
		this.quantity = quantity;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderRequest [skuCode=" + skuCode + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}

}
