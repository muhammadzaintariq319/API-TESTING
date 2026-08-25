package com.automationexercise.tests.authentication;

import com.automationexercise.utils.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AuthenticationTests extends BaseTest {

    // Generate a unique email for this test suite
    String authEmail = "auth_user_" + System.currentTimeMillis() + "@testmail.com";
    String authPassword = "TestPassword123!";

    // 1. POST Verify Login - Valid Credentials
    @Test(priority = 1)
    public void testVerifyLoginValidCredentials() {
        System.out.println("Executing: POST Verify Login - Valid Credentials");
        
        // Step A: Silently create the account first so we know it exists
        given()
            .formParam("name", "Auth User")
            .formParam("email", authEmail)
            .formParam("password", authPassword)
            .formParam("title", "Mr")
            .formParam("birth_date", "1").formParam("birth_month", "1").formParam("birth_year", "1990")
            .formParam("firstname", "Auth").formParam("lastname", "User").formParam("company", "QA")
            .formParam("address1", "123 Auth Street").formParam("country", "United States")
            .formParam("zipcode", "12345").formParam("state", "NY").formParam("city", "New York")
            .formParam("mobile_number", "1234567890")
        .when()
            .post("/createAccount");

        // Step B: Now perform the actual login verification test
        given()
            .formParam("email", authEmail)
            .formParam("password", authPassword)
        .when()
            .post("/verifyLogin")
        .then()
            .body("responseCode", equalTo(200))
            .body("message", equalTo("User exists!"));
    }

    // 2. POST Verify Login - Missing Email (Negative)
    @Test(priority = 2)
    public void testVerifyLoginMissingEmail() {
        System.out.println("Executing: POST Verify Login - Missing Email");
        
        given()
            .formParam("password", authPassword)
        .when()
            .post("/verifyLogin")
        .then()
            .body("responseCode", equalTo(400))
            .body("message", containsStringIgnoringCase("parameter is missing"));
    }

    // 3. POST Verify Login - Invalid Credentials (Negative)
    @Test(priority = 3)
    public void testVerifyLoginInvalidCredentials() {
        System.out.println("Executing: POST Verify Login - Invalid Credentials");
        
        given()
            .formParam("email", "wrong_user_" + System.currentTimeMillis() + "@testmail.com")
            .formParam("password", "WrongPassword123!")
        .when()
            .post("/verifyLogin")
        .then()
            .body("responseCode", equalTo(404))
            .body("message", equalTo("User not found!"));
    }

    // 4. DELETE Verify Login (Negative - Unsupported Method)
    @Test(priority = 4)
    public void testVerifyLoginUnsupportedMethod() {
        System.out.println("Executing: DELETE Verify Login - Unsupported Method");
        
        given()
        .when()
            .delete("/verifyLogin")
        .then()
            .body("responseCode", equalTo(405))
            .body("message", containsStringIgnoringCase("not supported"));
    }

    // 5. POST Verify Login - Empty Body (Edge Case)
    @Test(priority = 5)
    public void testVerifyLoginEmptyBody() {
        System.out.println("Executing: POST Verify Login - Empty Body");
        
        given()
        .when()
            .post("/verifyLogin")
        .then()
            .body("responseCode", equalTo(400))
            .body("message", containsStringIgnoringCase("parameter is missing"));
    }
}