package com.sathya.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sathya.employee.entity.EmployeeEntity;
import com.sathya.employee.model.EmployeeModel;
import com.sathya.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@GetMapping("/employeeform")
	public String employyeForm()
	{
		return "employeeForm";
	}
	@PostMapping("/saveemployee")
	public String postMethodName(EmployeeModel employeeModel) {
		employeeService.calculate(employeeModel);
		return "success";
	}
	
	//show all employee details
	@GetMapping("/employeelist")
	public String getMethodName(Model model) {
		
		List<EmployeeEntity> employees = employeeService.getAllEmployees();
		model.addAttribute("employees",employees);
		
		return "employee-list";
	}
	
	//search employee by id
	@GetMapping("/searchemployee")
	public String getEmployeeSearchForm() {
		return "search-employee";
	}
	
	@PostMapping("/searchbyid")
	public String SearchEmployeeBId(@RequestParam Long id,Model model) {
		EmployeeEntity employee= employeeService.searchById(id);
		model.addAttribute("employee",employee);
		return "search-employee";
	}
	
	//delete employee 

	//delete product using id
	@GetMapping("/delete/{id}")
	public String deleteEmployeeByID(@PathVariable("id") Long id) {
		employeeService.deleteById(id);
		return "redirect:/employeelist";
	}
	
	

}
