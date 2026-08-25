package com.automationexercise.utils;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    
    @BeforeSuite
    public void setupEnvironment() {
        // Fetch the baseUrl from config.properties
        RestAssured.baseURI = ConfigReader.getProperty("baseUrl");

        RestAssured.registerParser("text/html", Parser.JSON);
        
        System.out.println("Base URI has been set to: " + RestAssured.baseURI);
    }
}