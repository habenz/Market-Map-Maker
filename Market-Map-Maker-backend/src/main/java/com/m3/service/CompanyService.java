package com.m3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.m3.crunchbase.CrunchbaseSearchResponse;
import com.m3.entity.Company;
import com.m3.resources.ApiResources;


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
	
	public void testCrunchbaseAPI() {
		System.out.println("calling testCrunchbaseAPI method");
        String url = ApiResources.CRUNCHBASE_SEARCH_URL_BASE + "name=Hoppier";
        RestTemplate restTemplate = new RestTemplate();
        CrunchbaseSearchResponse response  = null;
        response = restTemplate.getForObject(url, CrunchbaseSearchResponse.class);
        
        System.out.println(response);
        System.out.println(response.getData().getPaging().getTotal_items());
	}


}
