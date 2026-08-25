package com.automationexercise.tests.edgecases;

import com.automationexercise.utils.BaseTest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class EdgeCasesTest extends BaseTest {

    // Edge Case 1: Do not provide the required fields (name, firstname, etc.) while creating an account
    @Test(priority = 1)
    public void testCreateAccountMissingRequiredFields() {

        System.out.println("Running Edge Case: Create Account Missing Fields...");

        given()
            .formParam("email", "incomplete_" + System.currentTimeMillis() + "@example.com")
            .formParam("password", "TestPass123!")
            
            // Intentionally leaving out all other required fields
        .when()
            .post("/createAccount")

        .then()
            .log().status()
            .statusCode(lessThan(500)) 
            .body("responseCode", not(equalTo(201))); // Account should not be created successfully (201)
    }

    // Edge Case 2: Send a malformed JSON request body
    @Test(priority = 2)
    public void testVerifyLoginInvalidJsonBody() {

        System.out.println("Running Edge Case: Invalid JSON Body...");

        String malformedJson = "{ \"email\": \"test@test.com\" \"password\": missing_comma_and_quotes }";

        given()
            .header("Content-Type", "application/json")
            .body(malformedJson)

        .when()
            .post("/verifyLogin")

        .then()
            .log().status()
            .statusCode(lessThan(500)); // The API should fail gracefully instead of returning a 500 error
    }

    // Edge Case 3: Request user data using an email address that does not exist in the database
    @Test(priority = 3)
    public void testGetUserDetailsInvalidEmail() {

        System.out.println("Running Edge Case: Non-existent Email...");

        given()
            .queryParam("email", "definitely_not_registered_" + System.currentTimeMillis() + "@example.com")

        .when()
            .get("/getUserDetailByEmail")

        .then()
            .log().status()
            .statusCode(lessThan(500))
            .body("responseCode", not(equalTo(200))); // Valid user data (200) should not be returned
    }
}