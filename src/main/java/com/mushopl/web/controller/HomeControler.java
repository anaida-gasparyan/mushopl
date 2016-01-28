package com.mushopl.web.controller;

import com.mushopl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * Created by anaida on 1/28/16.
 * <p>
 * Controller for misc requests
 */
@Controller
public class HomeControler {

	@Autowired
	private OrderService orderService;

	/**
	 * Redirects to product view as default view
	 *
	 * @param session   http session
	 * @param principal authenticated user
	 * @return redirect to products view
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpSession session, Principal principal) {
		session.setAttribute("orderQty", orderService.getItemCount(principal.getName()));
		return "redirect:/products";
	}

	/**
	 * Returns login view. This view is default for unauthenticated users
	 *
	 * @return login view
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	/**
	 * Access denied exception handler
	 *
	 * @return access denied view
	 */
	@ExceptionHandler(value = AccessDeniedException.class)
	public String accessDenied() {
		return "error/accessdenied";
	}

	/**
	 * Global exception handler
	 *
	 * @return error view
	 */
	@ExceptionHandler(value = Exception.class)
	public String globalException() {
		return "error";
	}
}
