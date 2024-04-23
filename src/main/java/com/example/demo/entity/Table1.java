package com.example.demo.entity;

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
@Table(name = "tbl_table")
public class Table1 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "table_id")
	private int tid;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "status")
	private String status;

	public Table1(int tid, User user, String status) {
		super();
		this.tid = tid;
		this.user = user;
		this.status = status;
	}

	public Table1(User user, String status) {
		super();
		this.user = user;
		this.status = status;
	}

	public Table1() {
		super();
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Table1 [tid=" + tid + ", user=" + user + ", status=" + status + "]";
	}

}
