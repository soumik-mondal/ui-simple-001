package com.fresher.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static com.fresher.automation.constants.TestConstants.*;

/**
 * Page Object Model class for Inputs Page
 * Contains all web elements and operations related to the inputs page
 * Follows Page Object Model design pattern for better maintainability
 */
public class InputsPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Page elements using @FindBy annotations
    @FindBy(xpath = PAGE_HEADER_XPATH)
    private WebElement pageHeader;
    
    @FindBy(xpath = NUMBER_INPUT_XPATH)
    private WebElement numberInputField;
    
    /**
     * Constructor to initialize page elements
     * @param driver WebDriver instance
     * @param wait WebDriverWait instance
     */
    public InputsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Navigate to the inputs page
     * @return InputsPage instance for method chaining
     */
    public InputsPage navigateToInputsPage() {
        try {
            System.out.println("🔄 Navigating to inputs page: " + INPUTS_PAGE_URL);
            driver.get(INPUTS_PAGE_URL);
            
            // Wait for page to load completely
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PAGE_HEADER_XPATH)));
            
            // Verify page is loaded correctly
            Assert.assertTrue(isPageLoaded(), "Inputs page failed to load properly");
            System.out.println("✅ Successfully navigated to inputs page");
            
            return this;
            
        } catch (Exception e) {
            System.err.println("❌ Failed to navigate to inputs page: " + e.getMessage());
            throw new RuntimeException("Navigation failed", e);
        }
    }
    
    /**
     * Enter a number into the input field
     * @param number Number to enter as string
     * @return InputsPage instance for method chaining
     */
    public InputsPage enterNumber(String number) {
        try {
            System.out.println("🔄 Entering number: " + number);
            
            // Wait for input field to be clickable
            wait.until(ExpectedConditions.elementToBeClickable(numberInputField));
            
            // Clear any existing value first
            numberInputField.clear();
            
            // Enter the number
            numberInputField.sendKeys(number);
            
            System.out.println("✅ Successfully entered number: " + number);
            return this;
            
        } catch (Exception e) {
            System.err.println("❌ Failed to enter number: " + e.getMessage());
            throw new RuntimeException("Number input failed", e);
        }
    }
    
    /**
     * Clear the input field
     * @return InputsPage instance for method chaining
     */
    public InputsPage clearInputField() {
        try {
            System.out.println("🔄 Clearing input field");
            
            // Wait for input field to be clickable
            wait.until(ExpectedConditions.elementToBeClickable(numberInputField));
            
            // Clear the field
            numberInputField.clear();
            
            System.out.println("✅ Successfully cleared input field");
            return this;
            
        } catch (Exception e) {
            System.err.println("❌ Failed to clear input field: " + e.getMessage());
            throw new RuntimeException("Clear operation failed", e);
        }
    }
    
    /**
     * Get the current value of the input field
     * @return Current value of the input field
     */
    public String getInputFieldValue() {
        try {
            // Wait for element to be present
            wait.until(ExpectedConditions.presenceOfElement(By.xpath(NUMBER_INPUT_XPATH)));
            
            String value = numberInputField.getAttribute("value");
            System.out.println("ℹ️ Current input field value: '" + value + "'");
            
            return value != null ? value : "";
            
        } catch (Exception e) {
            System.err.println("❌ Failed to get input field value: " + e.getMessage());
            throw new RuntimeException("Get value operation failed", e);
        }
    }
    
    /**
     * Verify that the input field is empty
     * @return InputsPage instance for method chaining
     */
    public InputsPage verifyFieldIsEmpty() {
        try {
            System.out.println("🔍 Verifying input field is empty");
            
            String currentValue = getInputFieldValue();
            Assert.assertEquals(currentValue, EMPTY_VALUE, FIELD_EMPTY_VALIDATION_MSG);
            
            System.out.println("✅ Verification passed: Input field is empty");
            return this;
            
        } catch (AssertionError e) {
            System.err.println("❌ Verification failed: Input field is not empty. Current value: '" + getInputFieldValue() + "'");
            throw e;
        }
    }
    
    /**
     * Verify that the input field contains the expected value
     * @param expectedValue Expected value to verify
     * @return InputsPage instance for method chaining
     */
    public InputsPage verifyFieldValue(String expectedValue) {
        try {
            System.out.println("🔍 Verifying input field value equals: " + expectedValue);
            
            String currentValue = getInputFieldValue();
            Assert.assertEquals(currentValue, expectedValue, FIELD_VALUE_VALIDATION_MSG);
            
            System.out.println("✅ Verification passed: Input field value is correct");
            return this;
            
        } catch (AssertionError e) {
            System.err.println("❌ Verification failed: Expected '" + expectedValue + "' but found '" + getInputFieldValue() + "'");
            throw e;
        }
    }
    
    /**
     * Verify that the input field accepts numeric values
     * @return InputsPage instance for method chaining
     */
    public InputsPage verifyNumericInputAcceptance() {
        try {
            System.out.println("🔍 Verifying input field accepts numeric values");
            
            // Check if the input field type is 'number'
            String inputType = numberInputField.getAttribute("type");
            Assert.assertEquals(inputType, "number", "Input field should be of type 'number'");
            
            System.out.println("✅ Verification passed: Input field accepts numeric values");
            return this;
            
        } catch (AssertionError e) {
            System.err.println("❌ Verification failed: Input field does not properly accept numeric values");
            throw e;
        }
    }
    
    /**
     * Check if the page is properly loaded
     * @return true if page is loaded, false otherwise
     */
    private boolean isPageLoaded() {
        try {
            return pageHeader.isDisplayed() && 
                   pageHeader.getText().contains("Inputs") &&
                   numberInputField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Get page title for verification
     * @return Page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Get current page URL
     * @return Current page URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
