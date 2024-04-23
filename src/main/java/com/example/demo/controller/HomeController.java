package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.User;
import com.example.demo.model.Login;

import com.example.demo.service.impl.ServiceImplU;
import com.example.demo.service.impl.ServiceTImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	ServiceImplU serv;

	@Autowired
	ServiceTImpl serv2;

	@GetMapping({ "/" })
	public String home() {
		return "home";
	}

	@GetMapping({ "/login" })
	public String login(Model model) {
		Login l = new Login();
		model.addAttribute("logino", l);
		return "login";
	}

	@GetMapping({ "/signUp" })
	public String signup() {
		return "signup";
	}

	@PostMapping({ "/signUp/saveUser" })
	public String saveUser(@ModelAttribute User user, Model model, HttpSession session) {
		System.out.println(user);
		User u = serv.saveUser(user);
		if (u != null) {
			session.setAttribute("msg", "Successfully registered");
		} else {
			session.setAttribute("msg", "Something went wrong");
		}
		return "redirect:/signUp";
	}

	@GetMapping({ "/success" })
	public String foodChk1() {
		return "success";
	}

}
