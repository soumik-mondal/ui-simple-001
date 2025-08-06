package com.intelli.automation.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

/**
 * Base test class following Intelli-Test framework
 * Provides common WebDriver setup and teardown functionality
 */
public class BaseTest {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    @BeforeMethod
    public void setUp() {
        // Setup Chrome WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        
        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        
        // Initialize WebDriver
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        
        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        System.out.println("🚀 WebDriver initialized successfully");
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("🔚 WebDriver closed successfully");
        }
    }
    
    /**
     * Navigate to a specific URL
     * @param url The URL to navigate to
     */
    protected void navigateTo(String url) {
        driver.get(url);
        System.out.println("🌐 Navigated to: " + url);
    }
    
    /**
     * Get current page title
     * @return Page title
     */
    protected String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Get current page URL
     * @return Current URL
     */
    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
} 
