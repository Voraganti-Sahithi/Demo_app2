package com.example.demoApp2.pact;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "ProviderPact", port = "8890")
public class EmployeeConsumerPact {
	@Pact(consumer = "ConsumerPact")
    public V4Pact createPact(PactDslWithProvider builder) {
        PactDslJsonBody responseBody = new PactDslJsonBody()
            .integerType("id", 1)
            .stringType("name", "sahithi")
            .integerType("age", 30);
 
        return builder
            .given("User with ID 1 exists")
            .uponReceiving("A request to retrieve user with ID 1")
            .path("/api/employees/byId/1")
            .method("GET")
            .willRespondWith()
            .status(200)
            .body(responseBody)
            .toPact(V4Pact.class);
    }
 
    @Test
    void testPact() throws Exception {
        // Create a RestTemplate to make the HTTP call
        RestTemplate restTemplate = new RestTemplate();
        
        // Define the URL of the provider
        String url = "http://localhost:8890/api/employees/byId/1";
        
        // Make the HTTP GET request
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        // Verify the response status code
        assertEquals(200, response.getStatusCodeValue());
        
        // Verify the response body
        String expectedBody = "{\"id\":1,\"name\":\"sahithi\",\"age\":30}";
        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(
            objectMapper.readTree(expectedBody),
            objectMapper.readTree(response.getBody())
        );
    }
}
