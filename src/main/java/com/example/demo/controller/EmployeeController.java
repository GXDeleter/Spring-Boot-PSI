package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@RequestMapping("/")
	public String index(Model model, @RequestParam(name = "department_id", required = false) Long department_id) {
		Employee employee = new Employee();
		List<Employee> employees =  employeeRepository.findAll();
		List<Department> departments = departmentRepository.findAll();
		Department department = null;
		
		// 若有指定 department id，預設其為 new Employee 的 Department，並找出關聯的 employee 資料
		if(department_id != null) {
			department = departmentRepository.findById(department_id).get();
			employee.setDepartment(department);
			employees = employeeRepository.findByDepartment(department);	
		}
		
		model.addAttribute("employee", employee);
		model.addAttribute("employees", employees);
		model.addAttribute("departments", departments);
		
		return "employee";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, Employee employee) {
		List<Department> departments = departmentRepository.findAll();
		
		// 若有指定員工，找出該名員工資料
		if(id != 0) {
			employee = employeeRepository.findById(id).get();
		}
		model.addAttribute("employee", employee);
		model.addAttribute("departments", departments);
		model.addAttribute("id", id);
		return "employee-update";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable("id") Long id, Employee employee) {
		
		// 若指定 id， 更新該 employee 資料
		if(id != 0) {
			employee.setId(id);			
		}
		
		employeeRepository.save(employee);
		return "redirect:/employee/?department_id=" + employee.getDepartment().getId();
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		Long department_id = employeeRepository.findById(id).get().getDepartment().getId();
		employeeRepository.deleteById(id);
		return "redirect:/employee/?department_id=" + department_id;
	}
}
