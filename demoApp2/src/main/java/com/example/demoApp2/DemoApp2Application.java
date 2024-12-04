package com.example.demoApp2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.demoApp2.feign")
public class DemoApp2Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoApp2Application.class, args);
	}

}
