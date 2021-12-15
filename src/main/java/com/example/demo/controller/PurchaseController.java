package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Product;
import com.example.demo.entity.Purchase;
import com.example.demo.entity.PurchaseItem;
import com.example.demo.entity.Supplier;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.PurchaseItemRepository;
import com.example.demo.repository.PurchaseRepository;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.validator.DateValidator;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Autowired
	SupplierRepository supplierRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	DateValidator dateValidator;
	
	@RequestMapping("/")
	public String index(Model model) {
		List<Purchase> purchases = purchaseRepository.findAll();
		model.addAttribute("purchases", purchases);
		return "purchase";
	}
	
	@RequestMapping("/edit/{id}")
	public String update(Model model, @PathVariable("id") Long id) {
		Purchase purchase = new Purchase();
		List<Supplier> suppliers = supplierRepository.findAll();
		
		// 不限定採購部的員工
		// List<Employee> employees = employeeRepository.findAll();
		
		// 限定業務部的員工
		Department department = departmentRepository.findByName("採購部").get(0);
		List<Employee> employees = employeeRepository.findByDepartment(department);
		
		// 若有指定 purchase id ，找出該筆資料
		if(id > 0) {
			purchase = purchaseRepository.findById(id).get();
		}
		
		model.addAttribute("purchase", purchase);
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("employees", employees);
		return "purchase-update";
	}
	
	@RequestMapping("/update/{id}")
	public String update(Model model, @PathVariable("id") Long id, Purchase purchase, BindingResult result) {
		
		// 檢查日期設定，若設定的採購日期晚於預計出貨日，會偵測錯誤
		dateValidator.validate(purchase, result);
		if(result.hasErrors()) {
			model.addAttribute("purchase", purchase);
			model.addAttribute("suppliers", supplierRepository.findAll());
			model.addAttribute("employees", employeeRepository.findByDepartment(departmentRepository.findByName("採購部").get(0)));
			model.addAttribute("id", id);
			return "purchase-update";
		}else {
			
			// 若有指定 id ，修改該筆資料，否則新增
			if(id != 0) {
				purchase.setId(id);
			}
			purchaseRepository.save(purchase);
		}
		return "redirect:/purchase/";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		purchaseRepository.deleteById(id);
		return "redirect:/purchase/";
	}
	
	// PurchaseItem -------------------------------------------------------------------------------------
	@Autowired
	PurchaseItemRepository purchaseItemRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@RequestMapping("/{pid}/view/item")
	public String view(Model model, @PathVariable("pid") Long pid) {
		Purchase purchase = purchaseRepository.findById(pid).get();
		model.addAttribute("purchase", purchase);
		return "purchaseitem";
	}
	
	@RequestMapping("/{pid}/edit/item/{iid}")
	public String edit(Model model, @PathVariable("pid") Long pid, @PathVariable("iid") Long iid) {
		PurchaseItem purchaseItem = new PurchaseItem();
		List<Product> products = productRepository.findAll();
		
		// 若有指定 purchaseItem id (iid)，找出該筆資料，供修改
		// 若否，則指定目前 purchase id(pid) 的 purchase 物件給 purchaseItem，供新增
		if(iid != 0) {
			purchaseItem = purchaseItemRepository.findById(iid).get();
		}else {
			Purchase purchase = purchaseRepository.findById(pid).get();
			purchaseItem.setPurchase(purchase);
		}
		
		model.addAttribute("purchaseitem", purchaseItem);
		model.addAttribute("products", products);
		model.addAttribute("iid", iid);
		return "purchaseitem-update";
	}
	
	@RequestMapping("/{pid}/update/item/{iid}")
	public String update(@PathVariable("pid") Long pid, @PathVariable("iid") Long iid, PurchaseItem purchaseItem) {
		
		// 若有指定 iid ，修改該筆資料，否則新增
		if(iid != 0 ) {
			purchaseItem.setId(iid);
		}
		purchaseItemRepository.save(purchaseItem);
		return "redirect:/purchase/" + pid + "/view/item";
	}
	
	@RequestMapping("/{pid}/delete/item/{iid}")
	public String delete(@PathVariable("pid") Long pid, @PathVariable("iid") Long iid) {
		purchaseItemRepository.deleteById(iid);
		return "redirect:/purchase/" + pid + "/view/item";
	}
}
