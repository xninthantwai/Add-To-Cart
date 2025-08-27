package com.jdc.scopes.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {

	private int id;
	private String name;
	private String category;
	private int price;
	
	public Product(String [] array) {
		this.id = Integer.parseInt(array[0]);
		this.name = array[1];
		this.category = array[2];
		this.price = Integer.parseInt(array[3]);
	}
}