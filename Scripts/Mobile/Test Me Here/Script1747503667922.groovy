import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable
import mk.katalon.helpers.LocatorStrategyBuilder
import mk.katalon.helpers.LocatorStrategyBuilder as GlobalVariable
import mk.katalon.helpers.MobileDriverHelper
import mk.katalon.helpers.MobileElementFinder




// ************validate locator and strategy*********


def platform = "android"
def locators = MobileElementFinder.extractLocatorsFromTestObject(findTestObject('Object Repository/Mobile/CityMall/android.view.ViewGroup (5)'))
println(locators)
Map<String, String> attributes = MobileElementFinder.getAttributeValues(findTestObject('Object Repository/Mobile/CityMall/android.view.ViewGroup (5)'))
def strategies = LocatorStrategyBuilder.buildStrategies(locators, platform, attributes)

println(strategies)

def platform2 = "android"
def locators2 = MobileElementFinder.extractLocatorsFromTestObject(findTestObject('Object Repository/Mobile/CityMall/android.view.ViewGroup (3)'))
println(locators2)
Map<String, String> attributes2 = MobileElementFinder.getAttributeValues(findTestObject('Object Repository/Mobile/CityMall/android.view.ViewGroup (3)'))
def strategies2 = LocatorStrategyBuilder.buildStrategies(locators2, platform2, attributes2)

println(strategies2)

def platform3 = "android"
def locators3 = MobileElementFinder.extractLocatorsFromTestObject(findTestObject('Object Repository/Mobile/CityMall/android.view.ViewGroup (3)'))
println(locators3)
Map<String, String> attributes3 = MobileElementFinder.getAttributeValues(findTestObject('Object Repository/Mobile/CityMall/android.view.ViewGroup (3)'))
def strategies3 = LocatorStrategyBuilder.buildStrategies(locators3, platform3, attributes3)

println(strategies3)

def platform4 = "android"
def locators4 = MobileElementFinder.extractLocatorsFromTestObject(findTestObject('Object Repository/Mobile/greyorange/New Folder/android.widget.ImageView (2)'))
println(locators4)
Map<String, String> attributes4 = MobileElementFinder.getAttributeValues(findTestObject('Object Repository/Mobile/greyorange/New Folder/android.widget.ImageView (2)'))
def strategies4 = LocatorStrategyBuilder.buildStrategies(locators4, platform4, attributes4)

println(strategies4)

def platform5 = "android"
def locators5 = MobileElementFinder.extractLocatorsFromTestObject(findTestObject('Object Repository/Mobile/CityMall/android.widget.TextView - Get OTP'))
println(locators5)
Map<String, String> attributes5 = MobileElementFinder.getAttributeValues(findTestObject('Object Repository/Mobile/CityMall/android.widget.TextView - Get OTP'))
def strategies5 = LocatorStrategyBuilder.buildStrategies(locators5, platform5, attributes5)

println(strategies5)
