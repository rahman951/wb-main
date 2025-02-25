package ru.kata.spring.boot_security.demo.service;

import com.google.zxing.WriterException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Product;
import ru.kata.spring.boot_security.demo.model.ProductFilter;
import ru.kata.spring.boot_security.demo.repositories.ProductRepository;
import ru.kata.spring.boot_security.demo.util.BarcodeGenerator;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServicesImpl implements ProductServices {
	private final ProductRepository productRepository;
	private final BarcodeGenerator barcodeGenerator;

	public ProductServicesImpl(ProductRepository productRepository, BarcodeGenerator barcodeGenerator) {
		this.productRepository = productRepository;
		this.barcodeGenerator = barcodeGenerator;
	}

	@Override
	@Transactional
	public void addProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	@Transactional
	public void updateProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	@Transactional
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public byte[] generateBarcode(Long productId) throws IOException, WriterException {
		Optional<Product> productOpt = productRepository.findById(productId);
		if (productOpt.isPresent()) {
			// Здесь используется, например, поле articleWb как значение штрих-кода
			return barcodeGenerator.generateBarcodePng(Collections.singletonList(productOpt.get().getArticleWb()));
		}
		throw new IllegalArgumentException("Product not found");
	}

	@Override
	public List<Product> findByCategory(String category) {
		return productRepository.findAll().stream()
				.filter(product -> product.getCategory() != null && product.getCategory().equalsIgnoreCase(category))
				.collect(Collectors.toList());
	}

	@Override
	public List<Product> findByBrand(String brand) {
		return productRepository.findAll().stream()
				.filter(product -> product.getBrand() != null && product.getBrand().equalsIgnoreCase(brand))
				.collect(Collectors.toList());
	}

	@Override
	public List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
		return productRepository.findAll().stream()
				.filter(product -> product.getPrice() != null &&
						product.getPrice().compareTo(minPrice) >= 0 &&
						product.getPrice().compareTo(maxPrice) <= 0)
				.collect(Collectors.toList());
	}

	@Override
	public List<Product> findByColor(String color) {
		return productRepository.findAll().stream()
				.filter(product -> product.getColor() != null && product.getColor().equalsIgnoreCase(color))
				.collect(Collectors.toList());
	}

	@Override
	public List<Product> findBySize(String size) {
		return productRepository.findAll().stream()
				.filter(product -> product.getSizes() != null && product.getSizes().contains(size))
				.collect(Collectors.toList());
	}

	@Override
	public List<Product> findByFilters(ProductFilter filter) {
		return productRepository.findAll().stream()
				.filter(product -> {
					boolean matches = true;
					if (filter.getCategory() != null && !filter.getCategory().isEmpty()) {
						matches &= product.getCategory() != null &&
								product.getCategory().equalsIgnoreCase(filter.getCategory());
					}
					if (filter.getBrand() != null && !filter.getBrand().isEmpty()) {
						matches &= product.getBrand() != null &&
								product.getBrand().equalsIgnoreCase(filter.getBrand());
					}
					if (filter.getMinPrice() != null) {
						matches &= product.getPrice() != null &&
								product.getPrice().compareTo(filter.getMinPrice()) >= 0;
					}
					if (filter.getMaxPrice() != null) {
						matches &= product.getPrice() != null &&
								product.getPrice().compareTo(filter.getMaxPrice()) <= 0;
					}
					if (filter.getColor() != null && !filter.getColor().isEmpty()) {
						matches &= product.getColor() != null &&
								product.getColor().equalsIgnoreCase(filter.getColor());
					}
					if (filter.getSize() != null && !filter.getSize().isEmpty()) {
						matches &= product.getSizes() != null && product.getSizes().contains(filter.getSize());
					}
					return matches;
				})
				.collect(Collectors.toList());
	}
}
