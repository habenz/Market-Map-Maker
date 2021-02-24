package com.m3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name = "map")
@SecondaryTable(name = "category", pkJoinColumns = @PrimaryKeyJoinColumn(name = "map_id"))
public class MarketMap {
	
	//@Column(name="id")
	@Id
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(table="category", name="category_name")
	private String categoryName;

	public MarketMap(long id, String name, String categoryName) {
		this.id = id;
		this.name = name;
		this.categoryName = categoryName;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String toString() {
		return String.format("Map[ id: %d, name: %s, category:%s", this.id, this.name, this.categoryName);
	}
}
