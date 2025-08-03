# Basic Form Field Interaction Automation

## 📋 Project Overview
This project automates basic form field interactions on https://the-internet.herokuapp.com/inputs using Selenium WebDriver and TestNG framework, implementing industry-standard Page Object Model design pattern.

## 🎯 Problem Statement
**Objective:** Automate basic form field interactions on a practice website

### Platform
- **URL:** https://the-internet.herokuapp.com/inputs
- **Target Element:** Number input field

### Required Test Steps
1. ✅ Navigate to the inputs page
2. ✅ Enter a number (e.g., 123) into the number input field
3. ✅ Clear the input field
4. ✅ Verify the field is empty
5. ✅ Enter a different number (e.g., 456)
6. ✅ Verify the entered value is correct

### Required Validations
- ✅ Verify input field accepts numeric values
- ✅ Verify clear functionality works
- ✅ Verify value persistence after entry

## 🛠️ Technical Stack
- **Language:** Java 11
- **Framework:** Selenium WebDriver 4.15.0
- **Test Framework:** TestNG 7.8.0
- **Build Tool:** Maven 3.8.x
- **Design Pattern:** Page Object Model (POM)
- **Browser Management:** WebDriverManager 5.6.2
- **Logging:** SLF4J Simple 2.0.9

## 📁 Project Structure