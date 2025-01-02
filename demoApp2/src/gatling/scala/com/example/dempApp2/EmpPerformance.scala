package com.example.demoApp2

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class EmpPerformance extends Simulation {

  // Define base URL for the application
  val baseUrl = "http://localhost:8889"

  // Define HTTP protocol configuration
  val httpProtocol = http
    .baseUrl(baseUrl)  // Set the base URL for the requests
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")

  // Define the scenario for getting employee by ID using RestTemplate
  val restTemplateScenario = scenario("Get Employee by ID using RestTemplate")
    .exec(
      http("Get Employee using RestTemplate")
        .get("/api2/employees/restTemplate/1")
        .check(status.is(200))
    )
  
  // Define the scenario for getting employee by ID using Feign
  val feignScenario = scenario("Get Employee by ID using Feign")
    .exec(
      http("Get Employee using Feign")
        .get("/api2/employees/feign/1") 
        .check(status.is(200))
    )

  // Set up the load profile for the simulation
  setUp(
    restTemplateScenario.inject(atOnceUsers(10)),  // Simulate 10 concurrent users for the restTemplate scenario
    feignScenario.inject(rampUsers(10).during(30.seconds))  // Ramp up to 10 users over 30 seconds for Feign
  ).protocols(httpProtocol)
}
