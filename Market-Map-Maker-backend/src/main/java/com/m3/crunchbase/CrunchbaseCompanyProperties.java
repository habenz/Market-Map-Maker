package com.m3.crunchbase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CrunchbaseCompanyProperties {
	
	private String name;
	private String short_description;
	private String profile_image_url;
	
	public CrunchbaseCompanyProperties() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShort_description() {
		return short_description;
	}

	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}

	public String getProfile_image_url() {
		return profile_image_url;
	}

	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}
	
	@Override
	public String toString() {
		return String.format("name:%s, desc: %s, logo: %s", name, short_description, profile_image_url);
	}
}
