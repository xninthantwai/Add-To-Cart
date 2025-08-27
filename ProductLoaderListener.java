package com.jdc.scopes.listener;

import com.jdc.scopes.model.ProductManager;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ProductLoaderListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		var context = sce.getServletContext();
		var path = context.getRealPath("/WEB-INF/data/products.txt");
		
		var productManager = new ProductManager(path);
		context.setAttribute(ProductManager.KEY, productManager);
		context.setAttribute("products", productManager.getProducts());
	}
}