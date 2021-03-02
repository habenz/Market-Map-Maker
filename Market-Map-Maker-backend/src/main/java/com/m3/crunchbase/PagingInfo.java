package com.m3.crunchbase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PagingInfo {
	
	private int total_items;
	
	public PagingInfo() {
		
	}

	public int getTotal_items() {
		return total_items;
	}

	public void setTotal_items(int total_items) {
		this.total_items = total_items;
	}
	
	

}
