package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private int cartId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "food_id")
	private Food food;

	@Column(name = "quantity")
	private int quantity;

	public Cart(int cartId, User user, int quantity, Food food) {
		super();
		this.cartId = cartId;
		this.user = user;
		this.quantity = quantity;
		this.food = food;
	}

	public Cart(User user, int quantity, Food food) {
		super();
		this.user = user;
		this.quantity = quantity;
		this.food = food;
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", user=" + user + ", quantity=" + quantity + ", food=" + food + "]";
	}

}