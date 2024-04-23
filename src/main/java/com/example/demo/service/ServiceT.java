package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Table1;
import com.example.demo.entity.User;
import com.example.demo.model.Login;

import jakarta.persistence.Table;

public interface ServiceT {
	Table1 getchkTId(User user);

	List<Table1> findAllT();

	Table1 deleteT(int i);

	boolean tUserAuthentication(List table);

	Table1 saveTable(Table1 table);
}
