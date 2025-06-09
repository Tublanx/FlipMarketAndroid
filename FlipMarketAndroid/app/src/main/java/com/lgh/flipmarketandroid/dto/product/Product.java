package com.lgh.flipmarketandroid.dto.product;

public class Product {

    private Long num;

    private String productName;

    private int price;

    private String category;

    private String description;

    private String imagePath;

    private int stock;

    private int likeCount;

    public Product() {}

    public Product(String productName, int price, String imagePath) {
        this.productName = productName;
        this.price = price;
        this.imagePath = imagePath;
    }

    public Product(String productName, int price, String category, String description, String imagePath, int stock, int likeCount) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.description = description;
        this.imagePath = imagePath;
        this.stock = stock;
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
}
