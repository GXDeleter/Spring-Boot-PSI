package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductRepository productRepository;
	
	@RequestMapping("/")
	public String index(Model model) {
		List<Product> products = productRepository.findAll();
		model.addAttribute("products", products);
		return "product";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long id) {
		Product product =  new Product();
		// 若有指定 product id，找出該筆資料
		if(id != 0) {
			product = productRepository.findById(id).get();
		}
		model.addAttribute("product",product);
		model.addAttribute("id", id);
		return "product-update";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable("id") Long id, Product product) {
		
		// 若有指定 product id，宿改該筆資料，否則新增
		if(id != 0) {
			product.setId(id);
		}
		productRepository.save(product);
		return "redirect:/product/";
	}
	
	@RequestMapping("/delete/{id}")
	public String deletee(@PathVariable("id") Long id) {
		productRepository.deleteById(id);
		return "redirect:/product/";
	}
}
