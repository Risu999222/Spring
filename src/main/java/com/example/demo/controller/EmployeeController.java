package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiListResponse;
import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.EmployeeFilteredList;
import com.example.demo.dto.EmployeeListRequest;
import com.example.demo.dto.EmployeeListResponse;
import com.example.demo.dto.EmployeeRequest;
import com.example.demo.dto.EmployeeResponse;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/myhr/employee/add")
	public ResponseEntity<EmployeeResponse> addEmployee(@RequestBody EmployeeRequest employeeRequest){
		Employee employee = new Employee();
		employee.setFname(employeeRequest.getData().get("fname"));
		employee.setFullname(employeeRequest.getData().get("fullname"));
		String dateString = employeeRequest.getData().get("dob");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dob = new Date();
		try {
			dob = dateFormat.parse(dateString);
		}catch(ParseException e){
			e.printStackTrace();
		}
		employee.setDob(dob);
		String dateStringjoining = employeeRequest.getData().get("doj");
		Date doj = new Date();
		try {
			doj = dateFormat.parse(dateStringjoining);
		}catch(ParseException e){
			e.printStackTrace();
		}
		employee.setDoj(doj);
		Integer salary = Integer.parseInt(employeeRequest.getData().get("salary"));
		employee.setSalary(salary);
		
		Long reportsTo = Long.parseLong(employeeRequest.getData().get("reportsTo"));
		employee.setReportsTo(reportsTo);
		
		Long rankId = Long.parseLong(employeeRequest.getData().get("rankId"));
		employee.setRankId(rankId);
		
		Long departmentId = Long.parseLong(employeeRequest.getData().get("deptId"));
		employee.setDeptId(departmentId);
		
		employee.setClientReqId(employeeRequest.getReqid());
		employee.setEmpId(Long.parseLong(employeeRequest.getData().get("empId")));
		
		ApiResponse result = employeeService.addEmployee(employee);
		EmployeeResponse employeeResponse = new EmployeeResponse();
		if(result.getStatus() == "success") {
			employeeResponse.setReqid(employeeRequest.getReqid());
			employeeResponse.setStatus(HttpStatus.CREATED);
			employeeResponse.setStatus_code(HttpStatus.CREATED.value());
			employeeResponse.setStatus_msg(HttpStatus.CREATED.toString());
			employeeResponse.set_server_ts(LocalDateTime.now());
			Map<String, String> data = new HashMap<>();
			data.put("EmpId", result.getEmployee().getEmpId().toString());
			data.put("FullName", result.getEmployee().getFullname());
			employeeResponse.setData(data);
		}
		else {
			employeeResponse.setReqid(employeeRequest.getReqid());
			employeeResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			employeeResponse.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value());
			employeeResponse.setStatus_msg(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			employeeResponse.set_server_ts(LocalDateTime.now());
			Map<String, String> data = new HashMap<>();
			employeeResponse.setData(data);
		}
		return ResponseEntity.ok(employeeResponse);
	}
	
	@GetMapping("/myhr/employee/list")
	public ResponseEntity<EmployeeListResponse> listEmployee(@RequestBody EmployeeListRequest employeeListRequest){
		ApiListResponse result = employeeService.listOfEmployees();
		EmployeeListResponse employeelistResponse = new EmployeeListResponse();
		if(result.getStatus() == "success") {
			employeelistResponse.setReqid(employeeListRequest.getReqid());
			employeelistResponse.setStatus(HttpStatus.OK);
			employeelistResponse.setStatus_code(HttpStatus.OK.value());
			employeelistResponse.setStatus_msg(HttpStatus.OK.toString());
			employeelistResponse.set_server_ts(LocalDateTime.now());
			List<Map<String, String>> data = new ArrayList<>();
			int size = result.getEmployeeList().size();
			for(int i=0;i<size;i++) {
				Employee e = result.getEmployeeList().get(i);
				Map<String, String> res = new HashMap<>();
				res.put("empId", e.getEmpId().toString());
				res.put("empname", e.getFullname());
				data.add(res);
			}
			employeelistResponse.setData(data);
		}
		else {
			employeelistResponse.setReqid(employeeListRequest.getReqid());
			employeelistResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			employeelistResponse.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value());
			employeelistResponse.setStatus_msg(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			employeelistResponse.set_server_ts(LocalDateTime.now());
			List<Map<String, String>> data = new ArrayList<>();
			employeelistResponse.setData(data);
		}
		return ResponseEntity.ok(employeelistResponse);
	}
	
	
}