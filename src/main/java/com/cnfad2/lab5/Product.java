package com.cnfad2.lab5;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Product {
	private Long id;
	@NotBlank(message = "Name is required") 
	private String name;
	@NotNull(message = "Price is required") 
	private Double price;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name; 
	}
	public Double getPrice() { 
		return price;
	}
	public void setPrice(Double price) {
		this.price = price; 
	}
}
