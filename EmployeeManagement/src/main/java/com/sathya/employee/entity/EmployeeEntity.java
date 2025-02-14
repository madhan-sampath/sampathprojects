package com.sathya.employee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employee")
public class EmployeeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private int age;
	private String gender;
	private String marritalStatus;
	private long mobileNumber;
	private String position;
	private String department;
	private double basicSalary;
	
	private double da;
	private double hra;
	private double bonus;
	private double totalSalary;
	private double taxRate;
	private double finalSalary;
}
