package com.example.demoApp2.EO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demoApp2.dto.EmployeeDTO;
import com.example.demoApp2.feign.EmployeeFeignClient;
import com.example.demoApp2.helper.EmployeeRestHelper;

@Component
public class EmployeeEOImpl implements EmployeeEO{

	@Autowired
    private EmployeeRestHelper restHelper;

    @Autowired
    private EmployeeFeignClient feignClient;
    
	@Override
	public EmployeeDTO getEmployeeByIdUsingRestTemplate(Long id) {
		// TODO Auto-generated method stub
		return restHelper.getEmployeeById(id);
		
	}

	@Override
	public EmployeeDTO getEmployeeByIdUsingFeign(Long id) {
		// TODO Auto-generated method stub
		 return feignClient.getEmployeeById(id);
	}

}
