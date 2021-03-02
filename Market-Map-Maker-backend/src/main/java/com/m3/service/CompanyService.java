package com.m3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.m3.crunchbase.CrunchbaseCompany;
import com.m3.crunchbase.CrunchbaseCompanyProperties;
import com.m3.crunchbase.CrunchbaseSearchResponse;
import com.m3.entity.Company;
import com.m3.resources.ApiResources;


@Service
public class CompanyService {

	//@Autowired
	public CompanyService() {
		// TODO Auto-generated constructor stub
	}
	
	public Company fillCompanyDetails(Company company) {
        String url = ApiResources.CRUNCHBASE_SEARCH_URL_BASE + 
        		"name="+company.getName();
        RestTemplate restTemplate = new RestTemplate();
        CrunchbaseSearchResponse response  = null;
        response = restTemplate.getForObject(url, CrunchbaseSearchResponse.class);
        
        List<CrunchbaseCompany> possibleMatches = response.getData().getItems();
        if (possibleMatches.size() == 1) {
        	CrunchbaseCompanyProperties details = possibleMatches.get(0).getProperties();
        	extractInfoIntoCompany(company, details);
        } else if (possibleMatches.size() > 1) {
        	int matchIndex = tryFindExactNameMatch(possibleMatches, company.getName());
        	extractInfoIntoCompany(company, possibleMatches.get(matchIndex).getProperties());
        } 
        // numPossibleMatches == 0
		return company;
	}
	
	private void extractInfoIntoCompany(Company company, CrunchbaseCompanyProperties details) {
    	company.setDescription(details.getShort_description());
    	company.setLogoUrl(details.getProfile_image_url());
	}
	
	private int tryFindExactNameMatch(List<CrunchbaseCompany> possibleMatches, String name) {
		for(int i =0; i< possibleMatches.size(); i++) {
			String checkAgainstName = possibleMatches.get(i).getProperties().getName().toLowerCase();
			if(name.toLowerCase() == checkAgainstName) {
				System.out.println("found exact match!");
				return i;
			}
		}
		return 0;
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
