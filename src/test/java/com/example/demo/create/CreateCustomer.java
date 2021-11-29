package com.example.demo.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@SpringBootTest
public class CreateCustomer {

	@Autowired
	CustomerRepository customerRepository;
	
	@Test
	public void start() {
		Customer c1 = new Customer();
		c1.setName("c1");
		c1.setTel("0278965412");
		c1.setCellPhone("0987654321");
		c1.setTaxNum("47491631");
		c1.setAddress("高雄市大寮區華東路28號");
		
		customerRepository.save(c1);
	}
}
