package com.learn.microservices.inventory.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.learn.microservices.inventory.dto.InventoryRequest;
import com.learn.microservices.inventory.dto.InventoryResponse;
import com.learn.microservices.inventory.exceptions.ItemNotFoundException;
import com.learn.microservices.inventory.model.Inventory;
import com.learn.microservices.inventory.repository.InventoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventoryService {

	private final InventoryRepository inventoryRepository;

	public InventoryService(InventoryRepository inventoryRepository) {
		this.inventoryRepository = inventoryRepository;
	}

	public boolean isInStock(String skuCode, Integer quantity) {
		return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
	}

	public List<InventoryResponse> getAllItemsFromInventory() {
		return inventoryRepository.findAll().stream()
				.map(item -> new InventoryResponse(item.getId(), item.getSkuCode(), item.getQuantity()))
				.collect(Collectors.toList());
	}

	public InventoryResponse findItemById(Long id) {
		Optional<Inventory> optionalItem = inventoryRepository.findById(id);
		if (optionalItem.isPresent()) {
			Inventory inventoryItem = optionalItem.get();
			return new InventoryResponse(inventoryItem.getId(), inventoryItem.getSkuCode(),
					inventoryItem.getQuantity());
		} else {
			log.error("Item not found with id: " + id);
			throw new ItemNotFoundException("Item not found with id: " + id);
		}
	}

	public void addToStock(InventoryRequest inventoryRequest) {
		Inventory inventory = new Inventory();
		inventory.setSkuCode(inventoryRequest.getSkuCode());
		inventory.setQuantity(inventoryRequest.getQuantity());
		inventoryRepository.save(inventory);
		log.info("Item added to inventory successfully");
	}

	public void updateItemById(Long id, InventoryRequest inventoryRequest) {
		Optional<Inventory> optionalItem = inventoryRepository.findById(id);
		if (optionalItem.isPresent()) {
			Inventory existingItem = optionalItem.get();
			existingItem.setSkuCode(inventoryRequest.getSkuCode());
			existingItem.setQuantity(inventoryRequest.getQuantity());
			inventoryRepository.save(existingItem);
			log.info("Item updated successfully with id: " + id);
		} else {
			log.error("Item not found with id: " + id);
			throw new ItemNotFoundException("Item not found with id: " + id);
		}
	}

	public void deleteItemById(Long id) {
		if (inventoryRepository.existsById(id)) {
			inventoryRepository.deleteById(id);
			log.info("Item deleted successfully with id: " + id);
		} else {
			log.error("Item not found with id: " + id);
			throw new ItemNotFoundException("Item not found with id: " + id);
		}
	}

}
