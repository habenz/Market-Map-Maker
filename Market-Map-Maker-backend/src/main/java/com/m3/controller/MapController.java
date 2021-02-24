package com.m3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m3.entity.MarketMap;
import com.m3.repository.MapRepository;

@RestController
@RequestMapping("/api/map")
public class MapController {
	
	@Autowired
	private MapRepository mapRepository;
	
	@GetMapping
	public List<MarketMap> getAllMaps() {
		return this.mapRepository.findAll();
	}
	
	@PostMapping
	public MarketMap createMap(@RequestBody MarketMap map) {
		System.out.println("in post");
		System.out.println(map);
		return this.mapRepository.save(map);
	}
}
