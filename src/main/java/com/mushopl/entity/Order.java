package com.mushopl.entity;

import com.mushopl.entity.security.User;

import javax.persistence.*;

/**
 * Created by anaida on 1/28/16.
 */
@Entity
@Table(name = "orders", uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "username"})})
public class Order {

	@Id
	@Basic(optional = false)
	@Column(name = "order_id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Basic(optional = false)
	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@Basic(optional = false)
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	private Product product;

	@Basic(optional = false)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "username", referencedColumnName = "username")
	private User user;

	public Order() {
	}

	public Order(User user, Product product, Integer quantity) {
		this.user = user;
		this.product = product;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
