package com.automationexercise.tests.regression;

import com.automationexercise.utils.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class EndToEndAccountFlowTest extends BaseTest {

    // Dynamic data for the chained flow
    String dynamicEmail = "qa_chained_" + System.currentTimeMillis() + "@testmail.com";
    String password = "TestPassword123!";
    String initialName = "QA Tester";
    String updatedName = "QA Tester Updated";

    // Step 01: Create Account (Chain Start)
    @Test(priority = 1)
    public void step01_createAccountTest() {
        System.out.println("Executing: Step 01 - Create Account (Chain Start)");
        
        given()
            .formParam("name", initialName)
            .formParam("email", dynamicEmail)
            .formParam("password", password)
            .formParam("title", "Mr")
            .formParam("birth_date", "15")
            .formParam("birth_month", "6")
            .formParam("birth_year", "1995")
            .formParam("firstname", "QA")
            .formParam("lastname", "Tester")
            .formParam("company", "QA Learning")
            .formParam("address1", "123 Test Street")
            .formParam("country", "United States")
            .formParam("zipcode", "10001")
            .formParam("state", "NY")
            .formParam("city", "New York")
            .formParam("mobile_number", "9876543210")
        .when()
            .post("/createAccount")
        .then()
            .body("responseCode", equalTo(201))
            .body("message", equalTo("User created!"));
    }

    // Step 02: Verify Login With New Account
    @Test(priority = 2, dependsOnMethods = "step01_createAccountTest")
    public void step02_verifyLoginTest() {
        System.out.println("Executing: Step 02 - Verify Login With New Account");
        
        given()
            .formParam("email", dynamicEmail)
            .formParam("password", password)
        .when()
            .post("/verifyLogin")
        .then()
            .body("responseCode", equalTo(200))
            .body("message", equalTo("User exists!"));
    }

    // Step 03: Get User Detail (Confirm Data Persistence)
    @Test(priority = 3, dependsOnMethods = "step02_verifyLoginTest")
    public void step03_getUserDetailTest() {
        System.out.println("Executing: Step 03 - Get User Detail (Confirm Data Persistence)");
        
        given()
            .queryParam("email", dynamicEmail)
        .when()
            .get("/getUserDetailByEmail")
        .then()
            .body("responseCode", equalTo(200))
            .body("user.name", equalTo(initialName)); // Verifying the name matches what we sent in Step 1
    }

    // Step 04: Update Account
    @Test(priority = 4, dependsOnMethods = "step03_getUserDetailTest")
    public void step04_updateAccountTest() {
        System.out.println("Executing: Step 04 - Update Account");
        
        given()
            .formParam("name", updatedName) // Changing the name
            .formParam("email", dynamicEmail)
            .formParam("password", password)
            .formParam("title", "Mr")
            .formParam("birth_date", "15")
            .formParam("birth_month", "6")
            .formParam("birth_year", "1995")
            .formParam("firstname", "QA")
            .formParam("lastname", "Tester")
            .formParam("company", "QA Learning")
            .formParam("address1", "123 Test Street")
            .formParam("country", "United States")
            .formParam("zipcode", "10001")
            .formParam("state", "NY")
            .formParam("city", "New York")
            .formParam("mobile_number", "9876543210")
        .when()
            .put("/updateAccount")
        .then()
            .body("responseCode", equalTo(200))
            .body("message", equalTo("User updated!"));
    }

    // Step 05: Delete Account (Chain Cleanup)
    @Test(priority = 5, dependsOnMethods = "step04_updateAccountTest")
    public void step05_deleteAccountTest() {
        System.out.println("Executing: Step 05 - Delete Account (Chain Cleanup)");
        
        given()
            .formParam("email", dynamicEmail)
            .formParam("password", password)
        .when()
            .delete("/deleteAccount")
        .then()
            .body("responseCode", equalTo(200))
            .body("message", equalTo("Account deleted!"));
    }
}