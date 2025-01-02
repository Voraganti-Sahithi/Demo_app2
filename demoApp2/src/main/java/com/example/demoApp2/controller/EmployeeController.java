package com.example.demoApp2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demoApp2.dto.EmployeeDTO;
import com.example.demoApp2.service.EmployeeService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api2/employees")
public class EmployeeController {

	 @Autowired
	    private EmployeeService employeeService;

	    // Using RestTemplate to fetch employee by ID
	    @GetMapping("/restTemplate/{id}")
	    @CircuitBreaker(name = "employeeService", fallbackMethod = "getEmployeeByIdFallback")
	    public ResponseEntity<EmployeeDTO> getEmployeeByIdUsingRestTemplate(@PathVariable("id") Long id) {
	        try {
	            EmployeeDTO employeeDTO = employeeService.getEmployeeByIdUsingRestTemplate(id);
	            if (employeeDTO == null) {
	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	            }
	            return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    // Using Feign Client to fetch employee by ID
	    @GetMapping("/feign/{id}")
	    @CircuitBreaker(name = "employeeService", fallbackMethod = "getEmployeeByIdFallback")
	    public ResponseEntity<EmployeeDTO> getEmployeeByIdUsingFeign(@PathVariable("id") Long id) {
	        try {
	            EmployeeDTO employeeDTO = employeeService.getEmployeeByIdUsingFeign(id);
	            if (employeeDTO == null) {
	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	            }
	            return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.CONFLICT);
	        }
	    }
	    
	    public ResponseEntity<EmployeeDTO> getEmployeeByIdFallback(Long id, Throwable throwable) {
	        EmployeeDTO fallbackEmployee = new EmployeeDTO();
	        fallbackEmployee.setId(id);
	        fallbackEmployee.setName("Fallback Employee");
	        fallbackEmployee.setAge(20);
	        return new ResponseEntity<>(fallbackEmployee, HttpStatus.SERVICE_UNAVAILABLE);
	    }
}
