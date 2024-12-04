package com.example.demoApp2.service;

import com.example.demoApp2.dto.EmployeeDTO;

public interface EmployeeService {
	EmployeeDTO getEmployeeByIdUsingRestTemplate(Long id);
    EmployeeDTO getEmployeeByIdUsingFeign(Long id);
}
