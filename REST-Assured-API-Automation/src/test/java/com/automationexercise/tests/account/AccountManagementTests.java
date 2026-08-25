package com.automationexercise.tests.account;

import com.automationexercise.utils.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AccountManagementTests extends BaseTest {

    String accountEmail = "acc_manager_" + System.currentTimeMillis() + "@testmail.com";
    String accountPassword = "TestPassword123!";

    // 1. POST Create Account
    @Test(priority = 1)
    public void testCreateAccount() {
        System.out.println("Executing: POST Create Account");
        
        given()
            .formParam("name", "Account Manager")
            .formParam("email", accountEmail)
            .formParam("password", accountPassword)
            .formParam("title", "Mr")
            .formParam("birth_date", "10")
            .formParam("birth_month", "5")
            .formParam("birth_year", "1995")
            .formParam("firstname", "John")
            .formParam("lastname", "Doe")
            .formParam("company", "Tech QA")
            .formParam("address1", "456 Test Ave")
            .formParam("country", "Canada")
            .formParam("zipcode", "54321")
            .formParam("state", "Ontario")
            .formParam("city", "Toronto")
            .formParam("mobile_number", "9876543210")
        .when()
            .post("/createAccount")
        .then()
            .body("responseCode", equalTo(201))
            .body("message", equalTo("User created!"));
    }

    // 2. GET User Detail By Email
    @Test(priority = 2, dependsOnMethods = "testCreateAccount")
    public void testGetUserDetailByEmail() {
        System.out.println("Executing: GET User Detail By Email");
        
        given()
            .queryParam("email", accountEmail)
        .when()
            .get("/getUserDetailByEmail")
        .then()
            .body("responseCode", equalTo(200))
            .body("user.email", equalTo(accountEmail))
            .body("user.name", equalTo("Account Manager"));
    }

    // 3. PUT Update Account
    @Test(priority = 3, dependsOnMethods = "testCreateAccount")
    public void testUpdateAccount() {
        System.out.println("Executing: PUT Update Account");
        
        given()
            .formParam("name", "Account Manager Updated")
            .formParam("email", accountEmail)
            .formParam("password", accountPassword)
            .formParam("title", "Mr")
            .formParam("birth_date", "10")
            .formParam("birth_month", "5")
            .formParam("birth_year", "1995")
            .formParam("firstname", "John")
            .formParam("lastname", "Doe")
            .formParam("company", "Tech QA Updated") // Changed company
            .formParam("address1", "456 Test Ave Updated")
            .formParam("country", "Canada")
            .formParam("zipcode", "54321")
            .formParam("state", "Ontario")
            .formParam("city", "Toronto")
            .formParam("mobile_number", "9876543210")
        .when()
            .put("/updateAccount")
        .then()
            .body("responseCode", equalTo(200))
            .body("message", equalTo("User updated!"));
    }

    // 4. DELETE Delete Account
    @Test(priority = 4, dependsOnMethods = "testCreateAccount")
    public void testDeleteAccount() {
        System.out.println("Executing: DELETE Delete Account");
        
        given()
            .formParam("email", accountEmail)
            .formParam("password", accountPassword)
        .when()
            .delete("/deleteAccount")
        .then()
            .body("responseCode", equalTo(200))
            .body("message", equalTo("Account deleted!"));
    }
}