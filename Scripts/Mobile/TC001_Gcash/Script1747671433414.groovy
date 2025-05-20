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

Mobile.startApplication('405b178e-6e51-4fda-90bf-4e1231ed9852', true)

CustomKeywords.'MobileHelper.tap'(findTestObject('Object Repository/Mobile/GCash/XCUIElementTypeButton - Allow Once'), 0, FailureHandling.OPTIONAL)

CustomKeywords.'MobileHelper.setText'(findTestObject('Object Repository/Mobile/GCash/XCUIElementTypeTextField'), '123456789', 0)

CustomKeywords.'MobileHelper.tap'(findTestObject('Object Repository/Mobile/GCash/XCUIElementTypeButton - Toolbar Done Button'), 0)

CustomKeywords.'MobileHelper.tap'(findTestObject('Object Repository/Mobile/GCash/XCUIElementTypeStaticText - Next'), 0)

Mobile.closeApplication()

