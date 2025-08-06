package com.intelli.automation.tests;

import com.intelli.automation.base.BaseTest;
import com.intelli.automation.pages.SamplePage;
import com.intelli.automation.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * UI Simple 001 Test Class following Intelli-Test framework
 * Focuses on FORM FIELD INTERACTIONS as required by the project
 */
public class UISimple001Test extends BaseTest {
    
    private SamplePage samplePage;
    
    @Test(description = "Test form field interactions and input validations")
    public void testFormFieldInteractions() {
        System.out.println("🧪 Starting Form Field Interactions Test");
        
        // Initialize page object
        samplePage = new SamplePage(driver);
        
        // Navigate to INPUTS page (not login page)
        String testUrl = "https://the-internet.herokuapp.com/inputs";
        samplePage.navigateToSamplePage(testUrl);
        
        // Verify page title
        String expectedTitle = "The Internet";
        Assert.assertTrue(driver.getTitle().contains(expectedTitle), 
            "Page title should contain: " + expectedTitle);
        System.out.println("✅ Page title verified: " + driver.getTitle());
        
        // Test form field interactions
        testInputFieldOperations();
        
        // Test form validation
        testFormValidation();
        
        // Test URL context for inputs
        testInputUrlContext();
        
        System.out.println("✅ Form Field Interactions Test completed successfully");
    }
    
    @Test(description = "Test input field operations and validations")
    public void testInputFieldOperations() {
        System.out.println("🧪 Starting Input Field Operations Test");
        
        // Navigate to inputs page
        driver.get("https://the-internet.herokuapp.com/inputs");
        
        // Find input field
        WebElement numberInput = driver.findElement(By.tagName("input"));
        Assert.assertTrue(numberInput.isDisplayed(), "Number input should be displayed");
        
        // Test input field operations
        testInputFieldClear(numberInput);
        testInputFieldType(numberInput);
        testInputFieldValueRetrieval(numberInput);
        testInputFieldValidation(numberInput);
        
        System.out.println("✅ Input Field Operations Test completed successfully");
    }
    
    @Test(description = "Test form validation and business logic")
    public void testFormValidation() {
        System.out.println("🧪 Starting Form Validation Test");
        
        // Navigate to a form with validation
        driver.get("https://the-internet.herokuapp.com/inputs");
        
        // Test form validation logic
        WebElement inputField = driver.findElement(By.tagName("input"));
        
        // Test different input scenarios
        testNumericInput(inputField);
        testTextInput(inputField);
        testSpecialCharacterInput(inputField);
        
        System.out.println("✅ Form Validation Test completed successfully");
    }
    
    @Test(description = "Test URL context for form field project")
    public void testUrlContext() {
        System.out.println("🧪 Starting URL Context Test");
        
        // Test URL navigation for form field project
        String baseUrl = "https://the-internet.herokuapp.com";
        driver.get(baseUrl);
        
        // Navigate to inputs page
        driver.findElement(By.linkText("Inputs")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/inputs"), 
            "Should navigate to inputs page");
        
        // Verify URL context for form field project
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("the-internet.herokuapp.com"), 
            "URL should contain the expected domain");
        Assert.assertTrue(currentUrl.contains("/inputs"), 
            "URL should contain inputs path for form field project");
        
