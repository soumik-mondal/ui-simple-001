package com.intelli.automation.pages;

import com.intelli.automation.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Sample page object following Intelli-Test framework
 * Focuses ONLY on form field interactions (no login functionality)
 */
public class SamplePage extends BasePage {
    
    // Form field elements using @FindBy annotation
    @FindBy(tagName = "input")
    private WebElement inputField;
    
    @FindBy(id = "number-input")
    private WebElement numberInput;
    
    @FindBy(id = "text-input")
    private WebElement textInput;
    
    @FindBy(id = "form-field")
    private WebElement formField;
    
    public SamplePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Navigate to the inputs page
     * @param url The URL to navigate to
     */
    public void navigateToInputsPage(String url) {
        driver.get(url);
        System.out.println("🌐 Navigated to inputs page: " + url);
    }
    
    /**
     * Clear the input field
     */
    public void clearInputField() {
        sendKeysToElement(inputField, "");
        System.out.println("🧹 Cleared input field");
    }
    
    /**
     * Type in the input field
     * @param text Text to type
     */
    public void typeInInputField(String text) {
        sendKeysToElement(inputField, text);
        System.out.println("⌨️ Typed in input field: " + text);
    }
    
    /**
     * Get input field value
     * @return Current value
     */
    public String getInputFieldValue() {
        return getElementText(inputField);
    }
    
    /**
     * Check if input field is displayed
     * @return true if displayed, false otherwise
     */
    public boolean isInputFieldDisplayed() {
        return isElementDisplayed(inputField);
    }
    
    /**
     * Test numeric input
     * @param number Number to input
     */
    public void testNumericInput(String number) {
        clearInputField();
        typeInInputField(number);
        System.out.println("🔢 Tested numeric input: " + number);
    }
    
    /**
     * Test text input
     * @param text Text to input
     */
    public void testTextInput(String text) {
        clearInputField();
        typeInInputField(text);
        System.out.println("📝 Tested text input: " + text);
    }
    
    /**
     * Test special character input
     * @param specialChars Special characters to input
     */
    public void testSpecialCharacterInput(String specialChars) {
        clearInputField();
        typeInInputField(specialChars);
        System.out.println("🔤 Tested special character input: " + specialChars);
    }
    
    /**
     * Validate input field functionality
     */
    public void validateInputField() {
        // Test basic operations
        clearInputField();
        typeInInputField("test123");
        
        // Verify value
        String currentValue = getInputFieldValue();
        System.out.println("✅ Input field validation completed. Current value: " + currentValue);
    }
    
    /**
     * Test form field operations
     */
    public void testFormFieldOperations() {
        System.out.println("📝 Testing form field operations...");
        
        // Test various input scenarios
        testNumericInput("12345");
        testTextInput("abc");
        testSpecialCharacterInput("!@#$%");
        
        System.out.println("✅ Form field operations completed");
    }
    
    /**
     * Get page title
     * @return Page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Get current URL
     * @return Current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
} 
