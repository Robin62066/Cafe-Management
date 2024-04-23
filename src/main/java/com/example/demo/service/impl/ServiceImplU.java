package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import com.example.demo.model.Login;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.UserRepositery;
import com.example.demo.service.ServiceU;

@Service
public class ServiceImplU implements ServiceU {

	@Autowired
	private AdminRepository arepo;

	@Autowired
	private UserRepositery repo;

	public ServiceImplU(UserRepositery repo) {
		super();
		this.repo = repo;
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return repo.save(user);
	}

	// find krega email se validate krega password ko
	@Override
	public boolean userAuthentication(Login login) {

		User user = repo.findUByEmail(login.getEmail());
		if (user == null) {
			return false;
		}
		return user.getPassword().equals(login.getPassword());
	}

	@Override
	public boolean adminAuthentication(Login login) {
		Admin admin = arepo.findEverythingByEmail(login.getEmail());
		if (admin == null) {
			return false;
		}
		return admin.getPassword().equals(login.getPassword());
	}

	@Override
	public List<User> findAllU() {
		return repo.findAll();
	}

	@Override
	public List<Admin> findAllA() {

		return arepo.findAll();
	}

	@Override
	public User findUEmail(String str) {
		// TODO Auto-generated method stub
		User user = new User();
		return repo.findUByEmail(str);

	}

	@Override
	public User findId(int i) {
		System.out.println(repo.findById(i));
		return repo.findById(i).orElse(null);
	}

}
