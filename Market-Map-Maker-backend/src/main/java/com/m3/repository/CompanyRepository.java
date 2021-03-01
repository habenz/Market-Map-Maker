package com.m3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.m3.entity.Category;
import com.m3.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
	List<Company> findByCategory_Id(long categoryId);
}

