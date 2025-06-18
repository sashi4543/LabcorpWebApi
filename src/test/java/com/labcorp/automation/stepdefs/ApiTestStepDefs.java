package com.labcorp.automation.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import java.util.Map;

public class ApiTestStepDefs {
    private RequestSpecification request;
    private Response response;
    private String baseURI;

    @Given("I have the base URI {string}")
    public void i_have_the_base_uri(String uri) {
        baseURI = uri;
        RestAssured.baseURI = uri;
        request = given()
            .header("Content-Type", "application/json");
    }

    @When("I send a GET request to {string} with parameter {string}")
    public void i_send_a_get_request_with_param(String endpoint, String queryParam) {
        response = request
            .when()
            .get(endpoint + "?" + queryParam);
        
        // Log response for debugging
        System.out.println("Response Body: " + response.getBody().asString());
    }

    @Then("the response status code should be {int}")
    public void verify_status_code(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    @Then("I should validate the following in response:")
    public void verify_response_fields(DataTable dataTable) {
        Map<String, String> fields = dataTable.asMap(String.class, String.class);
        
        // Validate path
        String path = response.jsonPath().getString("path");
        assertEquals("Path validation failed", fields.get("path"), path);
        
        // Validate IP is not empty
        String ip = response.jsonPath().getString("ip");
        assertNotNull("IP should not be null", ip);
        assertFalse("IP should not be empty", ip.isEmpty());
    }

    @Then("the response headers should be present")
    public void verify_headers() {
        // Verify headers exist in response
        Map<String, String> headers = response.jsonPath().getMap("headers");
        assertNotNull("Headers should not be null", headers);
        assertFalse("Headers should not be empty", headers.isEmpty());
        
        // Log headers for verification
        System.out.println("Response Headers: " + headers);
    }

    @When("I send a POST request to {string} with the following data:")
    public void i_send_a_post_request_with_data(String endpoint, String requestBody) {
        response = request
            .body(requestBody)
            .when()
            .post(endpoint);
    }

    @Then("the response should contain valid confirmation")
    public void verify_response_confirmation() {
        response.then().body(not(empty()));
        System.out.println("Response Body: " + response.getBody().asString());
    }
} 