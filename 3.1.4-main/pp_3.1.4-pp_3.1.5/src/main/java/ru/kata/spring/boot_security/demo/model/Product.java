package ru.kata.spring.boot_security.demo.model;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	private String brand;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	private String category;
	
	private String description;
	
	private String image;
	
	@Column(name = "article_wb")
	private String articleWb;
	
	@Column(name = "article_seller")
	private String articleSeller;
	
	@ElementCollection
	private List<String> barcodes;
	
	@ElementCollection
	private List<String> sizes;
	
	private int costPrice;
	
	private String color;
	
	@ManyToOne
	@JoinColumn(name = "seller_id", nullable = false)  // внешний ключ, связывающий продукт с продавцом
	private User seller;
	
	public Product(String name, String brand, BigDecimal price, String category, String description, String articleWb, String articleSeller, List<String> barcodes, List<String> sizes, String color, User seller, int costPrice) {
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.category = category;
		this.description = description;
		this.articleWb = articleWb;
		this.articleSeller = articleSeller;
		this.barcodes = barcodes;
		this.sizes = sizes;
		this.color = color;
		this.seller = seller;
		this.costPrice = costPrice;
	}
	
	public Product() {
	}
	
	// Getters and Setters
	
	public int getCostPrice() {
		return costPrice;
	}
	
	public void setCostPrice(int costPrice) {
		this.costPrice = costPrice;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getArticleWb() {
		return articleWb;
	}
	
	public void setArticleWb(String articleWb) {
		this.articleWb = articleWb;
	}
	
	public String getArticleSeller() {
		return articleSeller;
	}
	
	public void setArticleSeller(String articleSeller) {
		this.articleSeller = articleSeller;
	}
	
	public List<String> getBarcodes() {
		return barcodes;
	}
	
	public void setBarcodes(List<String> barcodes) {
		this.barcodes = barcodes;
	}
	
	public List<String> getSizes() {
		return sizes;
	}
	
	public void setSizes(List<String> sizes) {
		this.sizes = sizes;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public User getSeller() {
		return seller;
	}
	
	public void setSeller(User seller) {
		this.seller = seller;
	}
}
