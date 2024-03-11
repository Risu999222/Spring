package com.example.demo.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ApiListResponse;
import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public ApiResponse addEmployee(Employee employee) {
		try {
			Employee e = employeeRepository.save(employee);
			return new ApiResponse("success", null, null, e);
		}catch(Exception e){
			String errorCode = "ADD_EMPLOYEE_ERROR";
			String errorMessage = e.getMessage();
			Employee employe = new Employee();
			return new ApiResponse("error", errorCode, errorMessage, employe);
		}
	}
	
	@Override
	public ApiListResponse listOfEmployees() {
		try {
			List<Employee> empList = employeeRepository.findAll();
			return new ApiListResponse("success", null, null, empList);
		}catch(Exception e) {
			String errorCode = "ADD_EMPLOYEE_ERROR";
			String errorMessage = e.getMessage();
			List<Employee> empList = new ArrayList<>();
			return new ApiListResponse("error", errorCode, errorMessage, empList);
		}
	}
	
}
