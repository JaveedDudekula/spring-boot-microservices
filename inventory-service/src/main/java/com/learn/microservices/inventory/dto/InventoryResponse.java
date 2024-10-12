package com.learn.microservices.inventory.dto;

public class InventoryResponse {

	private Long id;
	
	private String skuCode;
	
	private Integer quantity;

	public InventoryResponse(Long id, String skuCode, Integer quantity) {
		this.id = id;
		this.skuCode = skuCode;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		return "InventoryResponse [id=" + id + ", skuCode=" + skuCode + ", quantity=" + quantity + "]";
	}

}
