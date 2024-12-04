package com.example.demoApp2.BO;

import com.example.demoApp2.dto.EmployeeDTO;

public interface EmployeeBO {
	 EmployeeDTO fetchEmployeeByIdUsingRestTemplate(Long id);
	 EmployeeDTO fetchEmployeeByIdUsingFeign(Long id);
}
