package ru.kata.spring.boot_security.demo.service;


import com.google.zxing.WriterException;
import ru.kata.spring.boot_security.demo.model.Product;
import ru.kata.spring.boot_security.demo.model.ProductFilter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductServices {

	void addProduct(Product product);
	void updateProduct(Product product);
	void deleteProduct(Long id);
	Optional<Product> findById(Long id);
	List<Product> getAllProducts();
	byte[] generateBarcode(Long productId) throws IOException, WriterException;

	// Методы для фильтрации товаров
	List<Product> findByCategory(String category);
	List<Product> findByBrand(String brand);
	List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
	List<Product> findByColor(String color);
	List<Product> findBySize(String size);

	// Можно добавить метод для комбинированного поиска с фильтрами
	List<Product> findByFilters(ProductFilter filter);


}
