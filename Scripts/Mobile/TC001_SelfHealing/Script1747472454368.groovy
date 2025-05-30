import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.model.FailureHandling.CONTINUE_ON_FAILURE
import static com.kms.katalon.core.model.FailureHandling.OPTIONAL
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
import mk.katalon.helpers.FailureHandler as FailureHandler
import mk.katalon.helpers.HealingInsightHelper as HealingInsightHelper
import org.openqa.selenium.Keys as Keys

Mobile.startApplication('/Users/mohit/Documents/APK/APIDemos2.apk', true)

CustomKeywords.'MobileHelper.tap'(findTestObject('Object Repository/Mobile/APIDemos/android.widget.Button - OK'), 0)

CustomKeywords.'MobileHelper.tap'(findTestObject('Object Repository/Mobile/APIDemos/android.widget.TextView - OK'), 0)

not_run: CustomKeywords.'MobileHelper.tap'(findTestObject('Mobile/APIDemos/Object Healing Mobile'), 0, FailureHandling.CONTINUE_ON_FAILURE)

CustomKeywords.'MobileHelper.tap'(findTestObject('Mobile/APIDemos/android.widget.TextView - restattributeBlankvalue'), 0)

Mobile.pressBack()

CustomKeywords.'MobileHelper.tap'(findTestObject('Object Repository/Mobile/APIDemos/android.widget.TextView - API Demos'), 
    0)

CustomKeywords.'MobileHelper.tap'(findTestObject('Object Repository/Mobile/APIDemos/android.widget.TextView - Security'), 
    0)

CustomKeywords.'MobileHelper.tap'(findTestObject('Object Repository/Mobile/APIDemos/android.widget.TextView - KeyStore'), 
    0)

CustomKeywords.'MobileHelper.setText'(findTestObject('Object Repository/Mobile/APIDemos/android.widget.EditText'), 'Katalon Demo', 
    0)

Mobile.pressBack()

Mobile.pressBack()

Mobile.pressBack()

Mobile.closeApplication()

