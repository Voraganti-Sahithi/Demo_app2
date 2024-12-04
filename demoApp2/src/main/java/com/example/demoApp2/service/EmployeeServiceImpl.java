package com.example.demoApp2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoApp2.BO.EmployeeBO;
import com.example.demoApp2.dto.EmployeeDTO;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
    private EmployeeBO employeeBO;

	@Override
	public EmployeeDTO getEmployeeByIdUsingRestTemplate(Long id) {
		// TODO Auto-generated method stub
		 return employeeBO.fetchEmployeeByIdUsingRestTemplate(id);
	}

	@Override
	public EmployeeDTO getEmployeeByIdUsingFeign(Long id) {
		// TODO Auto-generated method stub
		return employeeBO.fetchEmployeeByIdUsingFeign(id);
	}

}
