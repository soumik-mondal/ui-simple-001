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
 * Page Object Model class for Inputs Page automation
 * Contains all web elements and operations related to the inputs page
 * Follows Page Object Model design pattern for better maintainability
 * Implements method chaining for fluent test writing
 * 
 * @author Fresher Automation Engineer
 * @version 1.0.0
 */
public class InputsPage {
    
    private final WebDriver driver;
    private final WebDriverWait wait;
    
    // Page elements using multiple locator strategies
    @FindBy(xpath = PAGE_HEADER_XPATH)
    private WebElement pageHeader;
    
    @FindBy(css = PAGE_HEADER_CSS)
    private WebElement pageHeaderCss;
    
    @FindBy(xpath = NUMBER_INPUT_XPATH)
    private WebElement numberInputField;
    
    @FindBy(css = NUMBER_INPUT_CSS)
    private WebElement numberInputFieldCss;
    
    /**
     * Constructor to initialize page elements using PageFactory
     * Establishes WebDriver and WebDriverWait instances for page operations
     * 
     * @param driver WebDriver instance for browser automation
     * @param wait WebDriverWait instance for explicit waits
     * @throws IllegalArgumentException if driver or wait is null
     */
    public InputsPage(WebDriver driver, WebDriverWait wait) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null");
        }
        if (wait == null) {
            throw new IllegalArgumentException("WebDriverWait cannot be null");
        }
        
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
        
        System.out.println("📄 InputsPage object initialized successfully");
    }
    
    /**
     * Navigate to the inputs page and verify successful loading
     * Implements comprehensive page load verification
     * 
     * @return InputsPage instance for method chaining
     * @throws RuntimeException if navigation fails
     */
    public InputsPage navigateToInputsPage() {
        try {
            System.out.println("🔄 Navigating to inputs page: " + INPUTS_PAGE_URL);
            driver.get(INPUTS_PAGE_URL);
            
            // Wait for page to load completely using multiple strategies
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PAGE_HEADER_XPATH)));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(NUMBER_INPUT_XPATH)));
            
            // Verify page is loaded correctly
            Assert.assertTrue(isPageLoaded(), PAGE_LOAD_VALIDATION_MSG);
            
            // Additional verification using URL
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("/inputs"), 
                "Current URL should contain '/inputs'. Actual: " + currentUrl);
            
            System.out.println("✅ Successfully navigated to inputs page");
            System.out.println("📍 Current URL: " + currentUrl);
            
            return this;
            
        } catch (Exception e) {
            System.err.println("❌ Failed to navigate to inputs page: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Navigation to inputs page failed", e);
        }
    }
    
    /**
     * Enter a number into the input field with comprehensive validation
     * Implements multiple locator strategies and error handling
     * 
     * @param number Number to enter as string
     * @return InputsPage instance for method chaining
     * @throws RuntimeException if number input operation fails
     */
    public InputsPage enterNumber(String number) {
        try {
            System.out.println("🔄 Entering number: " + number);
            
            // Validate input parameter
            if (number == null) {
                throw new IllegalArgumentException("Number cannot be null");
            }
            
            // Wait for input field to be clickable using primary locator
            WebElement inputElement = wait.until(ExpectedConditions.elementToBeClickable(numberInputField));
            
            // Clear any existing value first
            inputElement.clear();
            
            // Verify field is cleared
            String clearedValue = inputElement.getAttribute("value");
            if (clearedValue != null && !clearedValue.isEmpty()) {
                System.out.println("⚠️ Field not completely cleared, attempting additional clear");
                inputElement.clear();
            }
            
            // Enter the number
            inputElement.sendKeys(number);
            
            // Verify the number was entered correctly
            String enteredValue = inputElement.getAttribute("value");
            Assert.assertEquals(enteredValue, number, 
                "Entered value should match input. Expected: " + number + ", Actual: " + enteredValue);
            
            System.out.println("✅ Successfully entered number: " + number);
            return this;
            
        } catch (Exception e) {
            System.err.println("❌ Failed to enter number '" + number + "': " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Number input operation failed", e);
        }
    }
    
    /**
     * Clear the input field and verify it's empty
     * Implements robust clearing with verification
     * 
     * @return InputsPage instance for method chaining
     * @throws RuntimeException if clear operation fails
     */
    public InputsPage clearInputField() {
        try {
            System.out.println("🔄 Clearing input field");
            
            // Wait for input field to be clickable
            WebElement inputElement = wait.until(ExpectedConditions.elementToBeClickable(numberInputField));
            
            // Store original value for logging
            String originalValue = inputElement.getAttribute("value");
            System.out.println("📝 Original value before clear: '" + originalValue + "'");
            
            // Clear the field
            inputElement.clear();
            
            // Verify field is actually cleared
            String clearedValue = inputElement.getAttribute("value");
            if (clearedValue != null && !clearedValue.isEmpty()) {
                // Retry clear operation if needed
                System.out.println("⚠️ First clear attempt incomplete, retrying...");
                inputElement.clear();
                clearedValue = inputElement.getAttribute("value");
            }
            
            System.out.println("✅ Successfully cleared input field");
            System.out.println("📝 Field value after clear: '" + clearedValue + "'");
            
            return this;
            
        } catch (Exception e) {
            System.err.println("❌ Failed to clear input field: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Clear input field operation failed", e);
        }
    }
    
    /**
     * Get the current value of the input field
     * Implements robust value retrieval with null safety
     * 
     * @return Current value of the input field (never null)
     */
    public String getInputFieldValue() {
        try {
            // Wait for element to be present using multiple strategies
            wait.until(ExpectedConditions.presenceOfElement(By.xpath(NUMBER_INPUT_XPATH)));
            
            String value = numberInputField.getAttribute("value");
            String safeValue = value != null ? value : "";
            
            System.out.println("ℹ️ Current input field value: '" + safeValue + "'");
            return safeValue;
            
        } catch (Exception e) {
            System.err.println("❌ Failed to get input field value: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Get input field value operation failed", e);
        }
    }
    
    /**
     * Verify that the input field is empty
     * Implements comprehensive empty field validation
     * 
     * @return InputsPage instance for method chaining
     * @throws AssertionError if field is not empty
     */
    public InputsPage verifyFieldIsEmpty() {
        try {
            System.out.println("🔍 Verifying input field is empty");
            
            String currentValue = getInputFieldValue();
            Assert.assertEquals(currentValue, EMPTY_VALUE, FIELD_EMPTY_VALIDATION_MSG);
            
            // Additional verification - check field has no visual content
            boolean isFieldEmpty = currentValue.trim().isEmpty();
            Assert.assertTrue(isFieldEmpty, "Input field should be visually empty");
            
            System.out.println("✅ Verification passed: Input field is empty");
            return this;
            
        } catch (AssertionError e) {
            String currentValue = getInputFieldValue();
            System.err.println("❌ Verification failed: Input field is not empty. Current value: '" + currentValue + "'");
            throw new AssertionError("Field empty verification failed. Expected: empty, Actual: '" + currentValue + "'", e);
        }
    }
    
    /**
     * Verify that the input field contains the expected value
     * Implements comprehensive value verification with detailed error reporting
     * 
     * @param expectedValue Expected value to verify against
     * @return InputsPage instance for method chaining
     * @throws AssertionError if values don't match
     */
    public InputsPage verifyFieldValue(String expectedValue) {
        try {
            System.out.println("🔍 Verifying input field value equals: '" + expectedValue + "'");
            
            String currentValue = getInputFieldValue();
            Assert.assertEquals(currentValue, expectedValue, FIELD_VALUE_VALIDATION_MSG);
            
            // Additional verification for numeric values
            if (expectedValue != null && expectedValue.matches("\\d+")) {
                try {
                    int expectedNum = Integer.parseInt(expectedValue);
                    int actualNum = Integer.parseInt(currentValue);
                    Assert.assertEquals(actualNum, expectedNum, "Numeric values should match");
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Value verification as string (non-numeric format)");
                }
            }
            
            System.out.println("✅ Verification passed: Input field value is correct");
            return this;
            
        } catch (AssertionError e) {
            String currentValue = getInputFieldValue();
            System.err.println("❌ Verification failed: Expected '" + expectedValue + "' but found '" + currentValue + "'");
            throw new AssertionError("Field value verification failed. Expected: '" + expectedValue + "', Actual: '" + currentValue + "'", e);
        }
    }
    
    /**
     * Verify that the input field accepts numeric values
     * Implements comprehensive numeric input validation
     * 
     * @return InputsPage instance for method chaining
     * @throws AssertionError if field doesn't accept numeric input properly
     */
    public InputsPage verifyNumericInputAcceptance() {
        try {
            System.out.println("🔍 Verifying input field accepts numeric values");
            
            // Primary verification - check input type attribute
            String inputType = numberInputField.getAttribute("type");
            Assert.assertEquals(inputType, "number", "Input field should be of type 'number'");
            
            // Secondary verification - test with various numeric inputs
            String[] testNumbers = {"0", "123", "999"};
            String originalValue = getInputFieldValue();
            
            for (String testNumber : testNumbers) {
                enterNumber(testNumber);
                verifyFieldValue(testNumber);
                clearInputField();
                verifyFieldIsEmpty();
            }
            
            // Restore original value if any
            if (!originalValue.isEmpty()) {
                enterNumber(originalValue);
            }
            
            System.out.println("✅ Verification passed: Input field accepts numeric values");
            return this;
            
        } catch (AssertionError e) {
            System.err.println("❌ Verification failed: Input field does not properly accept numeric values");
            throw new AssertionError("Numeric input acceptance verification failed", e);
        }
    }
    
    /**
     * Verify that clear functionality works properly
     * Implements comprehensive clear functionality testing
     * 
     * @return InputsPage instance for method chaining
     * @throws AssertionError if clear functionality doesn't work
     */
    public InputsPage verifyClearFunctionality() {
        try {
            System.out.println("🔍 Verifying clear functionality works");
            
            // Test clear functionality with different values
            String[] testValues = {"123", "0", "999"};
            
            for (String testValue : testValues) {
                // Enter test value
                enterNumber(testValue);
                verifyFieldValue(testValue);
                
                // Clear the field
                clearInputField();
                verifyFieldIsEmpty();
                
                System.out.println("✅ Clear functionality verified for value: " + testValue);
            }
            
            System.out.println("✅ Verification passed: Clear functionality works correctly");
            return this;
            
        } catch (Exception e) {
            System.err.println("❌ Verification failed: Clear functionality does not work properly");
            throw new AssertionError(CLEAR_FUNCTIONALITY_MSG, e);
        }
    }
    
    /**
     * Verify value persistence after entry
     * Tests that entered values remain stable in the field
     * 
     * @param value Value to test persistence with
     * @return InputsPage instance for method chaining
     * @throws AssertionError if value doesn't persist properly
     */
    public InputsPage verifyValuePersistence(String value) {
        try {
            System.out.println("🔍 Verifying value persistence for: '" + value + "'");
            
            // Enter the value
            enterNumber(value);
            verifyFieldValue(value);
            
            // Simulate focus loss and regain
            pageHeader.click(); // Click elsewhere to lose focus
            Thread.sleep(500); // Brief pause
            numberInputField.click(); // Click back to input field
            
            // Verify value is still there after focus changes
            verifyFieldValue(value);
            
            // Additional persistence check - refresh verification
            String persistedValue = getInputFieldValue();
            Assert.assertEquals(persistedValue, value, "Value should persist after focus changes");
            
            System.out.println("✅ Verification passed: Value persistence works correctly");
            return this;
            
        } catch (Exception e) {
            System.err.println("❌ Verification failed: Value persistence does not work properly");
            throw new AssertionError("Value persistence verification failed for: " + value, e);
        }
    }
    
    /**
     * Check if the page is properly loaded
     * Implements multiple verification strategies
     * 
     * @return true if page is loaded correctly, false otherwise
     */
    private boolean isPageLoaded() {
        try {
            // Check multiple elements to ensure page is fully loaded
            boolean headerDisplayed = pageHeader.isDisplayed();
            boolean inputDisplayed = numberInputField.isDisplayed();
            boolean titleCorrect = driver.getTitle().toLowerCase().contains("internet");
            
            return headerDisplayed && inputDisplayed && titleCorrect;
            
        } catch (Exception e) {
            System.err.println("⚠️ Page load verification failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Get page title for verification purposes
     * 
     * @return Current page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Get current page URL for verification purposes
     * 
     * @return Current page URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    /**
     * Check if input field is currently focused
     * 
     * @return true if input field has focus, false otherwise
     */
    public boolean isInputFieldFocused() {
        try {
            WebElement activeElement = driver.switchTo().activeElement();
            return activeElement.equals(numberInputField);
        } catch (Exception e) {
            return false;
        }
    }
}
