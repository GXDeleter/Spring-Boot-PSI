package com.example.demo.create;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Order;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.OrderRepository;

@SpringBootTest
public class CreateOrder {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Test
	public void start() {
		Order o1 = new Order();
		Customer c1 = customerRepository.findById(1L).get();
		Employee e1 = employeeRepository.findById(1L).get();
		
		o1.setDate(new Date());
		o1.setEmployee(e1);
		o1.setCustomer(c1);
		o1.setShipDate(new Date());
		o1.setMemo("無");
		
		orderRepository.save(o1);
	}
}
