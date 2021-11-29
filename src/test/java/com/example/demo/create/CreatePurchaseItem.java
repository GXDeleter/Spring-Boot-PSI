package com.example.demo.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Product;
import com.example.demo.entity.Purchase;
import com.example.demo.entity.PurchaseItem;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.PurchaseItemRepository;
import com.example.demo.repository.PurchaseRepository;

@SpringBootTest
public class CreatePurchaseItem {

	@Autowired
	PurchaseItemRepository purchaseItemRepository;
	
	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void start() {
		PurchaseItem pi1 = new PurchaseItem();
		Purchase p1 = purchaseRepository.findById(1L).get();
		Product pd1 = productRepository.findById(1L).get();
		
		pi1.setPurchase(p1);
		pi1.setProduct(pd1);
		pi1.setAmount(200);
		
		purchaseItemRepository.save(pi1);
				
	}
}
