package com.learn.microservices.order.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(OrderNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleOrderNotFoundException(OrderNotFoundException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(ProductNotInStockException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleProductNotInStockException(ProductNotInStockException ex) {
		return ex.getMessage();
	}
}
