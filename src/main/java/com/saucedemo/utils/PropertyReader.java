package com.saucedemo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static final Properties PROPERTIES = loadProperties();

    private PropertyReader() {
    }

    private static Properties loadProperties() {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
        return prop;
    }

    public static String getProperty(String propertyName) {
        return PROPERTIES.getProperty(propertyName);
    }
}
