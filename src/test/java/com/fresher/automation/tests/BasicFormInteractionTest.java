package com.fresher.automation.tests;

import com.fresher.automation.pages.InputsPage;
import com.fresher.automation.utils.TestBase;
import org.testng.annotations.Test;

import static com.fresher.automation.constants.TestConstants.*;

/**
 * Test class for Basic Form Field Interaction automation
 * Covers all requirements specified in the problem statement
 * Uses TestNG framework for test execution and assertions
 */
public class BasicFormInteractionTest extends TestBase {
    
    /**
     * Main test method that covers all required steps
     * Test Priority: 1 (Primary test case)
     * 
     * Problem Statement Requirements:
     * 1. Navigate to the inputs page
     * 2. Enter a number (123) into the number input field
     * 3. Clear the input field
     * 4. Verify the field is empty
     * 5. Enter a different number (456)
     * 6. Verify the entered value is correct
     * 
     * Validations:
     * - Verify input field accepts numeric values
     * - Verify clear functionality works
     * - Verify value persistence after entry
     */
    @Test(priority = 1, 
          description = "Complete Basic Form Field Interaction Test",
          groups = {"smoke", "regression", "ui-simple-001"})
    public void testBasicFormFieldInteraction() {
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🚀 STARTING: Basic Form Field Interaction Test");
        System.out.println("Platform: " + INPUTS_PAGE_URL);
        System.out.println("=".repeat(60));
        
        try {
            // Initialize page object
            InputsPage inputsPage = new InputsPage(driver, wait);
            
            // Step 1: Navigate to the inputs page
            System.out.println("\n📍 STEP 1: Navigate to inputs page");
            inputsPage.navigateToInputsPage();
            
            // Step 2: Enter first number (123) into the number input field
            System.out.println("\n📍 STEP 2: Enter number " + FIRST_TEST_NUMBER);
            inputsPage.enterNumber(FIRST_TEST_NUMBER)
                     .verifyFieldValue(FIRST_TEST_NUMBER);
            
            // Step 3: Clear the input field
            System.out.println("\n📍 STEP 3: Clear the input field");
            inputsPage.clearInputField();
            
            // Step 4: Verify the field is empty
            System.out.println("\n📍 STEP 4: Verify field is empty");
            inputsPage.verifyFieldIsEmpty();
            
            // Step 5: Enter different number (456)
            System.out.println("\n📍 STEP 5: Enter number " + SECOND_TEST_NUMBER);
            inputsPage.enterNumber(SECOND_TEST_NUMBER);
            
            // Step 6: Verify the entered value is correct
            System.out.println("\n📍 STEP 6: Verify entered value is correct");
            inputsPage.verifyFieldValue(SECOND_TEST_NUMBER);
            
            // Additional Validation 1: Verify input field accepts numeric values
            System.out.println("\n📍 VALIDATION 1: Verify numeric input acceptance");
            inputsPage.verifyNumericInputAcceptance();
            
            // Additional Validation 2: Test clear functionality again
            System.out.println("\n📍 VALIDATION 2: Verify clear functionality works");
            inputsPage.enterNumber("789")
                     .clearInputField()
                     .verifyFieldIsEmpty();
            
            // Additional Validation 3: Test value persistence
            System.out.println("\n📍 VALIDATION 3: Verify value persistence after entry");
            inputsPage.enterNumber(SECOND_TEST_NUMBER)
                     .verifyFieldValue(SECOND_TEST_NUMBER);
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("✅ TEST COMPLETED SUCCESSFULLY!");
            System.out.println("All validations passed for Basic Form Field Interaction");
            System.out.println("✅ Navigation: PASSED");
            System.out.println("✅ Number Entry: PASSED");
            System.out.println("✅ Clear Functionality: PASSED");
            System.out.println("✅ Empty Field Verification: PASSED");
            System.out.println("✅ Value Verification: PASSED");
            System.out.println("✅ Numeric Input Acceptance: PASSED");
            System.out.println("✅ Value Persistence: PASSED");
            System.out.println("=".repeat(60));
            
        } catch (Exception e) {
            System.out.println("\n" + "=".repeat(60));
            System.err.println("❌ TEST FAILED: " + e.getMessage());
            System.err.println("Error occurred during test execution");
            System.out.println("=".repeat(60));
            throw e;
        }
    }
    
    /**
     * Additional test for edge cases and boundary conditions
     * Test Priority: 2 (Secondary test case)
     */
    @Test(priority = 2,
          description = "Edge Cases and Boundary Value Testing",
          groups = {"regression", "ui-simple-001"},
          dependsOnMethods = "testBasicFormFieldInteraction")
    public void testEdgeCasesAndBoundaryValues() {
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🧪 STARTING: Edge Cases and Boundary Value Testing");
        System.out.println("=".repeat(60));
        
        try {
            InputsPage inputsPage = new InputsPage(driver, wait);
            
            // Navigate to page
            inputsPage.navigateToInputsPage();
            
            // Test with different numeric values
            String[] testNumbers = {"0", "999", "1", "12345", "100"};
            
            for (String number : testNumbers) {
                System.out.println("\n📍 Testing boundary value: " + number);
                inputsPage.enterNumber(number)
                         .verifyFieldValue(number)
                         .clearInputField()
                         .verifyFieldIsEmpty();
            }
            
            // Test rapid clear and enter operations
            System.out.println("\n📍 Testing rapid operations sequence");
            inputsPage.enterNumber("111")
                     .clearInputField()
                     .enterNumber("222")
                     .verifyFieldValue("222")
                     .clearInputField()
                     .verifyFieldIsEmpty()
                     .enterNumber("333")
                     .verifyFieldValue("333");
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("✅ EDGE CASES TEST COMPLETED SUCCESSFULLY!");
            System.out.println("All boundary value tests passed");
            System.out.println("=".repeat(60));
            
        } catch (Exception e) {
            System.out.println("\n" + "=".repeat(60));
            System.err.println("❌ EDGE CASES TEST FAILED: " + e.getMessage());
            System.out.println("=".repeat(60));
            throw e;
        }
    }
    
    /**
     * Verification test for all core functionalities
     * Test Priority: 3 (Verification test)
     */
    @Test(priority = 3,
          description = "Complete functionality verification test",
          groups = {"smoke", "ui-simple-001"},
          dependsOnMethods = "testBasicFormFieldInteraction")
    public void testCompleteVerification() {
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🔍 STARTING: Complete Functionality Verification");
        System.out.println("=".repeat(60));
        
        try {
            InputsPage inputsPage = new InputsPage(driver, wait);
            
            // Complete end-to-end workflow
            inputsPage.navigateToInputsPage()
                     .verifyNumericInputAcceptance()
                     .enterNumber(FIRST_TEST_NUMBER)
                     .verifyFieldValue(FIRST_TEST_NUMBER)
                     .clearInputField()
                     .verifyFieldIsEmpty()
                     .enterNumber(SECOND_TEST_NUMBER)
                     .verifyFieldValue(SECOND_TEST_NUMBER)
                     .clearInputField()
                     .verifyFieldIsEmpty();
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("✅ VERIFICATION TEST COMPLETED SUCCESSFULLY!");
            System.out.println("Complete workflow verification passed");
            System.out.println("=".repeat(60));
            
        } catch (Exception e) {
            System.out.println("\n" + "=".repeat(60));
            System.err.println("❌ VERIFICATION TEST FAILED: " + e.getMessage());
            System.out.println("=".repeat(60));
            throw e;
        }
    }
}
