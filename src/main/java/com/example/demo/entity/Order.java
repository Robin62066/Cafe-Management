package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int order_id;

	@Column(name = "order_date")
	private LocalDate order_date;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "food_id")
	private Food food;

	public Order(int order_id, LocalDate order_date, User user, Food food) {
		super();
		this.order_id = order_id;
		this.order_date = order_date;
		this.user = user;
		this.food = food;
	}

	public Order(LocalDate order_date, User user, Food food) {
		super();
		this.order_date = order_date;
		this.user = user;
		this.food = food;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public LocalDate getOrder_date() {
		return order_date;
	}

	public void setOrder_date(LocalDate order_date) {
		this.order_date = order_date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", order_date=" + order_date + ", user=" + user + ", food=" + food + "]";
	}

}