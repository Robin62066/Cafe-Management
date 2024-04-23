package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Table1;
import com.example.demo.entity.User;
import com.example.demo.model.Login;
import com.example.demo.repository.Table1Repository;
import com.example.demo.repository.UserRepositery;
import com.example.demo.service.ServiceT;

import jakarta.persistence.Table;

@Service
public class ServiceTImpl implements ServiceT {

	@Autowired
	Table1Repository trepo;

	@Autowired
	UserRepositery repo;

	public ServiceTImpl(UserRepositery repo) {
		super();
		this.repo = repo;
	}

	public ServiceTImpl(Table1Repository trepo) {
		super();
		this.trepo = trepo;
	}

	public ServiceTImpl() {

	}

	@Override
	public Table1 getchkTId(User user) {
		Table1 table = new Table1();
		return null;

	}

	@Override
	public List<Table1> findAllT() {
		return trepo.findAll();
	}

	@Override
	public boolean tUserAuthentication(List l) {
		Table1 t = new Table1();
		System.out.println(l);
		User user = t.getUser();
		l = (List) user;
		System.out.println(l);

		return false;

	}

	@Override
	public Table1 saveTable(Table1 table) {

		return trepo.save(table);
	}

	@Override
	public Table1 deleteT(int i) {
		Table1 t = new Table1();
		trepo.deleteById(i);
		return t;

	}

}
