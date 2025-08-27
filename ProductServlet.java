package com.jdc.scopes;

import java.io.IOException;

import com.jdc.scopes.model.ProductManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private ProductManager productManager;
	
	@Override
	public void init() throws ServletException {
		if(getServletContext().getAttribute(ProductManager.KEY) instanceof ProductManager pm) {
			this.productManager = pm;
		}
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var keyword = req.getParameter("keyword");
		var products = productManager.search(keyword);
		req.setAttribute("products", products);
		
		getServletContext().getRequestDispatcher("/index.jsp")
			.forward(req, resp);
	}
}