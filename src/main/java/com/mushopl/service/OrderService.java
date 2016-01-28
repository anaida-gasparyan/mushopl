package com.mushopl.service;

import com.mushopl.entity.Order;
import com.mushopl.entity.Product;
import com.mushopl.entity.security.User;
import com.mushopl.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by anaida on 1/28/16.
 * <p>
 * All methods are secured only for principal user
 */
@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	/**
	 * Return list of orders for current user
	 *
	 * @param username user username
	 * @return list of orders for current user
	 */
	@PreAuthorize("#username == principal.username")
	public List<Order> getOrdersByUser(@Nonnull String username) {
		return orderRepository.findByUserUsername(username);
	}

	/**
	 * Returns total count of user order items
	 *
	 * @param username user username
	 * @return total count of user order items
	 */
	@PreAuthorize("#username == principal.username")
	public int getItemCount(@Nonnull String username) {
		return getOrdersByUser(username).stream().mapToInt(Order::getQuantity).reduce(0, (x, y) -> x + y);
	}

	/**
	 * Adds given product to users orders. Returns new order or updated order if the product was already added. In
	 * that case just updates order product quantity
	 *
	 * @param username user username
	 * @param product  product to add to order list
	 * @return added or updated order
	 */
	@PreAuthorize("#username == principal.username")
	@Transactional
	public Order makeOrder(@Nonnull String username, @Nonnull Product product) {
		Order order = orderRepository.findOneByUserUsernameAndProductId(username, product.getId()).orElseGet(() -> new
				Order(new User(username), product, 0));
		order.setQuantity(order.getQuantity() + 1); // update quantity
		return orderRepository.save(order);
	}
}
