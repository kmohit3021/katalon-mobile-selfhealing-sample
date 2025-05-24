
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.testobject.MobileTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil

import io.appium.java_client.AppiumDriver
import mk.katalon.helpers.FailureHandler
import mk.katalon.helpers.HealingInsightHelper
import mk.katalon.helpers.LocatorStrategyBuilder
import mk.katalon.helpers.MobileDriverHelper
import mk.katalon.helpers.MobileElementFinder

import com.kms.katalon.core.model.FailureHandling
import org.openqa.selenium.WebElement

class MobileHelper {

	@Keyword
	def tap(TestObject to, int timeout, FailureHandling failureHandling = FailureHandling.STOP_ON_FAILURE) {
		performAction(to, timeout, failureHandling, 'tap')
	}

	@Keyword
	def setText(TestObject to, String text, int timeout, FailureHandling failureHandling = FailureHandling.STOP_ON_FAILURE) {
		performAction(to, timeout, failureHandling, 'setText', text)
	}

	@Keyword
	def sendKeys(TestObject to, String text, int timeout, FailureHandling failureHandling = FailureHandling.STOP_ON_FAILURE) {
		performAction(to, timeout, failureHandling, 'sendKeys', text)
	}

	private void performAction(TestObject to, int timeout, FailureHandling failureHandling, String action, String text = "") {
		if (to == null) {
			FailureHandler.handle(failureHandling, "Test Object is null.")
			return
		}

		boolean success = false
		String proposedLocator = ""
		String defaultLocator = ""
		String testObjectPath = to.getObjectId()
		//def platform = MobileDriverFactory.getDevicePlatform()
		def platform = MobileDriverHelper.getDeviceOS()
		AppiumDriver driver = MobileDriverFactory.getDriver()
		def weakLocator = MobileElementFinder.isWeakDefaultLocator(to)
		def locators = MobileElementFinder.extractLocatorsFromTestObject(to)
		Map<String, String> attributes = MobileElementFinder.getAttributeValues(to)
		def strategies = LocatorStrategyBuilder.buildStrategies(locators, platform, attributes)

		// First, try default Katalon action
		if(!weakLocator) {
			try {
				switch (action) {

					case 'tap':
						Mobile.tap(to, timeout)
						break
					case 'setText':
						Mobile.setText(to, text, timeout)
						break
					case 'sendKeys':
						Mobile.sendKeys(to, text, timeout)
						break
				}
				success = true
			} catch (Exception e) {
				MobileTestObject testobject= to
				defaultLocator = testobject.getMobileLocator()
				KeywordUtil.logInfo("Default method failed to interact with the object.")
			}
		}
		else {
			MobileTestObject testobject= to
			defaultLocator = testobject.getMobileLocator()
			KeywordUtil.logInfo("Avoid using default locator—it's unreliable and may cause false positives.")
		}

		// If Katalon default fails, retry with other strategies
		if (!success) {
			for (def strategy : strategies) {
				try {
					WebElement element = driver.findElement(strategy)
					switch (action) {
						case 'tap':
							element.click()
							break
						case 'setText':
						case 'sendKeys':
							element.sendKeys(text)
							break
					}

					proposedLocator = strategy.toString()
					success = true
					KeywordUtil.logInfo("Succeeded with: ${strategy}")
					break
				} catch (Exception e) {
					KeywordUtil.logInfo("Retry failed on: ${strategy}")
				}
			}
		}

		if (!success) {
			FailureHandler.handle(failureHandling, "Failed to perform '${action}' on ${to.getObjectId()}")
		}
		if (defaultLocator != proposedLocator) {
			HealingInsightHelper.logActionFailure(testObjectPath, defaultLocator, proposedLocator)
		}
	}
}
