package com.m3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.m3.entity.MarketMap;

@Repository
public interface MapRepository extends JpaRepository<MarketMap, Long>{

}
