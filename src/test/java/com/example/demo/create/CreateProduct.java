package com.example.demo.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@SpringBootTest
public class CreateProduct {

	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void start() {
		Product p1 = new Product();
		p1.setName("N次貼");
		p1.setCost(15);
		p1.setPrice(30);
		p1.setUnit("袋");
		p1.setMin(100);
		p1.setDiscribe("可再貼螢光透明貼紙");
		
		productRepository.save(p1);
	}
}
