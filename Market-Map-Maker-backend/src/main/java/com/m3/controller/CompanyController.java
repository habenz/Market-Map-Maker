package com.m3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m3.entity.Company;
import com.m3.repository.CompanyRepository;
import com.m3.service.CompanyService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
	
	@GetMapping("/{companyId}")
	public Company getCompany(@PathVariable Long companyId) {
		return this.companyRepository.findById(companyId).orElse(null);
	}
	
	@GetMapping("/test")
	public void test() {
		companyService.testCrunchbaseAPI();
	}

	// Both fill details endpoints could potentially be patch requests
	
	@GetMapping("/fill-details/{companyId}")
	public Company fillCompanyDetails(@PathVariable Long companyId) {
		Company savedCompany = this.companyRepository.findById(companyId).orElse(null);
		companyService.fillCompanyDetails(savedCompany);
		return this.companyRepository.save(savedCompany);
	}
	
	@PostMapping("/fill-many-details")
	public ResponseEntity fillManyCompanyDetails(@RequestBody List<Long> companyIds) {
		for(Long companyId : companyIds) {
			Company savedCompany = this.companyRepository.findById(companyId).orElse(null);
			companyService.fillCompanyDetails(savedCompany);
			this.companyRepository.save(savedCompany);
		}
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/remove/{companyId}")
	public Company removeCompany(@PathVariable Long companyId) {
		Company savedCompany = this.companyRepository.findById(companyId).orElse(null);
		this.companyRepository.delete(savedCompany);
		return savedCompany;
	}
	
}
