package com.example.demo.create;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Purchase;
import com.example.demo.entity.Supplier;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PurchaseRepository;
import com.example.demo.repository.SupplierRepository;

@SpringBootTest
public class CreatePurchase {

	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Autowired
	SupplierRepository supplierRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Test
	public void start() {
		Purchase p1 = new Purchase();
		Supplier s1 = supplierRepository.findById(1L).get();
		Employee e1 = employeeRepository.findById(1L).get();
		
		p1.setDate(new Date());
		p1.setSupplier(s1);
		p1.setEmployee(e1);
		p1.setArrival(new Date());
		
		purchaseRepository.save(p1);
	}
}
