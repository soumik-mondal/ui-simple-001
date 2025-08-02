package com.fresher.automation.constants;

/**
 * Test Constants for Basic Form Field Interaction Automation
 * Contains all static values and configuration parameters
 */
public class TestConstants {
    
    // Application URLs
    public static final String BASE_URL = "https://the-internet.herokuapp.com";
    public static final String INPUTS_PAGE_URL = BASE_URL + "/inputs";
    
    // Test Data
    public static final String FIRST_TEST_NUMBER = "123";
    public static final String SECOND_TEST_NUMBER = "456";
    public static final String EMPTY_VALUE = "";
    
    // Timeout values (in seconds)
    public static final int EXPLICIT_WAIT_TIMEOUT = 10;
    public static final int PAGE_LOAD_TIMEOUT = 30;
    public static final int IMPLICIT_WAIT_TIMEOUT = 10;
    
    // Browser configuration
    public static final String DEFAULT_BROWSER = "chrome";
    public static final boolean HEADLESS_MODE = false;
    
    // Test validation messages
    public static final String FIELD_EMPTY_VALIDATION_MSG = "Input field should be empty after clear operation";
    public static final String FIELD_VALUE_VALIDATION_MSG = "Input field value should match entered value";
    public static final String NUMERIC_INPUT_VALIDATION_MSG = "Input field should accept numeric values";
    
    // Page elements
    public static final String NUMBER_INPUT_XPATH = "//input[@type='number']";
    public static final String PAGE_HEADER_XPATH = "//h3[contains(text(),'Inputs')]";
}
