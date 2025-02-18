package ru.kata.spring.boot_security.demo.service;

import com.google.zxing.WriterException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Order;
import ru.kata.spring.boot_security.demo.repositories.OrderRepository;
import ru.kata.spring.boot_security.demo.util.PngToPdfConverter;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class OrderServicesImpl implements OrderServices {
	private OrderRepository orderRepository;
	private PngToPdfConverter pngToPdfConverter;
	
	public OrderServicesImpl(OrderRepository orderRepository, PngToPdfConverter pngToPdfConverter) {
		this.orderRepository = orderRepository;
		this.pngToPdfConverter = pngToPdfConverter;
	}
	
	
	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}
	
	@Override
	@Transactional
	public void deleteOrder(Order order) {
		orderRepository.delete(order);
	}
	
	@Override
	public List<Order> getOrderByStatus(Enum status) {
		List<Order> orders = orderRepository.findAll();
		List<Order> result = new ArrayList<>();
		
		for (Order order : orders) {
			if (order.getStatus().equals(status)) {
				result.add(order);
			}
		}
		
		return result;
	}
	
	@Override
	public byte[] getSticker(List<Long> orderIds, String type, int width, int height) throws IOException, WriterException {
		
		return null;
	}
}
