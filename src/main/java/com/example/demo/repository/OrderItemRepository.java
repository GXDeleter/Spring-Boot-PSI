package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	List<OrderItem> findByProduct(Product product);
}
