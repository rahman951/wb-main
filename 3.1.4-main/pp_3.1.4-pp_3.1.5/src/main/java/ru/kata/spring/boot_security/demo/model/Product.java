package ru.kata.spring.boot_security.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false)
	private String name;

	private String color;

	public Product(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

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

	private int costPrice; // Себестоимость
	private int quantitySold; // Количество продаж
	private float averageRating; // Средний рейтинг товара
	private BigDecimal competitorPrice; // Цена конкурента

	@Column(name = "last_updated")
	private LocalDateTime lastUpdated; // Дата обновления данных

	@ManyToOne
	@JoinColumn(name = "seller_id", nullable = false)
	private User seller;

	public Product() {

	}

	@PreUpdate
	@PrePersist
	public void updateTimestamp() {
		this.lastUpdated = LocalDateTime.now();
	}

	public Product(String name, String brand, BigDecimal price, String category, String description, String image, String articleWb, String articleSeller, List<String> barcodes, List<String> sizes, int costPrice, int quantitySold, float averageRating, BigDecimal competitorPrice, User seller, LocalDateTime lastUpdated) {
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.category = category;
		this.description = description;
		this.image = image;
		this.articleWb = articleWb;
		this.articleSeller = articleSeller;
		this.barcodes = barcodes;
		this.sizes = sizes;
		this.costPrice = costPrice;
		this.quantitySold = quantitySold;
		this.averageRating = averageRating;
		this.competitorPrice = competitorPrice;
		this.seller = seller;
		this.lastUpdated = lastUpdated;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public int getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(int costPrice) {
		this.costPrice = costPrice;
	}

	public int getQuantitySold() {
		return quantitySold;
	}

	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}

	public float getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(float averageRating) {
		this.averageRating = averageRating;
	}

	public BigDecimal getCompetitorPrice() {
		return competitorPrice;
	}

	public void setCompetitorPrice(BigDecimal competitorPrice) {
		this.competitorPrice = competitorPrice;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
}
