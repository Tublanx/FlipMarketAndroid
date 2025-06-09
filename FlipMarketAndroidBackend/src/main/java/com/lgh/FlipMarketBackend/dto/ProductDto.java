package com.lgh.FlipMarketBackend.dto;

public class ProductDto {
	private Long num;

	private String productName;

	private int price;

	private String category;

	private int stock;

	private String description;

	private String imagePath;

	private int likeCount = 0;

	public ProductDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductDto(Long num, String productName, int price, String category, int stock, String description,
			String imagePath, int likeCount) {
		this.num = num;
		this.productName = productName;
		this.price = price;
		this.category = category;
		this.stock = stock;
		this.description = description;
		this.imagePath = imagePath;
		this.likeCount = likeCount;
	}

	public Long getNum() {
		return num;
	}

	public String getProductName() {
		return productName;
	}

	public int getPrice() {
		return price;
	}

	public String getCategory() {
		return category;
	}

	public int getStock() {
		return stock;
	}

	public String getDescription() {
		return description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public int getLikeCount() {
		return likeCount;
	}
}
