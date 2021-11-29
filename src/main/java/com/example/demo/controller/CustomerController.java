package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepository;

	@RequestMapping("/")
	public String index(Model model) {
		List<Customer> customers = customerRepository.findAll();
		model.addAttribute("customers", customers);
		return "customer";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long id) {
		Customer customer = new Customer();
		if(id != 0) {
			customer = customerRepository.findById(id).get();
		}
		model.addAttribute("customer", customer);
		model.addAttribute("id", id);
		return "customer-update";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable("id") Long id, Customer customer) {
		if(id != 0) {
			customer.setId(id);			
		}
		customerRepository.save(customer);
		return "redirect:/customer/";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		customerRepository.deleteById(id);
		return "redirect:/customer/";
	}
}
