package com.example.demo.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;

@SpringBootTest
public class CreateDepartment {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Test
	public void start() {
		Department d1 = new Department();
		d1.setName("採購部");
		d1.setDirector("d1");
		
		Department d2 = new Department();
		d2.setName("研發部");
		d2.setDirector("d2");
		
		departmentRepository.save(d1);
		departmentRepository.save(d2);
	}
}
