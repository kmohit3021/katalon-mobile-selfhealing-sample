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

WebUI.openBrowser('')

WebUI.navigateToUrl('https://orange.katalon.com/web/index.php/auth/login')

WebUI.waitForElementPresent(findTestObject('Object Repository/Web/Page_OrangeHRM/h5_Login'), 0)

WebUI.setText(findTestObject('Object Repository/Web/Page_OrangeHRM/input_Username_username'), 'mohit.kumar')

WebUI.setEncryptedText(findTestObject('Object Repository/Web/Page_OrangeHRM/input_Password_password'), 'H0Q291REJi8mZmD6Fe3ZMw==')

WebUI.click(findTestObject('Object Repository/Web/Page_OrangeHRM/button_Login'))

WebUI.waitForElementPresent(findTestObject('Object Repository/Web/Page_OrangeHRM/svg_Admin_oxd-icon oxd-main-menu-item--icon'), 
    0)

WebUI.click(findTestObject('Object Repository/Web/Page_OrangeHRM/span_Admin'))

WebUI.setText(findTestObject('Object Repository/Web/Page_OrangeHRM/input_Username_oxd-input oxd-input--focus'), 'mohit.kumar')

WebUI.click(findTestObject('Object Repository/Web/Page_OrangeHRM/button_Search'))

WebUI.verifyElementText(findTestObject('Object Repository/Web/Page_OrangeHRM/span_(1) Record Found'), '(1) Record Found')

WebUI.closeBrowser()

