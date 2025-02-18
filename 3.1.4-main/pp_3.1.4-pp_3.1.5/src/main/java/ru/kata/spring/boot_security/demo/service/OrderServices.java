package ru.kata.spring.boot_security.demo.service;


import com.google.zxing.WriterException;
import ru.kata.spring.boot_security.demo.model.Order;

import java.io.IOException;
import java.util.List;

public interface OrderServices {
	
	List<Order> getAllOrders();
	
	void deleteOrder(Order order);
	
	List<Order> getOrderByStatus(Enum status);
	
	byte[] getSticker(List<Long> orderIds, String type, int width, int height) throws IOException, WriterException;
}
