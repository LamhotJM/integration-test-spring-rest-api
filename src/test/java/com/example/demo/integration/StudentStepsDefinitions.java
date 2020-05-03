package com.example.demo.integration;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
 
import java.util.Map;
 
import org.apache.commons.lang3.StringUtils;
 
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
 
public class StudentStepsDefinitions {
 
	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
    private String ENDPOINT_GET_STUDENT_BY_ID = "http://localhost:8080/api/v1/students";
 
	@Given("a student exists with an id of (.*)")
	public void a_student_exists_with_isbn(String id){
		request = given().param(id);
	}
 
	@When("a user retrieves the student by id")
	public void a_user_retrieves_the_student_by_id(){
		response = request.when().get(ENDPOINT_GET_STUDENT_BY_ID);
		System.out.println("response: " + response.prettyPrint());
	}
 
	@Then("the status code is (\\d+)")
	public void verify_status_code(int statusCode){
		json = response.then().statusCode(statusCode);
	}	
 
}