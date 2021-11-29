package com.example.demo.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;

@SpringBootTest
public class CreatEmployee {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Test
	public void start() {
		
		Employee e1 = new Employee();
		Department d1 = departmentRepository.findById(1L).get();
		e1.setName("e1");
		e1.setDepartment(d1);
		e1.setExt("#224");
		e1.setTitle("採購員");
		
		employeeRepository.save(e1);
	}
}
