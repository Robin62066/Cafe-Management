package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.ServiceC;

@Service
public class ServiceCImpl implements ServiceC {

	@Autowired
	CartRepository crepo;

	public ServiceCImpl(CartRepository crepo) {
		super();
		this.crepo = crepo;
	}

	
	//return the list as all the cart elements 
	@Override
	public List<Cart> getAllC(Cart cart) {

		return crepo.findAll();

	}

	
	@Override
	public Cart saveAllC(Cart cart) {
		return crepo.save(cart);
	}

	@Override
	public Cart getCId(int i) {
		// TODO Auto-generated method stub
		return crepo.findById(i).orElse(null);
	}

}
