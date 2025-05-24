package mk.katalon.helpers

import io.appium.java_client.AppiumBy
import org.openqa.selenium.By

class LocatorStrategyBuilder {

	static List<By> buildStrategies(Map<String, String> locators, String platform, Map<String, String> attributes) {
		List<By> strategies = []
		def locatorConditions = []

		if (platform.equalsIgnoreCase("Android")) {
			if (locators["ATTRIBUTES"]) strategies << By.xpath(locators["ATTRIBUTES"])
			if (locators["XPATH"]) strategies << By.xpath(locators["XPATH"])
			if (locators["ANDROID_UI_AUTOMATOR"]) strategies << AppiumBy.androidUIAutomator(locators["ANDROID_UI_AUTOMATOR"])
			if (locators["ACCESSIBILITY"]) strategies << AppiumBy.accessibilityId(locators["ACCESSIBILITY"])
			if (locators["NAME"]) strategies << AppiumBy.name(locators["NAME"])
		} else if (platform.equalsIgnoreCase("iOS")) {
			if (locators["ATTRIBUTES"]) strategies << By.xpath(locators["ATTRIBUTES"])
			if (locators["XPATH"]) strategies << By.xpath(locators["XPATH"])
			if (locators["IOS_PREDICATE_STRING"]) strategies << AppiumBy.iOSNsPredicateString(locators["IOS_PREDICATE_STRING"])
			if (locators["IOS_CLASS_CHAIN"]) strategies << AppiumBy.iOSClassChain(locators["IOS_CLASS_CHAIN"])
			if (locators["ACCESSIBILITY"]) strategies << AppiumBy.accessibilityId(locators["ACCESSIBILITY"])
			if (locators["NAME"]) strategies << AppiumBy.name(locators["NAME"])
		}

		/*
		 attributes.each { key, val ->
		 locatorConditions << "@${key}='${val}'"
		 }
		 if (!locatorConditions.isEmpty()) {
		 String baseLocator = "(//*[" + locatorConditions.join(' and ') + "])"
		 strategies << By.xpath(baseLocator)
		 }
		 */
		attributes.each { key, val ->
			String stableValue = MobileElementFinder.getBestStableSubstringFromText(val)
			if (stableValue && stableValue != val) {
				locatorConditions << "contains(@${key}, '${stableValue}')"
			} else {
				locatorConditions << "@${key}='${val}'"
			}
		}

		if (!locatorConditions.isEmpty()) {
			String baseLocator = "(//*[" + locatorConditions.join(' and ') + "])"
			strategies << By.xpath(baseLocator)
		}

		return strategies
	}
}
