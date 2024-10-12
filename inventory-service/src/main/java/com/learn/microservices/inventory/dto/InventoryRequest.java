package com.learn.microservices.inventory.dto;

public class InventoryRequest {

	private String skuCode;
	
	private Integer quantity;

	public InventoryRequest(String skuCode, Integer quantity) {
		this.skuCode = skuCode;
		this.quantity = quantity;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "InventoryRequest [skuCode=" + skuCode + ", quantity=" + quantity + "]";
	}
	
}
