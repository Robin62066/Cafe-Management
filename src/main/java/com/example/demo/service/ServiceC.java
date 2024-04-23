package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Cart;

public interface ServiceC {
	
	Cart saveAllC(Cart cart);

	public Cart getCId(int i);

	List<Cart> getAllC(Cart cart);
}
