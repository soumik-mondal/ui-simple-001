package com.intelli.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration reader utility following Intelli-Test framework
 * Provides centralized configuration management
 */
public class ConfigReader {
    
    private static Properties properties;
    private static final String CONFIG_FILE = "config.properties";
    
    static {
        loadProperties();
    }
    
    /**
     * Load properties from config file
     */
    private static void loadProperties() {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(CONFIG_FILE);
            properties.load(fis);
            fis.close();
            System.out.println("📋 Configuration loaded successfully");
        } catch (IOException e) {
            System.out.println("⚠️ Config file not found, using default values");
            setDefaultProperties();
        }
    }
    
    /**
     * Set default properties if config file is not found
     */
    private static void setDefaultProperties() {
        properties.setProperty("base.url", "https://example.com");
        properties.setProperty("username", "testuser");
        properties.setProperty("password", "testpass");
        properties.setProperty("timeout", "10");
        properties.setProperty("browser", "chrome");
    }
    
    /**
     * Get property value
     * @param key Property key
     * @return Property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    /**
     * Get property value with default
     * @param key Property key
     * @param defaultValue Default value if key not found
     * @return Property value or default
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    /**
     * Get base URL
     * @return Base URL
     */
    public static String getBaseUrl() {
        return getProperty("base.url", "https://example.com");
    }
    
    /**
     * Get username
     * @return Username
     */
    public static String getUsername() {
        return getProperty("username", "testuser");
    }
    
    /**
     * Get password
     * @return Password
     */
    public static String getPassword() {
        return getProperty("password", "testpass");
    }
    
    /**
     * Get timeout value
     * @return Timeout in seconds
     */
    public static int getTimeout() {
        String timeout = getProperty("timeout", "10");
        return Integer.parseInt(timeout);
    }
    
    /**
     * Get browser type
     * @return Browser type
     */
    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }
    
    /**
     * Check if property exists
     * @param key Property key
     * @return true if exists, false otherwise
     */
    public static boolean hasProperty(String key) {
        return properties.containsKey(key);
    }
    
    /**
     * Get all properties
     * @return Properties object
     */
    public static Properties getAllProperties() {
        return properties;
    }
} 
