package com.m3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
//	@Column(name="map_id")
//	private long mapId;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "map_id"), name = "map_id")
    private MarketMap map;
	
	public Category(long id, String name) {
		this.id = id;
		this.name = name;
	}
	public Category() {
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
	
	public MarketMap getMap() {
		return map;
	}
	public void setMap(MarketMap map) {
		this.map = map;
	}
	@Override
	public String toString() {
		return String.format("Category:{name:%s, id:%s} in Map: %s", this.name, this.id, this.map.toString());
	}
}
