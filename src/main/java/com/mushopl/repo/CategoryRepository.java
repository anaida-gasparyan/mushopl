package com.mushopl.repo;

import com.mushopl.entity.Category;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

/**
 * Created by anaida on 1/27/16.
 */
@RepositoryDefinition(domainClass = Category.class, idClass = Long.class)
public interface CategoryRepository {

	List<Category> findAll();
}
