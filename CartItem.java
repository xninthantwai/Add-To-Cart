package com.jdc.scopes.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CartItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int productId;
	private String name;
	private String category;
	private int unitPrice;
	private int quantity;

	public CartItem(Product product) {
		this.productId = product.getId();
		this.name = product.getName();
		this.category = product.getCategory();
		this.unitPrice = product.getPrice();
	}
	
	public int getTotal() {
		return unitPrice * quantity;
	}

	public void countUp() {
		++ this.quantity;
	}

}