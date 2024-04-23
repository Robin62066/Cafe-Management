package com.example.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Food;
import com.example.demo.entity.Table1;
import com.example.demo.entity.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.Table1Repository;
import com.example.demo.repository.UserRepositery;
import com.example.demo.service.impl.ServiceImplU;

@SpringBootApplication
public class CafeManagementSystemApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(CafeManagementSystemApplication.class, args);

	}

	@Autowired
	Table1Repository trepo;

	@Autowired
	UserRepositery repo;

	@Autowired
	FoodRepository frepo;

	@Autowired
	ServiceImplU s;

	@Autowired
	CartRepository crepo;

	@Override
	public void run(String... args) throws Exception {
//		System.out.println(frepo.findById(1));

//		Food f=new Food();
//		Food f2=new Food();
//		f=frepo.findById(1).orElse(null);
//		f2=frepo.findById(2).orElse(null);
//		
//		
//		ArrayList<Food> a = new ArrayList<Food>();
//		
//		a.add(f);
//		a.add(f2);
//		
//		ArrayList<Integer> i=new ArrayList<Integer>();
//		for(Food fo:a) {
//			System.out.println(fo.getFood_id());
//			i.add(fo.getFood_id());
//		}
//		
//		
//		
//		User u=new User();
//		u=repo.findById(1).orElse(null);
//
//System.out.println("Food - "+f);
//System.out.println("User - "+u);
//System.out.println("Food list - "+a);
//System.out.println("Food Integer list - "+i);

//		
//		crepo.save(c);

	}
}
