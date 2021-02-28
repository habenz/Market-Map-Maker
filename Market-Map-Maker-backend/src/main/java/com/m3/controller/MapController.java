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
import com.m3.entity.MarketMap;
import com.m3.repository.CategoryRepository;
import com.m3.repository.MapRepository;

@RestController
@RequestMapping("/api/map")
public class MapController {
	
	@Autowired
	private MapRepository mapRepository;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@GetMapping
	public List<MarketMap> getAllMaps() {
		return this.mapRepository.findAll();
	}
	
	@GetMapping("/cats")
	public List<Category> getAllCategories() {
		return this.categoryRepo.findAll();
	}
	
	@PostMapping
	public MarketMap createMap(@RequestBody MarketMap map) {
		MarketMap savedMap = this.mapRepository.save(map);
		System.out.println(savedMap);
		return savedMap;
	}
	
	@PostMapping("/addTo/{mapId}")
//	public String createMap(@RequestBody String requestBody) {
	public Category addNewCategoryToMap(@PathVariable Long mapId, @RequestBody Category category) {
		//System.out.println(category);
		MarketMap savedMap = this.mapRepository.findById(mapId).get();
		category.setMap(savedMap);
		Category addedCat = this.categoryRepo.save(category);
		System.out.println(addedCat);
		return addedCat;
	}

	@PostMapping("/addManyTo/{mapId}")
//	public String createMap(@RequestBody String requestBody) {
	public List<Category> addNewCategoriesToMap(@PathVariable Long mapId, @RequestBody List<Category> categories) {
		//System.out.println(category);
		MarketMap savedMap = this.mapRepository.findById(mapId).get();
		List<Category> createdCategories = new ArrayList<Category>();
		for(Category cat: categories) {
			cat.setMap(savedMap);
			createdCategories.add(this.categoryRepo.save(cat));
		}
		return createdCategories;
	}
	
	@GetMapping("/mapCategories/{mapId}")
	public List<Category> getAllCategoriesFromMap(@PathVariable Long mapId){
		return categoryRepo.findByMap_Id(mapId);
	}
}
