package com.example.demoApp2.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demoApp2.dto.EmployeeDTO;
import com.example.demoApp2.util.constants.AppConstants;

@Component
public class EmployeeRestHelperImpl implements EmployeeRestHelper{

	private final RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public EmployeeDTO getEmployeeById(Long id) {
		// TODO Auto-generated method stub
		String url = AppConstants.FIRST_APP_BASE_URL + "/byId/" + id;
        return restTemplate.getForObject(url, EmployeeDTO.class);
	}

}
