package com.intelli.automation.pages;

import com.intelli.automation.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Sample page object following Intelli-Test framework
 * Demonstrates Page Object Model implementation
 */
public class SamplePage extends BasePage {
    
    // Page elements using @FindBy annotation
    @FindBy(id = "username")
    private WebElement usernameField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(id = "login-button")
    private WebElement loginButton;
    
    @FindBy(id = "welcome-message")
    private WebElement welcomeMessage;
    
    @FindBy(id = "submit-form")
    private WebElement submitFormButton;
    
    @FindBy(id = "form-title")
    private WebElement formTitle;
    
    public SamplePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Navigate to the sample page
     * @param url The URL to navigate to
     */
    public void navigateToSamplePage(String url) {
        driver.get(url);
        System.out.println("🌐 Navigated to sample page: " + url);
    }
    
    /**
     * Enter username
     * @param username Username to enter
     */
    public void enterUsername(String username) {
        sendKeysToElement(usernameField, username);
        System.out.println("👤 Entered username: " + username);
    }
    
    /**
     * Enter password
     * @param password Password to enter
     */
    public void enterPassword(String password) {
        sendKeysToElement(passwordField, password);
        System.out.println("🔒 Entered password");
    }
    
    /**
     * Click login button
     */
    public void clickLoginButton() {
        clickElement(loginButton);
        System.out.println("🖱️ Clicked login button");
    }
    
    /**
     * Get welcome message
     * @return Welcome message text
     */
    public String getWelcomeMessage() {
        return getElementText(welcomeMessage);
    }
    
    /**
     * Check if welcome message is displayed
     * @return true if displayed, false otherwise
     */
    public boolean isWelcomeMessageDisplayed() {
        return isElementDisplayed(welcomeMessage);
    }
    
    /**
     * Click submit form button
     */
    public void clickSubmitForm() {
        clickElement(submitFormButton);
        System.out.println("📝 Submitted form");
    }
    
    /**
     * Get form title
     * @return Form title text
     */
    public String getFormTitle() {
        return getElementText(formTitle);
    }
    
    /**
     * Complete login process
     * @param username Username to enter
     * @param password Password to enter
     */
    public void performLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        System.out.println("✅ Login process completed");
    }
    
    /**
     * Fill and submit form
     * @param username Username for form
     * @param password Password for form
     */
    public void fillAndSubmitForm(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSubmitForm();
        System.out.println("✅ Form filled and submitted");
    }
} 
