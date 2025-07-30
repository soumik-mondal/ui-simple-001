package com.fresher.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FormTest {
    
    @Test
    public void testFormInput() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.clear();
        usernameInput.sendKeys("tomsmith");
        
        String value = usernameInput.getAttribute("value");
        System.out.println("Entered value: " + value);
        
        driver.quit();
    }
}
