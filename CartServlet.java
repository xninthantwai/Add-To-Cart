package com.jdc.scopes;

import java.io.IOException;

import com.jdc.scopes.model.ProductManager;
import com.jdc.scopes.model.ShoppingCart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({
	"/cart",
	"/cart/add",
	"/cart/remove",
	"/cart/clear",
})
public class CartServlet extends HttpServlet{

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
		
		ShoppingCart cart = null;
		var session = req.getSession(true);
		
		if(session.getAttribute("cart") instanceof ShoppingCart sc) {
			cart = sc;
		}
		
		if(null == cart) {
			cart = new ShoppingCart();
			session.setAttribute("cart", cart);
		}
		
		var view = switch(req.getServletPath()) {
		case "/cart/add" -> {
			var productId = req.getParameter("productId");
			var product = productManager.findById(Integer.parseInt(productId));
			cart.add(product);
			var source = req.getParameter("source");
			yield null != source && !source.isBlank() ? source : "/cart.jsp";
		}
		case "/cart/remove" -> {
			var productId = req.getParameter("productId");
			cart.remove(Integer.parseInt(productId));
			yield "/cart.jsp";
		}
		case "/cart/clear" -> {
			cart.clear();
			yield "/index.jsp";
		}
		default -> "/cart.jsp";
		};
		
		getServletContext().getRequestDispatcher(view).forward(req, resp);
	}
}