package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Food;
import com.example.demo.repository.FoodRepository;
import com.example.demo.service.ServiceF;

@Service
public class ServiceImplF implements ServiceF {

	@Autowired
	FoodRepository frepo;

	@Override
	public List<Food> findAllF() {
		// TODO Auto-generated method stub
		return frepo.findAll();
	}

	@Override
	public Food getFId(int i) {
		System.out.println();
		return frepo.getById(i);
	}

	@Override
	public Food saveF(Food food) {

		return frepo.save(food);
	}

	@Override
	public boolean deleteF(int i) {
		if (frepo.findById(i) != null) {
			frepo.deleteById(i);
			return true;
		}
		return false;
	}

}
