import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys


/*
*  Automate the interaction with a mobile application.
*
*  1. Start the mobile application using the specified APK file.
*  2. Tap on the "OK" button to dismiss any initial prompts.
*  3. Tap on the "OK" text view to proceed.
*  4. Tap on the "API Demos" text view to navigate to the API demos section.
*  5. Tap on the "Security" text view to access security options.
*  6. Tap on the "KeyStore" text view to open the KeyStore settings.
*  7. Set the text in the EditText field to "Katalon Demo".
*  8. Press back three times to navigate back through the application.
*  9. Close the application after completing the interactions.
*/

// Start the mobile application using the specified APK file
Mobile.startApplication('path/to/your/app.apk', false)

// Tap on the "OK" button to dismiss any initial prompts
Mobile.tap(findTestObject('Object Repository/OK_Button'), 0)

// Tap on the "OK" text view to proceed
Mobile.tap(findTestObject('Object Repository/OK_TextView'), 0)

// Tap on the "API Demos" text view to navigate to the API demos section
Mobile.tap(findTestObject('Object Repository/API_Demos_TextView'), 0)

// Tap on the "Security" text view to access security options
Mobile.tap(findTestObject('Object Repository/Security_TextView'), 0)

// Tap on the "KeyStore" text view to open the KeyStore settings
Mobile.tap(findTestObject('Object Repository/KeyStore_TextView'), 0)

// Set the text in the EditText field to "Katalon Demo"
Mobile.setText(findTestObject('Object Repository/EditText_Field'), 'Katalon Demo', 0)

// Press back three times to navigate back through the application
Mobile.pressBack()
Mobile.pressBack()
Mobile.pressBack()

// Close the application after completing the interactions
Mobile.closeApplication()