package com.mushopl.service;

import com.mushopl.entity.Category;
import com.mushopl.entity.Product;
import com.mushopl.repo.CategoryRepository;
import com.mushopl.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

/**
 * Created by anaida on 1/28/16.
 */
@Service
public class ProductService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	/**
	 * Returns list of all categories
	 *
	 * @return list of all categories
	 */
	public List<Category> getAllProductCategories() {
		return categoryRepository.findAll();
	}

	/**
	 * Return product if it is available in store
	 *
	 * @param id product id
	 * @return product by id
	 */
	public Optional<Product> getProductById(@Nonnull Long id) {
		return productRepository.findOneByIdAndQuantityGreaterThan(id, 0);
	}

	/**
	 * Returns list of products for given category if they are available in store
	 *
	 * @param categoryId category id
	 * @return list of products
	 */
	public List<Product> getProductsByCategoryId(@Nullable Long categoryId) {
		return categoryId == null ? productRepository.findByQuantityGreaterThan(0) : productRepository
				.findByCategoryIdAndQuantityGreaterThan(categoryId, 0);
	}

	/**
	 * Returns list of products if they are available in store
	 *
	 * @return list of products
	 */
	public List<Product> getProducts() {
		return productRepository.findByQuantityGreaterThan(0);
	}
}
