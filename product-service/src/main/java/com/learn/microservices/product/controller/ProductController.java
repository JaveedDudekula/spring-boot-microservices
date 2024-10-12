package com.learn.microservices.product.controller;

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

import com.learn.microservices.product.dto.ProductRequest;
import com.learn.microservices.product.dto.ProductResponse;
import com.learn.microservices.product.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
		return productService.createProduct(productRequest);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProductResponse getProductById(@PathVariable String id) {
		return productService.getProductById(id);
	}

	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String updateProductById(@PathVariable String id, @RequestBody ProductRequest productRequest) {
		productService.updateProductById(id, productRequest);
		return "Product updated successfully with id: " + id;
	}

	@DeleteMapping("/remove/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteProductById(@PathVariable String id) {
		productService.deleteProductById(id);
		return "Product deleted successfully with id: " + id;
	}
}
