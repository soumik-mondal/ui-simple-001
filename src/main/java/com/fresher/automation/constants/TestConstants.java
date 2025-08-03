package com.fresher.automation.constants;

/**
 * Test Constants for Basic Form Field Interaction Automation
 * Contains all static values and configuration parameters
 * 
 * @author Fresher Automation Engineer
 * @version 1.0.0
 */
public class TestConstants {
    
    // Application URLs
    public static final String BASE_URL = "https://the-internet.herokuapp.com";
    public static final String INPUTS_PAGE_URL = BASE_URL + "/inputs";
    
    // Test Data
    public static final String FIRST_TEST_NUMBER = "123";
    public static final String SECOND_TEST_NUMBER = "456";
    public static final String EMPTY_VALUE = "";
    public static final String BOUNDARY_VALUE_ZERO = "0";
    public static final String BOUNDARY_VALUE_LARGE = "999999";
    
    // Timeout values (in seconds)
    public static final int EXPLICIT_WAIT_TIMEOUT = 15;
    public static final int PAGE_LOAD_TIMEOUT = 30;
    public static final int IMPLICIT_WAIT_TIMEOUT = 10;
    
    // Browser configuration
    public static final String DEFAULT_BROWSER = "chrome";
    public static final boolean HEADLESS_MODE = false;
    
    // Test validation messages
    public static final String FIELD_EMPTY_VALIDATION_MSG = "Input field should be empty after clear operation";
    public static final String FIELD_VALUE_VALIDATION_MSG = "Input field value should match entered value";
    public static final String NUMERIC_INPUT_VALIDATION_MSG = "Input field should accept numeric values";
    public static final String PAGE_LOAD_VALIDATION_MSG = "Inputs page should load successfully";
    public static final String CLEAR_FUNCTIONALITY_MSG = "Clear functionality should work properly";
    
    // Page elements - Multiple locator strategies
    public static final String NUMBER_INPUT_XPATH = "//input[@type='number']";
    public static final String NUMBER_INPUT_CSS = "input[type='number']";
    public static final String PAGE_HEADER_XPATH = "//h3[contains(text(),'Inputs')]";
    public static final String PAGE_HEADER_CSS = "h3";
    
    // Browser capabilities
    public static final String CHROME_BINARY_PATH = "chrome.binary.path";
    public static final String FIREFOX_BINARY_PATH = "firefox.binary.path";
    
    // Test execution parameters
    public static final int RETRY_COUNT = 3;
    public static final int SCREENSHOT_QUALITY = 85;
    
    private TestConstants() {
        // Private constructor to prevent instantiation
        throw new UnsupportedOperationException("TestConstants is a utility class and cannot be instantiated");
    }
}
