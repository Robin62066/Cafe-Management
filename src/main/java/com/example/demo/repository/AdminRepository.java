package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	// mtlb isme find likhna hi h
	
	Admin findEverythingByEmail(String email);

}
