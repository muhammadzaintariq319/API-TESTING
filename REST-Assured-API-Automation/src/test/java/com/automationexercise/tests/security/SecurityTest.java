package com.automationexercise.tests.security;

import com.automationexercise.utils.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SecurityTest extends BaseTest {

    // Test 1: Inspect basic security headers in the response[cite: 1]
    @Test(priority = 1)
    public void testResponseHeaderInspection() {
        System.out.println("Executing Security Test: Response Header Inspection...");

        Response response = given()
        .when()
            .get("/productsList");

        System.out.println("X-Content-Type-Options: " + response.header("X-Content-Type-Options"));
        System.out.println("X-Frame-Options: " + response.header("X-Frame-Options"));
        System.out.println("Strict-Transport-Security: " + response.header("Strict-Transport-Security"));

        response.then().statusCode(200);
    }

    // Test 2: Ensure passwords and internal stack traces are never exposed in the response[cite: 1]
    @Test(priority = 2)
    public void testSensitiveDataExposure() {
        System.out.println("Executing Security Test: Sensitive Data Exposure Check...");

        String testEmail = "security_check@example.com";
        String testPassword = "SuperSecretPassword123!";

        String responseBody = given()
            .formParam("email", testEmail)
            .formParam("password", testPassword)
        .when()
            .post("/verifyLogin")
        .then()
            .extract().asString();

        Assert.assertFalse(responseBody.contains(testPassword), "CRITICAL: Password exposed in response body!");
     
        String lowerCaseBody = responseBody.toLowerCase();
        Assert.assertFalse(lowerCaseBody.contains("stack trace"), "CRITICAL: Stack trace leaked!");
        Assert.assertFalse(lowerCaseBody.contains("sql"), "CRITICAL: Database SQL leaked!");
    }

    // Test 3: Basic input validation against cross-site scripting (XSS) payload[cite: 1]
    @Test(priority = 3)
    public void testInputValidationScriptInjection() {
        System.out.println("Executing Security Test: Script Injection Input Validation...");

        String maliciousPayload = "<script>alert(1)</script>";

        String responseBody = given()
            .formParam("search_product", maliciousPayload)
        .when()
            .post("/searchProduct")
        .then()

            .statusCode(lessThan(500))
            .extract().asString();

        Assert.assertFalse(responseBody.contains(maliciousPayload), "CRITICAL: XSS Payload reflected in response!");
    }
}