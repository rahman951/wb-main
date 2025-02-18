package ru.kata.spring.boot_security.demo.model;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "analytics")
public class Analytics {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	@Column(name = "views_count", nullable = false)
	private Integer viewsCount;
	
	@Column(name = "sales_count", nullable = false)
	private Integer salesCount;
	
	@Column(name = "average_rating")
	private Float averageRating;
	
	@Column(name = "revenue", nullable = false)
	private BigDecimal revenue;
	
	@Column(name = "date", nullable = false)
	private LocalDate date;
	
	public Analytics(Product product, Integer viewsCount, Integer salesCount, Float averageRating, BigDecimal revenue, LocalDate date) {
		this.product = product;
		this.viewsCount = viewsCount;
		this.salesCount = salesCount;
		this.averageRating = averageRating;
		this.revenue = revenue;
		this.date = date;
	}
	
	public Analytics() {
	}
	
	// Getters and Setters
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Integer getViewsCount() {
		return viewsCount;
	}
	
	public void setViewsCount(Integer viewsCount) {
		this.viewsCount = viewsCount;
	}
	
	public Integer getSalesCount() {
		return salesCount;
	}
	
	public void setSalesCount(Integer salesCount) {
		this.salesCount = salesCount;
	}
	
	public Float getAverageRating() {
		return averageRating;
	}
	
	public void setAverageRating(Float averageRating) {
		this.averageRating = averageRating;
	}
	
	public BigDecimal getRevenue() {
		return revenue;
	}
	
	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
}
