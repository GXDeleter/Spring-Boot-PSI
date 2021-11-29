package com.example.demo.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Supplier;
import com.example.demo.repository.SupplierRepository;

@SpringBootTest
public class CreateSupplier {

	@Autowired
	SupplierRepository supplierRepository;
	
	@Test
	public void start() {
		Supplier s1 = new Supplier();
		s1.setName("supplier 1");
		s1.setTel("0123456789");
		s1.setFax("0123456789");
		s1.setContact("s1");
		s1.setAddress("高雄市大寮區華東路28號");
		s1.setMemo("無");
		
		supplierRepository.save(s1);
	}
}
