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
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_food")
public class Food {

	@Id
	@Column(name = "food_id")
	private int food_id;

	@Column(name = "food_name")
	private String food_name;

	@Column(name = "price")
	private int price;

	@Column(name = "description")
	private String description;

	public Food(int food_id, String food_name, int price, String description) {
		super();
		this.food_id = food_id;
		this.food_name = food_name;
		this.price = price;
		this.description = description;
	}

	public Food(String food_name, int price, String description) {
		super();
		this.food_name = food_name;
		this.price = price;
		this.description = description;
	}

	public Food() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getFood_id() {
		return food_id;
	}

	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}

	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Food [food_id=" + food_id + ", food_name=" + food_name + ", price=" + price + ", description="
				+ description + "]";
	}

}
