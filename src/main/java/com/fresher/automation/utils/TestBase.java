package com.fresher.automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.fresher.automation.constants.TestConstants.*;

/**
 * Base class for all test classes extending TestNG framework
 * Handles WebDriver initialization, configuration, and cleanup
 * Provides common utilities and setup for test execution
 * Implements Page Object Model design pattern support
 * 
 * @author Fresher Automation Engineer
 * @version 1.0.0
 */
public class TestBase {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    /**
     * Setup method to initialize WebDriver before each test method
     * Configures browser settings, timeouts, and window management
     * Supports multiple browsers with parameter-driven execution
     * 
     * @param browser Browser name (chrome/firefox) - defaults to chrome
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(@Optional(DEFAULT_BROWSER) String browser) {
        try {
            System.out.println("🚀 Initializing test environment...");
            System.out.println("📋 Browser: " + browser);
            System.out.println("🌐 Target URL: " + INPUTS_PAGE_URL);
            
            // Initialize WebDriver based on browser parameter
            driver = initializeDriver(browser);
            
            // Configure driver timeouts for stable automation
            configureBrowserTimeouts();
            
            // Maximize browser window for better element visibility
            driver.manage().window().maximize();
            
            // Initialize explicit wait with custom timeout
            wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
            
            System.out.println("✅ Browser setup completed successfully: " + browser.toUpperCase());
            
        } catch (Exception e) {
            System.err.println("❌ Failed to setup browser: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Browser initialization failed", e);
        }
    }
    
    /**
     * Initialize WebDriver instance based on browser type
     * Configures browser-specific options and capabilities
     * Uses WebDriverManager for automatic driver management
     * 
     * @param browser Browser name (case-insensitive)
     * @return Configured WebDriver instance
     * @throws IllegalArgumentException if browser is not supported
     */
    private WebDriver initializeDriver(String browser) {
        WebDriver driverInstance;
        
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = configureChromeOptions();
                driverInstance = new ChromeDriver(chromeOptions);
                break;
                
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = configureFirefoxOptions();
                driverInstance = new FirefoxDriver(firefoxOptions);
                break;
                
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser + 
                    ". Supported browsers: chrome, firefox");
        }
        
        return driverInstance;
    }
    
    /**
     * Configure Chrome browser options for optimal automation
     * Includes stability, performance, and security settings
     * 
     * @return Configured ChromeOptions
     */
    private ChromeOptions configureChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        
        if (HEADLESS_MODE) {
            options.addArguments("--headless");
            System.out.println("🔇 Running in headless mode");
        }
        
        // Stability and performance options
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
        
        // Additional options for CI/CD environments
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        
        return options;
    }
    
    /**
     * Configure Firefox browser options for automation
     * 
     * @return Configured FirefoxOptions
     */
    private FirefoxOptions configureFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        
        if (HEADLESS_MODE) {
            options.addArguments("--headless");
        }
        
        // Firefox-specific configurations
        options.addArguments("--width=1920");
        options.addArguments("--height=1080");
        
        return options;
    }
    
    /**
     * Configure browser timeouts for stable test execution
     * Sets implicit wait, page load timeout, and script timeout
     */
    private void configureBrowserTimeouts() {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(30));
    }
    
    /**
     * Cleanup method to quit WebDriver after each test method
     * Ensures proper resource cleanup and prevents memory leaks
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            if (driver != null) {
                System.out.println("🧹 Cleaning up browser resources...");
                driver.quit();
                System.out.println("✅ Browser closed successfully");
            }
        } catch (Exception e) {
            System.err.println("❌ Error during browser cleanup: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver = null;
            wait = null;
        }
    }
    
    /**
     * Get current WebDriver instance
     * 
     * @return WebDriver instance
     * @throws IllegalStateException if driver is not initialized
     */
    public WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized. Call setUp() first.");
        }
        return driver;
    }
    
    /**
     * Get WebDriverWait instance for explicit waits
     * 
     * @return WebDriverWait instance
     * @throws IllegalStateException if wait is not initialized
     */
    public WebDriverWait getWait() {
        if (wait == null) {
            throw new IllegalStateException("WebDriverWait is not initialized. Call setUp() first.");
        }
        return wait;
    }
    
    /**
     * Get current page title for verification
     * 
     * @return Current page title
     */
    protected String getCurrentPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Get current page URL for verification
     * 
     * @return Current page URL
     */
    protected String getCurrentPageUrl() {
        return driver.getCurrentUrl();
    }
}
