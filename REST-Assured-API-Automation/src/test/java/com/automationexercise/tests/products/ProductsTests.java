package com.automationexercise.tests.products;

import com.automationexercise.utils.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductsTests extends BaseTest {

    // 1. GET All Products List
    @Test(priority = 1)
    public void verifyGetAllProductsList() {
        System.out.println("Executing: GET All Products List");
        
        given()
        .when()
            .get("/productsList")
        .then()
            .body("responseCode", equalTo(200))
            .body("products", hasSize(greaterThan(0)))
            .body("products[0].id", instanceOf(Integer.class))
            .body("products[0].name", instanceOf(String.class));
    }

    // 2. POST All Products List (Negative - Unsupported Method)
    @Test(priority = 2)
    public void testPostAllProductsListUnsupported() {
        System.out.println("Executing: POST All Products List (Negative)");
        
        given()
        .when()
            .post("/productsList")
        .then()
           
            .body("responseCode", equalTo(405))
            .body("message", containsStringIgnoringCase("not supported"));
    }

    // 3. GET All Brands List[cite: 1]
    @Test(priority = 3)
    public void testGetAllBrandsList() {
        System.out.println("Executing: GET All Brands List");
        
        given()
        .when()
            .get("/brandsList")
        .then()
            .body("responseCode", equalTo(200))
            .body("brands", hasSize(greaterThan(0)))
            .body("brands[0].id", notNullValue())
            .body("brands[0].brand", instanceOf(String.class));
    }

    // 4. PUT All Brands List (Negative - Unsupported Method)[cite: 1]
    @Test(priority = 4)
    public void testPutAllBrandsListUnsupported() {
        System.out.println("Executing: PUT All Brands List (Negative)");
        
        given()
        .when()
            .put("/brandsList")
        .then()
            // Official docs state this endpoint only supports GET, should return 405[cite: 1]
            .body("responseCode", equalTo(405));
    }

    // 5. POST Search Product - Valid[cite: 1]
    @Test(priority = 5)
    public void testSearchProductValid() {
        System.out.println("Executing: POST Search Product - Valid");
        
        given()
           
            .formParam("search_product", "top") 
        .when()
            .post("/searchProduct")
        .then()
            .body("responseCode", equalTo(200))
            .body("products", hasSize(greaterThan(0)));
    }

    // 6. POST Search Product - Missing Param (Negative)[cite: 1]
    @Test(priority = 6)
    public void testSearchProductMissingParam() {
        System.out.println("Executing: POST Search Product - Missing Param");
        
        given()
            
        .when()
            .post("/searchProduct")
        .then()
            .body("responseCode", equalTo(400))
            .body("message", containsStringIgnoringCase("parameter is missing"));
    }
}