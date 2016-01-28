package com.mushopl.web.controller;

import com.mushopl.service.ProductService;
import com.mushopl.web.controller.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by anaida on 1/28/16.
 * <p>
 * This controller is responsible for product related requests
 */
@Controller
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * Returns appropriate view and data for one product detail
	 * Model contains the following attributes:
	 * {@code product} - current product model
	 *
	 * @param id    current product id
	 * @param model model for current view
	 * @return product detail view
	 * @throws ProductNotFoundException when product with given id is not found
	 */
	@RequestMapping(value = {"/{id}", "/{id}/details"}, method = RequestMethod.GET)
	public String productDetails(@PathVariable("id") Long id, Model model) {
		model.addAttribute("product", productService.getProductById(id).orElseThrow(ProductNotFoundException::new));

		return "products/details";
	}

	/**
	 * Returns appropriate view and data for product list
	 * Model contains the following attributes:
	 * {@code products} - list of all products
	 * {@code categories} - list of all categories
	 *
	 * @param model model for current view
	 * @return product list view
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String products(Model model) {
		model.addAttribute("products", productService.getProducts());
		model.addAttribute("categories", productService.getAllProductCategories());

		return "products/list";
	}

	/**
	 * Returns appropriate view fragment and data (can be filtered by category) for product list
	 * Model contains the following attributes:
	 * {@code products} - list of all products or filterer by {@code categoryId} request parameter
	 *
	 * @param categoryId category id for filtering products
	 * @param model      model for current view
	 * @return product list view fragment
	 */
	@RequestMapping(value = "/filter", method = RequestMethod.GET)
	public String productsByCategory(@RequestParam(value = "category", required = true) Long categoryId, Model model) {
		model.addAttribute("products", productService.getProductsByCategoryId(categoryId));

		return "fragments/productlist :: list";
	}
}
