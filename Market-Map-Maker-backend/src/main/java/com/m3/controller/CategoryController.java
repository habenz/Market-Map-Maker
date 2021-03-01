package com.m3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m3.entity.Category;
import com.m3.entity.Company;
import com.m3.repository.CategoryRepository;
import com.m3.repository.CompanyRepository;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
		
		@Autowired
		private CategoryRepository categoryRepository;
		
		@Autowired
		private CompanyRepository companyRepository;
		
		@GetMapping("/all")
		public List<Category> getAllCategories() {
			return this.categoryRepository.findAll();
		}
		
		@GetMapping("/{categoryId}")
		public Category getCategoryById(@PathVariable long categoryId) {
			return this.categoryRepository.findById(categoryId).orElse(null);
		}
		
		@GetMapping("/category-companies/{categoryId}")
		public List<Company> getAllCompaniesFromCategory(@PathVariable Long categoryId){
			return companyRepository.findByCategory_Id(categoryId);
		}
		
		@PostMapping("/addTo/{categoryId}")
		public Company addNewCompanyToCategory(@PathVariable Long categoryId, @RequestBody Company company) {
			Category savedCategory = this.categoryRepository.findById(categoryId).get();
			company.setCategory(savedCategory);
			return this.companyRepository.save(company);
		}

//		@PostMapping("/addManyTo/{categoryId}")
//		public List<Company> addNewCategoriesToMap(@PathVariable Long categoryId, @RequestBody List<Company> companies) {
//			Category savedCategory = this.categoryRepository.findById(categoryId).get();
//			List<Company> createdCompanies = new ArrayList<Company>();
//			for(Company company: companies) {
//				company.setCategory(savedCategory);
//				createdCompanies.add(this.companyRepository.save(company));
//			}
//			return createdCompanies;
//		}
}
