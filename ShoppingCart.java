package com.jdc.scopes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.jdc.scopes.model.dto.CartItem;
import com.jdc.scopes.model.dto.Product;

public class ShoppingCart implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Map<Integer, CartItem> itemMap;
	
	public ShoppingCart() {
		itemMap = new LinkedHashMap<>();
	}

	public void add(Product product) {

		var item = itemMap.get(product.getId());
		if(null == item) {
			item = new CartItem(product);
			itemMap.put(product.getId(), item);
		}
		
		item.countUp();
	}

	public void remove(int productId) {
		itemMap.remove(productId);
	}

	public void clear() {
		itemMap.clear();
	}
	
	public List<CartItem> getItems() {
		return new ArrayList<>(itemMap.values());
	}
	
	public int getTotalCount() {
		return itemMap.values().stream().mapToInt(CartItem::getQuantity)
				.sum();
	}
	
	public int getTotalAmount() {
		return itemMap.values().stream().mapToInt(CartItem::getTotal)
				.sum();
	}

}