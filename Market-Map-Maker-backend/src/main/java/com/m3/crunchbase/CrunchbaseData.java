package com.m3.crunchbase;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CrunchbaseData {
	
	private PagingInfo paging;
	private List<CrunchbaseCompany> items;

	public CrunchbaseData() {
		// TODO Auto-generated constructor stub
	}

	public PagingInfo getPaging() {
		return paging;
	}

	public void setPaging(PagingInfo paging) {
		this.paging = paging;
	}

	public List<CrunchbaseCompany> getItems() {
		return items;
	}

	public void setItems(List<CrunchbaseCompany> items) {
		this.items = items;
	}
	
	@Override
	public String toString(){
		return String.format("CrunchbaseData: %s", items.toString());
	}

}
