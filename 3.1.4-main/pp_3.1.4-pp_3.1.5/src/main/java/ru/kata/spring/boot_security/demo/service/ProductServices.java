package ru.kata.spring.boot_security.demo.service;


import com.google.zxing.WriterException;
import ru.kata.spring.boot_security.demo.model.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductServices {
	
	void addProduct(Product product);
	
	void updateProduct(Product product);
	
	void deleteProduct(Product product);
	
	List<Product> getProductByArticleWb(String articleWb);
	
	List<Product> getProductByName(String name);
	
	List<Product> getProductByPrice(BigDecimal price1, BigDecimal price2);
	
	List<Product> getProductByCategory(List<String> category);
	
	List<Product> getProductByBrand(List<String> brand);
	
	List<Product> getProductByColor(List<String> color);
	
	List<Product> getProductBySize(List<String> size);
	
	byte[] printBarcodes(List<Product> products, List<String> fieldsToPrint) throws IOException, WriterException;
	
	
}
