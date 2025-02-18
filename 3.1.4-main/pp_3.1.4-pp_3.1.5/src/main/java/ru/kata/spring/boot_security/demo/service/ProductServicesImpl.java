package ru.kata.spring.boot_security.demo.service;


import com.google.zxing.WriterException;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Product;
import ru.kata.spring.boot_security.demo.repositories.ProductRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ProductServicesImpl implements ProductServices {
	private ProductRepository productRepository;
	
	
	public ProductServicesImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
		
	}
	
	@Override
	@Transactional
	public void addProduct(Product product) {
		productRepository.save(product);
	}
	
	@Override
	public void updateProduct(Product product) {
		productRepository.save(product);
	}
	
	@Override
	public void deleteProduct(Product product) {
		productRepository.save(product);
	}
	
	@Override
	public List<Product> getProductByArticleWb(String articleWb) {
		List<Product> products = productRepository.findAll();
		String regex = ".*" + Pattern.quote(articleWb) + ".*";
		
		return products.stream().filter(product -> product.getName().matches(regex)).collect(Collectors.toList());
	}
	
	@Override
	public List<Product> getProductByName(String name) {
		List<Product> products = productRepository.findAll();
		String regex = ".*" + Pattern.quote(name) + ".*";
		
		return products.stream().filter(product -> product.getName().matches(regex)).collect(Collectors.toList());
	}
	
	@Override
	public List<Product> getProductByPrice(BigDecimal price1, BigDecimal price2) {
		List<Product> products = productRepository.findAll();
		
		return products.stream().filter(product -> product.getPrice().compareTo(price1) >= 0 && product.getPrice().compareTo(price2) <= 0).collect(Collectors.toList());
	}
	
	@Override
	public List<Product> getProductByCategory(List<String> category) {
		if (category == null || category.isEmpty()) return Collections.emptyList();
		List<Product> products = productRepository.findAll();
		
		return products.stream()
				.filter(product -> category.stream().anyMatch(c -> product.getCategory().contains(c)))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Product> getProductByBrand(List<String> brand) {
		if (brand == null || brand.isEmpty()) return Collections.emptyList();
		List<Product> products = productRepository.findAll();
		
		return products.stream()
				.filter(product -> brand.stream().anyMatch(b -> product.getBrand().contains(b)))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Product> getProductByColor(List<String> color) {
		if (color == null || color.isEmpty()) return Collections.emptyList();
		List<Product> products = productRepository.findAll();
		
		return products.stream()
				.filter(product -> color.stream().anyMatch(c -> product.getColor().contains(c)))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Product> getProductBySize(List<String> size) {
		if (size == null || size.isEmpty()) return Collections.emptyList();
		List<Product> products = productRepository.findAll();
		
		return products.stream()
				.filter(product -> size.stream().anyMatch(s -> product.getSizes().contains(s)))
				.collect(Collectors.toList());
	}
	
	@Override
	public byte[] printBarcodes(List<Product> products, List<String> fieldsToPrint) throws IOException, WriterException {
		return null;
	}
	
}
