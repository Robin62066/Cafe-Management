package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import com.example.demo.model.Login;

public interface ServiceU {

	User saveUser(User user);

	boolean userAuthentication(Login login);

	boolean adminAuthentication(Login login);
	
	
	List<User> findAllU();

	List<Admin> findAllA();

	
	User findUEmail(String str);

	User findId(int i);

}
