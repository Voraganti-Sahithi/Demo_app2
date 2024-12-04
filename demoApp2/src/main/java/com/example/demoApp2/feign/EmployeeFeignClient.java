package com.example.demoApp2.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demoApp2.dto.EmployeeDTO;

@FeignClient(name = "firstApp", url = "${first.app.base.url}")
public interface EmployeeFeignClient {
	@GetMapping("/byId/{id}")
    EmployeeDTO getEmployeeById(@PathVariable("id") Long id);
}
