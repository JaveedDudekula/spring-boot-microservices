package com.learn.microservices.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.learn.microservices.product.dto.ProductRequest;
import com.learn.microservices.product.dto.ProductResponse;
import com.learn.microservices.product.exceptions.ProductNotFoundException;
import com.learn.microservices.product.model.Product;
import com.learn.microservices.product.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ProductResponse createProduct(ProductRequest productRequest) {
		Product product = new Product(productRequest.getName(), productRequest.getDescription(),
				productRequest.getPrice());
		productRepository.save(product);
		log.info("Product created successfully");
		return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
	}

	public List<ProductResponse> getAllProducts() {
		return productRepository.findAll().stream().map(product -> new ProductResponse(product.getId(),
				product.getName(), product.getDescription(), product.getPrice())).collect(Collectors.toList());
	}

	public ProductResponse getProductById(String id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			return new ProductResponse(product.getId(), product.getName(), product.getDescription(),
					product.getPrice());
		} else {
			log.error("Product not found with id: " + id);
			throw new ProductNotFoundException("Product not found with id: " + id);
		}
	}

	public void updateProductById(String id, ProductRequest productRequest) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			Product existingProduct = optionalProduct.get();
			existingProduct.setName(productRequest.getName());
			existingProduct.setDescription(productRequest.getDescription());
			existingProduct.setPrice(productRequest.getPrice());
			productRepository.save(existingProduct);
			log.info("Product updated successfully with id: " + id);
		} else {
			log.error("Product not found with id: " + id);
			throw new ProductNotFoundException("Product not found with id: " + id);
		}
	}

	public void deleteProductById(String id) {
		if (productRepository.existsById(id)) {
			productRepository.deleteById(id);
			log.info("Product updated successfully with id: " + id);
		} else {
			log.error("Product not found with id: " + id);
			throw new ProductNotFoundException("Product not found with id: " + id);
		}
	}
}
