package com.sathya.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {
		private String name;
		private int age;
		private String gender;
		private String marritalStatus;
		private long mobileNumber;
		private String position;
		private String department;
		private double basicSalary;
		
		
}
