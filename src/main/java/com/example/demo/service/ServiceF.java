package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Food;

public interface ServiceF {
	List<Food> findAllF();

	Food getFId(int i);

	public Food saveF(Food food);

	boolean deleteF(int i);
}
