package com.cnfad2.lab5;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.*;
@RestController 
@RequestMapping("/api/products")
public class ProductController {
	private final List<Product> products = new ArrayList<>();
	
	@PostMapping
	public ResponseEntity<?> addProduct(@Valid @RequestBody Product product, BindingResult result) {
		if (result.hasErrors()) {
			List<String> errors = new ArrayList<>(); 
			result.getFieldErrors().forEach(error -> errors.add(error.getField() + ": " +error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errors);
		}
		product.setId((long) (products.size() + 1));
		products.add(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable Long id, @Valid @RequestBody Product updatedProduct, BindingResult result) {
		if (result.hasErrors()) {
			List<String> errors = new ArrayList<>(); result.getFieldErrors().forEach(error -> errors.add(error.getField() + ": " +
					error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errors);
		}
		Product productToUpdate = products.stream() .filter(p -> p.getId().equals(id)) .findFirst()
				.orElse(null);
		if (productToUpdate != null) { 
			productToUpdate.setName(updatedProduct.getName()); productToUpdate.setPrice(updatedProduct.getPrice()); return ResponseEntity.ok(productToUpdate);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
		} 
	}
}
