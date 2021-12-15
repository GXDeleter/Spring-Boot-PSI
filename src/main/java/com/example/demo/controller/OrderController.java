package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.validator.DateValidator;
import com.example.demo.validator.InventoryValidator;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	DateValidator dateValidator;
	
	
	@RequestMapping("/")
	public String index(Model model, @RequestParam(name = "customer_id", required = false) Long customer_id) {
		Order order = new Order();
		List<Order> orders = orderRepository.findAll();;
		List<Customer> customers = customerRepository.findAll();
		Customer customer = null;
		
		// 若有指定 customer id，預設其為 new Order 的 Customer，並找出關聯的 order 資料
		if(customer_id != null) {
			customer = customerRepository.findById(customer_id).get();
			order.setCustomer(customer);
			orders = orderRepository.findByCustomer(customer);
		}
		
		model.addAttribute("order", order);
		model.addAttribute("orders", orders);
		model.addAttribute("customers", customers);
		
		return "order";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long id, Order order) {
		List<Customer> customers = customerRepository.findAll();
		
		// 不限定業務部的員工
		// List<Employee> employees = employeeRepository.findAll();
		
		// 限定業務部的員工
		Department department = departmentRepository.findByName("業務部").get(0);
		List<Employee> employees = employeeRepository.findByDepartment(department);
		
		// 若有指定訂單，找出該筆訂單資料
		if(id != 0) {
			order = orderRepository.findById(id).get();
		}
		
		model.addAttribute("customers", customers);
		model.addAttribute("employees", employees);
		model.addAttribute("order", order);
		model.addAttribute("id",id);
		return "order-update";
	}
	
	@RequestMapping("/update/{id}")
	public String update(Model model, Order order, @PathVariable("id") Long id, BindingResult result) {
		
		// 檢查日期設定，若設定的訂單日期晚於預計出貨日，會偵測錯誤
		dateValidator.validate(order, result);
		if(result.hasErrors()) {
			List<Customer> customers = customerRepository.findAll();
			List<Employee> employees = employeeRepository.findAll();
			model.addAttribute("customers", customers);
			model.addAttribute("employees", employees);
			model.addAttribute("order", order);
			model.addAttribute("id",id);
			return "order-update";
		}
		
		// 若有指定 id，更新該 order 資料
		if(id != 0 ) {
			order.setId(id);
		}
		
		orderRepository.save(order);			
		return "redirect:/order/";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		orderRepository.deleteById(id);
		return "redirect:/order/";
	}

	// OrderItem -----------------------------------------------------------------------------------
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	InventoryValidator inventoryValidator;
	
	@RequestMapping("/{id}/view/item")
	public String viewItem(Model model, @PathVariable("id") Long id) {
		model.addAttribute("order", orderRepository.findById(id).get());
		return "orderItem";
	}
	
	@RequestMapping("/{oid}/edit/item/{iid}")
	public String edit(Model model, @PathVariable("oid") Long oid, @PathVariable("iid") Long iid) {
		OrderItem orderItem = new OrderItem();
		List<Product> products = productRepository.findAll();
		
		// 若有指定 orderItem id (iid)，找出該筆資料，供修改
		// 若否，則指定目前 order id(oid) 的 order 物件給 orderItem，供新增
		if(iid != 0) {
			orderItem = orderItemRepository.findById(iid).get();
		}else {
			Order oder = orderRepository.findById(oid).get();
			orderItem.setOrder(oder);
		}
		
		model.addAttribute("orderItem", orderItem);
		model.addAttribute("products", products);
		model.addAttribute("iid", iid);
		return "orderitem-update";
	}
	
	@RequestMapping("/{oid}/update/item/{iid}")
	public String update(Model model, @PathVariable("oid") Long oid, @PathVariable("iid") Long iid, OrderItem orderItem, BindingResult result) {
		
		// 檢查訂購數量設定，若設定為0、無輸入或超出採購數量，會偵測錯誤
		inventoryValidator.validate(orderItem, result);
		if(result.hasErrors()) {
			model.addAttribute("orderItem", orderItem);
			model.addAttribute("products", productRepository.findAll());
			model.addAttribute("iid", iid);
			return "orderitem-update";
		}else {
			
			// 若有指定 iid ，修改該筆資料，否則新增
			if(iid != 0) {
				orderItem.setId(iid);
			}
			orderItemRepository.save(orderItem);
			return "redirect:/order/" + oid + "/view/item";			
		}
	}
	
	@RequestMapping("/{oid}/delete/item/{iid}")
	public String delete(@PathVariable("oid") Long oid, @PathVariable("iid") Long iid) {
		orderItemRepository.deleteById(iid);
		return "redirect:/order/" + oid + "/view/item";
	}
}
