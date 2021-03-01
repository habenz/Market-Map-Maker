package com.m3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="company")
public class Company {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(foreignKey= @ForeignKey(name="category_id"), name="category_id")
	private Category category;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="logo_url")
	private String logoUrl;
	
	@Column(name="product")
	private String productName;

	@Column(name="product_description")
	private String productDescription;
	
	@Column(name="product_logo_url")
	private String productLogoUrl;
	
	public Company() {
		// TODO Auto-generated constructor stub
	}
	
	public Company(String name, String description, String logoUrl, String productName, String productDescription, String productLogoUrl) {
		this.name = name;
		this.description = description;
		this.logoUrl = logoUrl;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productLogoUrl = productLogoUrl;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductLogoUrl() {
		return productLogoUrl;
	}

	public void setProductLogoUrl(String productLogoUrl) {
		this.productLogoUrl = productLogoUrl;
	}

}