        System.out.println("✅ URL Context Test completed successfully");
    }
    
    @Test(description = "Test configuration and utility usage")
    public void testConfigurationUsage() {
        System.out.println("🧪 Starting Configuration Usage Test");
        
        // Test configuration reader
        String baseUrl = ConfigReader.getBaseUrl();
        String username = ConfigReader.getUsername();
        int timeout = ConfigReader.getTimeout();
        
        Assert.assertNotNull(baseUrl, "Base URL should not be null");
        Assert.assertNotNull(username, "Username should not be null");
        Assert.assertTrue(timeout > 0, "Timeout should be positive");
        
        System.out.println("📋 Configuration values:");
        System.out.println("   Base URL: " + baseUrl);
        System.out.println("   Username: " + username);
        System.out.println("   Timeout: " + timeout);
        
        System.out.println("✅ Configuration Usage Test completed successfully");
    }
    
    /**
     * Test input field clear operation
     */
    private void testInputFieldClear(WebElement inputField) {
        System.out.println("🧹 Testing input field clear operation...");
        
        // Clear the input field
        inputField.clear();
        Assert.assertEquals(inputField.getAttribute("value"), "", 
            "Input field should be empty after clear");
        
        System.out.println("✅ Input field clear operation tested successfully");
    }
    
    /**
     * Test input field type operation
     */
    private void testInputFieldType(WebElement inputField) {
        System.out.println("⌨️ Testing input field type operation...");
        
        // Type in the input field
        String testValue = "12345";
        inputField.sendKeys(testValue);
        Assert.assertEquals(inputField.getAttribute("value"), testValue, 
            "Input field should contain typed value");
        
        System.out.println("✅ Input field type operation tested successfully");
    }
    
    /**
     * Test input field value retrieval
     */
    private void testInputFieldValueRetrieval(WebElement inputField) {
        System.out.println("📥 Testing input field value retrieval...");
        
        // Get the current value
        String currentValue = inputField.getAttribute("value");
        Assert.assertNotNull(currentValue, "Input field value should not be null");
        
        System.out.println("✅ Input field value retrieval tested successfully");
    }
    
    /**
     * Test input field validation
     */
    private void testInputFieldValidation(WebElement inputField) {
        System.out.println("✅ Testing input field validation...");
        
        // Test if input field is enabled
        Assert.assertTrue(inputField.isEnabled(), "Input field should be enabled");
        
        // Test if input field is displayed
        Assert.assertTrue(inputField.isDisplayed(), "Input field should be displayed");
        
        System.out.println("✅ Input field validation tested successfully");
    }
    
    /**
     * Test numeric input scenarios
     */
    private void testNumericInput(WebElement inputField) {
        System.out.println("🔢 Testing numeric input scenarios...");
        
        // Test positive numbers
        inputField.clear();
        inputField.sendKeys("123");
        Assert.assertEquals(inputField.getAttribute("value"), "123", 
            "Should accept positive numbers");
        
        // Test negative numbers
        inputField.clear();
        inputField.sendKeys("-456");
        Assert.assertEquals(inputField.getAttribute("value"), "-456", 
            "Should accept negative numbers");
        
        // Test decimal numbers
        inputField.clear();
        inputField.sendKeys("789.123");
        Assert.assertEquals(inputField.getAttribute("value"), "789.123", 
            "Should accept decimal numbers");
        
        System.out.println("✅ Numeric input scenarios tested successfully");
    }
    
    /**
     * Test text input scenarios
     */
    private void testTextInput(WebElement inputField) {
        System.out.println("📝 Testing text input scenarios...");
        
        // Test alphabetic input
        inputField.clear();
        inputField.sendKeys("abc");
        Assert.assertEquals(inputField.getAttribute("value"), "abc", 
            "Should accept alphabetic input");
        
        // Test alphanumeric input
        inputField.clear();
        inputField.sendKeys("abc123");
        Assert.assertEquals(inputField.getAttribute("value"), "abc123", 
            "Should accept alphanumeric input");
        
        System.out.println("✅ Text input scenarios tested successfully");
    }
    
    /**
     * Test special character input
     */
    private void testSpecialCharacterInput(WebElement inputField) {
        System.out.println("🔤 Testing special character input...");
        
        // Test special characters
        inputField.clear();
        inputField.sendKeys("!@#$%");
        Assert.assertEquals(inputField.getAttribute("value"), "!@#$%", 
            "Should accept special characters");
        
        System.out.println("✅ Special character input tested successfully");
    }
    
    /**
     * Test form field interactions
     */
    private void testInputFieldOperations() {
        System.out.println("📝 Testing form field operations...");
        
        // Find input field
        WebElement inputField = driver.findElement(By.tagName("input"));
        
        // Test basic operations
        testInputFieldClear(inputField);
        testInputFieldType(inputField);
        testInputFieldValueRetrieval(inputField);
        testInputFieldValidation(inputField);
        
        System.out.println("✅ Form field operations tested successfully");
    }
    
    /**
     * Test form validation
     */
    private void testFormValidation() {
        System.out.println("✅ Testing form validation...");
        
        WebElement inputField = driver.findElement(By.tagName("input"));
        
        // Test various validation scenarios
        testNumericInput(inputField);
        testTextInput(inputField);
        testSpecialCharacterInput(inputField);
        
        System.out.println("✅ Form validation tested successfully");
    }
    
    /**
     * Test URL context for inputs
     */
    private void testInputUrlContext() {
        System.out.println("🌐 Testing URL context for inputs...");
        
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("the-internet.herokuapp.com"), 
            "URL should contain expected domain");
        Assert.assertTrue(currentUrl.contains("/inputs"), 
            "URL should contain inputs path for form field project");
        
        System.out.println("✅ URL context for inputs tested successfully");
    }
} 
