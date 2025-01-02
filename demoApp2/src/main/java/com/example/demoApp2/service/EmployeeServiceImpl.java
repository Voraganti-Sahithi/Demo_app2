package com.example.demoApp2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoApp2.BO.EmployeeBO;
import com.example.demoApp2.dto.EmployeeDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
    private EmployeeBO employeeBO;
	
	@Override
    @CircuitBreaker(name = "employeeService", fallbackMethod = "getEmployeeByIdFallback")
    public EmployeeDTO getEmployeeByIdUsingRestTemplate(Long id) {
        // Call the BO to fetch employee
        return employeeBO.fetchEmployeeByIdUsingRestTemplate(id);
    }

    // Use CircuitBreaker with a name and fallback method
    @Override
    @CircuitBreaker(name = "employeeService", fallbackMethod = "getEmployeeByIdFallback")
    public EmployeeDTO getEmployeeByIdUsingFeign(Long id) {
        // Call the BO to fetch employee
        return employeeBO.fetchEmployeeByIdUsingFeign(id);
    }

    // Fallback method when circuit breaker is triggered
    public EmployeeDTO getEmployeeByIdFallback(Long id, Throwable throwable) {
        // Provide a fallback response
        EmployeeDTO fallbackEmployee = new EmployeeDTO();
        fallbackEmployee.setId(id);
        fallbackEmployee.setName("Fallback Employee");
        fallbackEmployee.setAge(20);
        return fallbackEmployee;
    }

}
