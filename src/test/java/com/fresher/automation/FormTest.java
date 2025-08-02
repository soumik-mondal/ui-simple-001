package com.fresher.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FormTest {
    
    @Test
    public void testFormFieldInteraction() {
        WebDriver driver = new ChromeDriver();
        
        // ✅ Correct URL for form practice
        driver.get("https://the-internet.herokuapp.com/inputs");
        
        // ✅ Form field interaction (not login)
        WebElement numberInput = driver.findElement(By.tagName("input"));
        numberInput.clear();
        numberInput.sendKeys("12345");
        
        String value = numberInput.getAttribute("value");
        System.out.println("Entered value: " + value);
        
        // ✅ Additional form interactions
        numberInput.clear();
        numberInput.sendKeys("67890");
        
        String newValue = numberInput.getAttribute("value");
        System.out.println("New value: " + newValue);
        
        driver.quit();
    }
}
