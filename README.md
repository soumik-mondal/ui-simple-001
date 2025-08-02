# Basic Form Field Interaction Automation

## Project Overview
This project automates basic form field interactions on https://the-internet.herokuapp.com/inputs using Selenium WebDriver and TestNG framework.

## Problem Statement
Automate basic form field interactions on a practice website with the following requirements:

### Platform
- URL: https://the-internet.herokuapp.com/inputs

### Test Steps
1. Navigate to the inputs page
2. Enter a number (e.g., 123) into the number input field
3. Clear the input field
4. Verify the field is empty
5. Enter a different number (e.g., 456)
6. Verify the entered value is correct

### Validations
- Verify input field accepts numeric values
- Verify clear functionality works
- Verify value persistence after entry

## Technical Stack
- **Language**: Java 11
- **Framework**: Selenium WebDriver 4.15.0
- **Test Framework**: TestNG 7.8.0
- **Build Tool**: Maven
- **Design Pattern**: Page Object Model
- **Browser Management**: WebDriverManager

## How to Run Tests

### Command Line
```bash
# Run all tests
mvn clean test

# Run with specific browser
mvn clean test -Dbrowser=chrome
```

## Key Features
- ✅ Page Object Model design pattern
- ✅ Comprehensive error handling and logging
- ✅ Explicit waits for stable automation
- ✅ Modular and maintainable code structure
- ✅ Cross-browser support
- ✅ Complete requirements coverage

## Author
Fresher Automation Engineer

## Version
1.0.0