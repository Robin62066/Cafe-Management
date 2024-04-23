package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.ServiceO;

@Service
public class ServiceOImpl implements ServiceO {
	OrderRepository orepo;

	public ServiceOImpl(OrderRepository orepo) {
		super();
		this.orepo = orepo;
	}

	@Override
	public Order saveOAll(Order order) {
		return orepo.save(order);
	}

	@Override
	public Order getOId(int i) {
		return orepo.findById(i).orElse(null);
	}

}
