package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface UserRepositery extends JpaRepository<User, Integer> {

//	returning the element using email
	User findUByEmail(String email);
}
