package com.example.demoApp2.EO;

import com.example.demoApp2.dto.EmployeeDTO;

public interface EmployeeEO {
	EmployeeDTO getEmployeeByIdUsingRestTemplate(Long id);
    EmployeeDTO getEmployeeByIdUsingFeign(Long id);
}
