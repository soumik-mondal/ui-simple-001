package com.fresher.automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.fresher.automation.constants.TestConstants.*;

/**
 * Base class for all test classes
 * Handles WebDriver initialization and cleanup
 * Provides common utilities for test execution
 */
public class TestBase {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    /**
     * Setup method to initialize WebDriver before each test
     * @param browser Browser name (chrome/firefox)
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(@Optional(DEFAULT_BROWSER) String browser) {
        try {
            // Initialize WebDriver based on browser parameter
            driver = initializeDriver(browser);
            
            // Configure driver timeouts
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
            
            // Maximize browser window for better element visibility
            driver.manage().window().maximize();
            
            // Initialize explicit wait
            wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
            
            System.out.println("✅ Browser setup completed successfully: " + browser);
            
        } catch (Exception e) {
            System.err.println("❌ Failed to setup browser: " + e.getMessage());
            throw new RuntimeException("Browser setup failed", e);
        }
    }
    
    /**
     * Initialize WebDriver instance based on browser type
     * @param browser Browser name
     * @return WebDriver instance
     */
    private WebDriver initializeDriver(String browser) {
        WebDriver driverInstance;
        
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                
                if (HEADLESS_MODE) {
                    chromeOptions.addArguments("--headless");
                }
                
                // Additional Chrome options for stability
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--window-size=1920,1080");
                
                driverInstance = new ChromeDriver(chromeOptions);
                break;
                
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driverInstance = new FirefoxDriver();
                break;
                
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        
        return driverInstance;
    }
    
    /**
     * Cleanup method to quit WebDriver after each test
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
                System.out.println("✅ Browser closed successfully");
            }
        } catch (Exception e) {
            System.err.println("❌ Error during browser cleanup: " + e.getMessage());
        }
    }
    
    /**
     * Get current WebDriver instance
     * @return WebDriver instance
     */
    public WebDriver getDriver() {
        return driver;
    }
    
    /**
     * Get WebDriverWait instance
     * @return WebDriverWait instance
     */
    public WebDriverWait getWait() {
        return wait;
    }
}
