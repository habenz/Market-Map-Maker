package com.m3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "map")
public class MarketMap {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String name;
    
	public MarketMap(String name) {
		this.name = name;
	}
	
	public MarketMap() {
	}

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

//	public List<Category> getCategory() {
//		return category;
//	}
//
//	public void setCategory(List<Category> category) {
//		this.category = category;
//	}

	public String toString() {
		
//		return String.format("Map[ id: %d, name: %s, categories: %s]", this.id, this.name, this.category.toString());
		return String.format("Map[ id: %d, name: %s]", this.id, this.name);

	}
}
