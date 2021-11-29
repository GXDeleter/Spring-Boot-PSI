package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.PurchaseItem;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.PurchaseItemRepository;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	ProductRepository productRepository;
	
	@RequestMapping("/")
	public String index(Model model) {
		List<Product> products = productRepository.findAll();
		model.addAttribute("products", products);
		return "inventory";
	}
	
	// inventoryItem -----------------------------------------------------------------------
	
	@Autowired
	PurchaseItemRepository purchaseItemRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@RequestMapping("/product/purchaseitem/{id}")
	public String purchaseItemList(Model model, @PathVariable("id") Long id) {
		List<PurchaseItem> purchaseItems = purchaseItemRepository.findByProduct(productRepository.findById(id).get());
		model.addAttribute("purchaseItems", purchaseItems);
		return "inventoryitem";
	}
	
	@RequestMapping("/product/orderitem/{id}")
	public String orderItemList(Model model, @PathVariable("id") Long id) {
		List<OrderItem> orderItems = orderItemRepository.findByProduct(productRepository.findById(id).get());
		model.addAttribute("orderItems", orderItems);
		return "inventoryitem";
	}
}
