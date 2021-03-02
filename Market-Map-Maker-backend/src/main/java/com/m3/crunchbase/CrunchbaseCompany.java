package com.m3.crunchbase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CrunchbaseCompany {
	
	private String uuid;
	private CrunchbaseCompanyProperties properties;
	
	public CrunchbaseCompany() {
		
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public CrunchbaseCompanyProperties getProperties() {
		return properties;
	}

	public void setProperties(CrunchbaseCompanyProperties properties) {
		this.properties = properties;
	}
	
	@Override
	public String toString() {
		return String.format("{uuid:%s, properties:{%s}}", uuid, properties.toString());
	}
	
	
}
