package com.automationexercise.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            // Path to the properties file where the URL is stored
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(file);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to read the config.properties file!");
        }
    }

    // Method to retrieve the baseUrl or any other property in our tests
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}