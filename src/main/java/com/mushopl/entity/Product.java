package com.mushopl.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by anaida on 1/27/16.
 */
@Entity
@Table(name = "products")
public class Product implements Serializable {

	@Id
	@Basic(optional = false)
	@Column(name = "product_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Basic(optional = false)
	@Column(name = "product_name", nullable = false)
	private String name;

	@Basic(optional = false)
	@Column(name = "description", nullable = false, length = 1000)
	private String description;

	@Basic(optional = false)
	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@Basic(optional = false)
	@Column(name = "price", nullable = false, precision = 2, scale = 10)
	private Double price;

	@Basic(optional = true)
	@Column(name = "old_price", precision = 2, scale = 10)
	private Double oldPrice;

	@Basic(optional = false)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	private Category category;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(Double oldPrice) {
		this.oldPrice = oldPrice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
