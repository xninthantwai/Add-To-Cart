package com.jdc.scopes.model;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.jdc.scopes.model.dto.Product;

public class ProductManager {
	
	public static final String KEY = "productManager";
	
	private Map<Integer, Product> products;
	
	public ProductManager(String path) {
		try (var stream = Files.lines(Path.of(path))){
			
			products = stream.map(line -> line.split("\t"))
				.map(Product::new)
				.collect(Collectors.toMap(Product::getId, Function.identity()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Product> getProducts() {
		return new ArrayList<>(products.values());
	}

	public List<Product> search(String keyword) {
		return products.values().stream()
				.filter(a -> a.getName().toLowerCase().startsWith(keyword.toLowerCase())
						|| a.getCategory().toLowerCase().startsWith(keyword.toLowerCase()))
				.toList();
	}
	
	public Product findById(int id) {
		return products.get(id);
	}
	
}