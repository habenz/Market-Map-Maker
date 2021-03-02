package com.m3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.m3.entity.Company;

@Service
public class CompanyService {

	//@Autowired
	public CompanyService() {
		// TODO Auto-generated constructor stub
	}
	
	public Company fillDetails(Company company) {
		return company;
	}
	
	public void testAPI() {
		System.out.println("calling testAPI method");
        String url = "http://api.open-notify.org/astros.json";
        RestTemplate restTemplate = new RestTemplate();
        SpaceAPIResponse response  = null;
        response = restTemplate.getForObject(url, SpaceAPIResponse.class);
        System.out.println(response);
	}


}
