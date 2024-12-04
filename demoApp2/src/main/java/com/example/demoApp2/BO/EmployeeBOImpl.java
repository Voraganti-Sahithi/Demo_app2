package com.example.demoApp2.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demoApp2.EO.EmployeeEO;
import com.example.demoApp2.dto.EmployeeDTO;


@Component
public class EmployeeBOImpl implements EmployeeBO{
	
	@Autowired
    private EmployeeEO employeeEO;

	public EmployeeDTO fetchEmployeeByIdUsingRestTemplate(Long id) {
		// TODO Auto-generated method stub
		return employeeEO.getEmployeeByIdUsingRestTemplate(id);
	}

	public EmployeeDTO fetchEmployeeByIdUsingFeign(Long id) {
		// TODO Auto-generated method stub
		return employeeEO.getEmployeeByIdUsingFeign(id);
	}
	

}
