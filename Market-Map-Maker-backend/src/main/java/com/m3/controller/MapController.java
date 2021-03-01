package com.m3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
	
	//TO DO: Add exceptions to account for non existent Id's
	
	@Autowired
	private MapRepository mapRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/all")
	public List<MarketMap> getAllMaps() {
		return this.mapRepository.findAll();
	}
	
	@GetMapping("/{mapId}")
	public MarketMap getMapById(@PathVariable Long mapId){
		return this.mapRepository.findById(mapId).orElse(null);
	}
	
	@GetMapping("/map-categories/{mapId}")
	public List<Category> getAllCategoriesFromMap(@PathVariable Long mapId){
		return categoryRepository.findByMap_Id(mapId);
	}
	
	@PostMapping("/new")
	public MarketMap createMap(@RequestBody MarketMap map) {
		return this.mapRepository.save(map);
	}
	
	@PostMapping("/addTo/{mapId}")
	public Category addNewCategoryToMap(@PathVariable Long mapId, @RequestBody Category category) {
		MarketMap savedMap = this.mapRepository.findById(mapId).get();
		category.setMap(savedMap);
		return this.categoryRepository.save(category);
	}

	@PostMapping("/addManyTo/{mapId}")
	public List<Category> addNewCategoriesToMap(@PathVariable Long mapId, @RequestBody List<Category> categories) {
		MarketMap savedMap = this.mapRepository.findById(mapId).get();
		List<Category> createdCategories = new ArrayList<Category>();
		for(Category category: categories) {
			category.setMap(savedMap);
			createdCategories.add(this.categoryRepository.save(category));
		}
		return createdCategories;
	}
	
	@PatchMapping("/rename/{mapId}")
	public MarketMap renameMap(@PathVariable Long mapId, @RequestBody String newName) {
		//Request Body being a naked String might be an issue for axios
		// need to throw if map is null
		MarketMap savedMap = this.mapRepository.findById(mapId).get();
		savedMap.setName(newName);
		return this.mapRepository.save(savedMap);
	}
	
	@DeleteMapping("/remove/{mapId}")
	public MarketMap deleteMap(@PathVariable Long mapId) {
		// There's definitely a way to do this by cascading changes
		// need to throw if map is null
		MarketMap savedMap = this.mapRepository.findById(mapId).orElse(null);
		List<Category> categoriesToDelete =  categoryRepository.findByMap_Id(mapId);
		for(Category category: categoriesToDelete) {
			this.categoryRepository.delete(category);
		}
		this.mapRepository.deleteById(mapId);
		return savedMap;
	}

}
