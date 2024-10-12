package com.learn.microservices.order.exceptions;

public class ProductNotInStockException extends RuntimeException {

	public ProductNotInStockException(String message) {
		super(message);
	}
}
