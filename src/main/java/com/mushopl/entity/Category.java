package com.mushopl.entity;

import javax.persistence.*;

/**
 * Created by anaida on 1/27/16.
 */
@Entity
@Table(name = "categories", uniqueConstraints = {@UniqueConstraint(columnNames = {"category_name"})})
public class Category {

	@Id
	@Basic(optional = false)
	@Column(name = "category_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Basic(optional = false)
	@Column(name = "category_name", nullable = false, unique = true)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
