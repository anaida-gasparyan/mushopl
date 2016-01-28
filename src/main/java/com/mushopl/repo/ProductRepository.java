package com.mushopl.repo;

import com.mushopl.entity.Product;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;
import java.util.Optional;

/**
 * Created by anaida on 1/27/16.
 */
@RepositoryDefinition(domainClass = Product.class, idClass = Long.class)
public interface ProductRepository {

	List<Product> getByCategoryId(Long categoryId);

	List<Product> findAll();

	Optional<Product> findOne(Long id);

	Optional<Product> findOneByIdAndQuantityGreaterThan(Long id, Integer quantity);

	List<Product> findByQuantityGreaterThan(Integer quantity);

	List<Product> findByCategoryIdAndQuantityGreaterThan(Long categoryId, Integer quantity);

	Product save(Product product);
}