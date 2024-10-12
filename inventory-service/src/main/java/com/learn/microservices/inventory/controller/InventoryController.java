package com.learn.microservices.inventory.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.learn.microservices.inventory.dto.InventoryRequest;
import com.learn.microservices.inventory.dto.InventoryResponse;
import com.learn.microservices.inventory.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	private final InventoryService inventoryService;

	public InventoryController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
		return inventoryService.isInStock(skuCode, quantity);
	}

	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	public List<InventoryResponse> getAllItemsFromInventory() {
		return inventoryService.getAllItemsFromInventory();
	}

	@GetMapping("/item/{id}")
	@ResponseStatus(HttpStatus.OK)
	public InventoryResponse getItemById(@PathVariable Long id) {
		return inventoryService.findItemById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String addToStock(@RequestBody InventoryRequest inventoryRequest) {
		inventoryService.addToStock(inventoryRequest);
		return "Item added to inventory successfully";
	}

	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String updateItem(@PathVariable Long id, @RequestBody InventoryRequest inventoryRequest) {
		inventoryService.updateItemById(id, inventoryRequest);
		return "Item updated successfully with id: " + id;
	}

	@DeleteMapping("/remove/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteItemById(@PathVariable Long id) {
		inventoryService.deleteItemById(id);
		return "Item deleted successfully with id: " + id;
	}

}
