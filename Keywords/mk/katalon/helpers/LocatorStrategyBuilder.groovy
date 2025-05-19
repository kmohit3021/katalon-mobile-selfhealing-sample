package mk.katalon.helpers

import io.appium.java_client.AppiumBy
import org.openqa.selenium.By

class LocatorStrategyBuilder {

	static List<By> buildStrategies(Map<String, String> locators, String platform) {
		List<By> strategies = []

		if (platform.equalsIgnoreCase("Android")) {
			if (locators["ATTRIBUTES"]) strategies << By.xpath(locators["ATTRIBUTES"])
			if (locators["XPATH"]) strategies << By.xpath(locators["XPATH"])
			if (locators["NAME"]) strategies << AppiumBy.name(locators["NAME"])
			//if (locators["CLASS_NAME"]) strategies << By.className(locators["CLASS_NAME"])
			//if (locators["ID"]) strategies << By.id(locators["ID"])
			if (locators["ACCESSIBILITY"]) strategies << AppiumBy.accessibilityId(locators["ACCESSIBILITY"])
			if (locators["ANDROID_UI_AUTOMATOR"]) strategies << AppiumBy.androidUIAutomator(locators["ANDROID_UI_AUTOMATOR"])
		} else if (platform.equalsIgnoreCase("iOS")) {
			if (locators["ATTRIBUTES"]) strategies << By.xpath(locators["ATTRIBUTES"])
			if (locators["XPATH"]) strategies << By.xpath(locators["XPATH"])
			if (locators["ACCESSIBILITY"]) strategies << AppiumBy.accessibilityId(locators["ACCESSIBILITY"])
			if (locators["NAME"]) strategies << AppiumBy.name(locators["NAME"])
			//if (locators["ID"]) strategies << By.id(locators["ID"])
			//if (locators["CLASS_NAME"]) strategies << By.className(locators["CLASS_NAME"])
			if (locators["IOS_PREDICATE_STRING"]) strategies << AppiumBy.iOSNsPredicateString(locators["IOS_PREDICATE_STRING"])
			if (locators["IOS_CLASS_CHAIN"]) strategies << AppiumBy.iOSClassChain(locators["IOS_CLASS_CHAIN"])
		}

		return strategies
	}
}
