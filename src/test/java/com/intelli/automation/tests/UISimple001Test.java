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
 * Demonstrates comprehensive UI automation testing
 */
public class UISimple001Test extends BaseTest {
    
    private SamplePage samplePage;
    
    @Test(description = "Test basic form interaction and validation")
    public void testBasicFormInteraction() {
        System.out.println("🧪 Starting Basic Form Interaction Test");
        
        // Initialize page object
        samplePage = new SamplePage(driver);
        
        // Navigate to test page
        String testUrl = "https://the-internet.herokuapp.com/login";
        samplePage.navigateToSamplePage(testUrl);
        
        // Verify page title
        String expectedTitle = "The Internet";
        Assert.assertTrue(driver.getTitle().contains(expectedTitle), 
            "Page title should contain: " + expectedTitle);
        System.out.println("✅ Page title verified: " + driver.getTitle());
        
        // Test form interaction
        testFormElements();
        
        // Test login functionality
        testLoginFunctionality();
        
        // Test URL context
        testUrlContext();
        
        System.out.println("✅ Basic Form Interaction Test completed successfully");
    }
    
    @Test(description = "Test element interactions and validations")
    public void testElementInteractions() {
        System.out.println("🧪 Starting Element Interactions Test");
        
        // Navigate to a form page
        driver.get("https://the-internet.herokuapp.com/inputs");
        
        // Find and interact with input elements
        WebElement numberInput = driver.findElement(By.tagName("input"));
        Assert.assertTrue(numberInput.isDisplayed(), "Number input should be displayed");
        
        // Test input functionality
        numberInput.clear();
        numberInput.sendKeys("12345");
        Assert.assertEquals(numberInput.getAttribute("value"), "12345", 
            "Input value should match entered text");
        
        System.out.println("✅ Element Interactions Test completed successfully");
    }
    
    @Test(description = "Test business logic and form validation")
    public void testBusinessLogic() {
        System.out.println("🧪 Starting Business Logic Test");
        
        // Navigate to a page with business logic
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        
        // Test business logic - start button functionality
        WebElement startButton = driver.findElement(By.xpath("//button[text()='Start']"));
        Assert.assertTrue(startButton.isDisplayed(), "Start button should be displayed");
        
        // Click start button
        startButton.click();
        System.out.println("🖱️ Clicked start button");
        
        // Wait for loading to complete
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement finishElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.id("finish")));
        
        Assert.assertTrue(finishElement.isDisplayed(), "Finish element should be displayed after loading");
        Assert.assertEquals(finishElement.getText(), "Hello World!", 
            "Finish element should display correct text");
        
        System.out.println("✅ Business Logic Test completed successfully");
    }
    
    @Test(description = "Test URL context and navigation")
    public void testUrlContext() {
        System.out.println("🧪 Starting URL Context Test");
        
        // Test URL navigation
        String baseUrl = "https://the-internet.herokuapp.com";
        driver.get(baseUrl);
        
        // Verify URL context
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("the-internet.herokuapp.com"), 
            "URL should contain the expected domain");
        
        // Test navigation to different pages
        driver.findElement(By.linkText("Form Authentication")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"), 
            "Should navigate to login page");
        
        driver.navigate().back();
        Assert.assertTrue(driver.getCurrentUrl().equals(baseUrl), 
            "Should return to base URL");
        
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
     * Test form elements interaction
     */
    private void testFormElements() {
        System.out.println("📝 Testing form elements...");
        
        // Find form elements
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        
        // Verify elements are displayed
        Assert.assertTrue(usernameField.isDisplayed(), "Username field should be displayed");
        Assert.assertTrue(passwordField.isDisplayed(), "Password field should be displayed");
        Assert.assertTrue(loginButton.isDisplayed(), "Login button should be displayed");
        
        // Test element interactions
        usernameField.clear();
        usernameField.sendKeys("tomsmith");
        Assert.assertEquals(usernameField.getAttribute("value"), "tomsmith", 
            "Username field should contain entered value");
        
        passwordField.clear();
        passwordField.sendKeys("SuperSecretPassword!");
        Assert.assertEquals(passwordField.getAttribute("value"), "SuperSecretPassword!", 
            "Password field should contain entered value");
        
        System.out.println("✅ Form elements tested successfully");
    }
    
    /**
     * Test login functionality
     */
    private void testLoginFunctionality() {
        System.out.println("🔐 Testing login functionality...");
        
        // Perform login
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        
        usernameField.clear();
        usernameField.sendKeys("tomsmith");
        passwordField.clear();
        passwordField.sendKeys("SuperSecretPassword!");
        loginButton.click();
        
        // Verify successful login
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement flashMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.className("flash")));
        
        Assert.assertTrue(flashMessage.getText().contains("You logged into a secure area!"), 
            "Login should be successful");
        
        System.out.println("✅ Login functionality tested successfully");
    }
    
    /**
     * Test URL context validation
     */
    private void testUrlContext() {
        System.out.println("🌐 Testing URL context...");
        
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("the-internet.herokuapp.com"), 
            "URL should contain expected domain");
        Assert.assertTrue(currentUrl.contains("/secure_area"), 
            "URL should contain secure area after login");
        
        System.out.println("✅ URL context tested successfully");
    }
} 
