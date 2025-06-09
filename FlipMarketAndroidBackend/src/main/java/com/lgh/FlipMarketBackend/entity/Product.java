package com.lgh.FlipMarketBackend.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long num;
	
	@Column(nullable = false)
	private String productName;
	
	@Column(nullable = false)
	private String category;
	
	@Column(nullable = false)
	private int price;
	
	@Column(nullable = false)
	private String description;
	
	private String imagePath;
	
	@Column(nullable = false)
	private int stock;
	
	private int likeCount;
	
	@ManyToOne
	@JoinColumn(name = "user_num", referencedColumnName = "num")
	private User user;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String productName, String category, int price, String description, String imagePath, int stock,
			int likeCount, User user) {
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.description = description;
		this.imagePath = imagePath;
		this.stock = stock;
		this.likeCount = likeCount;
		this.user = user;
	}
	
	public Long getNum() {
		return num;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public String getCategory() {
		return category;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public int getStock() {
		return stock;
	}
	
	public int getLikeCount() {
		return likeCount;
	}
	
	public User getUser() {
		return user;
	}
	
}
