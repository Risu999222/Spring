package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ApiListResponse;
import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Employee;

@Service
public interface EmployeeService {

	public ApiResponse addEmployee(Employee employee);
	
	public ApiListResponse listOfEmployees();
}
