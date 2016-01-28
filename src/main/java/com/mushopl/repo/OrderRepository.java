package com.mushopl.repo;

import com.mushopl.entity.Order;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;
import java.util.Optional;

/**
 * Created by anaida on 1/28/16.
 */
@RepositoryDefinition(domainClass = Order.class, idClass = Long.class)
public interface OrderRepository {

	List<Order> findByUserUsername(String username);

	Optional<Order> findOneByUserUsernameAndProductId(String userName, Long productId);

	Order save(Order order);
}
