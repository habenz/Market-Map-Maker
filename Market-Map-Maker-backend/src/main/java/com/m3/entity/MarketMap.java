package com.m3.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "map")
//@SecondaryTable(name = "category", pkJoinColumns = @PrimaryKeyJoinColumn(name = "map_id"))
public class MarketMap {
	
	//@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="map_id")
//    private List<Category> category;
  private List<Category> category = new ArrayList<Category>();
    
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

	public List<Category> getCategories() {
		return category;
	}

	public void setCategories(List<Category> categories) {
		this.category = categories;
	}

	public String toString() {
		return String.format("Map[ id: %d, name: %s]", this.id, this.name);
	}
}
