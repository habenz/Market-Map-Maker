package com.m3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m3.entity.Company;
import com.m3.repository.CompanyRepository;
import com.m3.service.CompanyService;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/all")
	public List<Company> getAllCompanies() {
		return this.companyRepository.findAll();
	}
	
	@GetMapping("/test")
	public void test() {
		companyService.testCrunchbaseAPI();
	}

//	@GetMapping("/test/{companyId}")
//	public Company filledOutCompany(@PathVariable Long companyId) {
//		Company savedCompany = this.companyRepository.findById(companyId).orElse(null);
//		companyService.testCrunchbaseAPI();
//		return null;
//	}
}
