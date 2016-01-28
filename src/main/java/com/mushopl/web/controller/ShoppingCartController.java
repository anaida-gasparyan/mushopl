package com.mushopl.web.controller;

/**
 * Created by anaida on 1/23/16.
 */

import com.mushopl.entity.Order;
import com.mushopl.entity.Product;
import com.mushopl.service.OrderService;
import com.mushopl.service.ProductService;
import com.mushopl.web.controller.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by anaida on 1/28/16.
 * <p>
 * This controller is responsible for shopping cart requests
 * Methods in controller are secured to be accessible only for current user
 */
@Controller
@RequestMapping(value = "/{username:[\\w]+}")
public class ShoppingCartController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	/**
	 * Returns appropriate view and data for user shopping cart view
	 * Model contains the following attributes:
	 * {@code orders} - list of orders
	 * {@code totalPrice} - shopping cart total order price
	 *
	 * @param username current user username
	 * @param model    model for current view
	 * @return shopping cart view
	 */
	@PreAuthorize("#username == principal.username")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String shoppingCart(@PathVariable("username") String username, Model model) {
		List<Order> orders = orderService.getOrdersByUser(username);
		double sum = orders.stream().mapToDouble(o -> o.getProduct().getQuantity() * o.getQuantity()).sum();
		model.addAttribute("orders", orders);
		model.addAttribute("totalPrice", sum);

		return "user/shoppingcart";
	}

	/**
	 * Adds product order to shopping cart
	 *
	 * @param username current user username
	 * @param id       product id to add to cart
	 * @param session  http session
	 * @return redirect to shopping cart
	 */
	@PreAuthorize("#username == principal.username")
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public String addToShoppingCart(@PathVariable("username") String username, @PathVariable("id") Long id,
	                                HttpSession session) {
		Product product = productService.getProductById(id).orElseThrow(ProductNotFoundException::new);
		orderService.makeOrder(username, product);

		// TODO this part is duplicated think another solution
		session.setAttribute("orderQty", orderService.getItemCount(username));

		return "redirect:/" + username;
	}

	/**
	 * If user tries to access another user's shopping card via url access denied is thrown
	 *
	 * @return access denied view
	 */
	@ExceptionHandler(value = AccessDeniedException.class)
	public String accessDenied() {
		return "error/accessdenied";
	}
}