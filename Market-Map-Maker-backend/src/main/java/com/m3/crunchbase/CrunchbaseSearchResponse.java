package com.m3.crunchbase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CrunchbaseSearchResponse {
	
	private CrunchbaseData data;
	public CrunchbaseSearchResponse() {
		// TODO Auto-generated constructor stub
	}
	public CrunchbaseData getData() {
		return data;
	}
	public void setData(CrunchbaseData data) {
		this.data = data;
	}
	
	@Override
	public String toString(){
		return data.toString();
	}
	

}
