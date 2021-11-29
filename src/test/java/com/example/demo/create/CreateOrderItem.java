package com.example.demo.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;

@SpringBootTest
public class CreateOrderItem {

	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void start() {
		
		OrderItem oi1 = new OrderItem();
		Order o1 = orderRepository.findById(1L).get();
		Product p1 = productRepository.findById(1L).get();
		
		oi1.setOrder(o1);
		oi1.setProduct(p1);
		oi1.setAmount(1);
		oi1.setMemo("無");
		
		orderItemRepository.save(oi1);
	}
}
