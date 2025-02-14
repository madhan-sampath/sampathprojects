package com.sathya.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathya.employee.entity.EmployeeEntity;
import com.sathya.employee.model.EmployeeModel;
import com.sathya.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	public void calculate(EmployeeModel employeeModel) {

		double da;
		double hra;
		double bonus;
		double totalSalary;
		double taxRate;
		double finalSalary;
		
		da = employeeModel.getBasicSalary() * 10 / 100;
		hra = employeeModel.getBasicSalary() * 20 / 100;
		bonus = employeeModel.getBasicSalary() * 5 / 100;
		totalSalary =  employeeModel.getBasicSalary() + da + hra + bonus;
		taxRate = 18;
		finalSalary = totalSalary-(totalSalary * taxRate / 100);
		
		EmployeeEntity employeeEntity =new EmployeeEntity();
		employeeEntity.setName(employeeModel.getName());
		employeeEntity.setAge(employeeModel.getAge());
		employeeEntity.setGender(employeeModel.getGender());
		employeeEntity.setMarritalStatus(employeeModel.getMarritalStatus());
		employeeEntity.setMobileNumber(employeeModel.getMobileNumber());
		employeeEntity.setPosition(employeeModel.getPosition());
		employeeEntity.setDepartment(employeeModel.getDepartment());
		employeeEntity.setBasicSalary(employeeModel.getBasicSalary());
		
		employeeEntity.setDa(da);
		employeeEntity.setHra(hra);
		employeeEntity.setBonus(bonus);
		employeeEntity.setTotalSalary(totalSalary);
		employeeEntity.setTaxRate(taxRate);
		employeeEntity.setFinalSalary(finalSalary);
		
		employeeRepository.save(employeeEntity);


		
	}

	public List<EmployeeEntity> getAllEmployees() {
		List<EmployeeEntity> employees=employeeRepository.findAll();
		return employees;
	}

	public EmployeeEntity searchById(Long id) {
		Optional<EmployeeEntity> optionalData=employeeRepository.findById(id);
		if(optionalData.isPresent()) {
			EmployeeEntity employee=optionalData.get();
			return employee;
		}
		else {
			return null;
		}
	}

	public void deleteById(Long id) {
		employeeRepository.deleteById(id);
	}

}
