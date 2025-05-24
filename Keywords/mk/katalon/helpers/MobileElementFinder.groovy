package mk.katalon.helpers

import java.util.regex.Matcher
import java.util.regex.Pattern

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.MobileTestObject
import com.kms.katalon.core.testobject.TestObject


class MobileElementFinder {

	/**
	 * Extracts valid locators from a TestObject (.rs XML).
	 */
	static Map<String, String> extractLocatorsFromTestObject(TestObject to) {
		def parser = getXmlParser(to)
		if (!parser) return [:]

		Map<String, String> locators = [:]

		parser.locatorCollection.entry.each {
			String key = it.key.text()?.trim()
			String value = it.value.text()?.trim()

			if (!value || value in [
						'new UiSelector()',
						':new UiSelector()'
					]) return

				if (key == 'ATTRIBUTES') {
					boolean hasClass = value =~ /@class\s*=\s*'[^']+'/
					boolean hasOtherAttrs = value =~ /@(?!class)[^=]+\s*=\s*'(?!\s*')/

					if (hasClass && !hasOtherAttrs) return
				}

			locators[key] = value
		}

		return locators
	}

	/**
	 * Checks if the default ATTRIBUTES locator is weak (i.e., only @class present).
	 */
	static boolean isWeakDefaultLocator(TestObject to) {
		if (!(to instanceof MobileTestObject)) return false

		MobileTestObject mto = (MobileTestObject) to
		if (!mto.getSelectorMethod()?.getName()?.equalsIgnoreCase("Attributes")) return false

		String defaultLocator = mto.getMobileLocator()
		if (!defaultLocator) return false

		boolean hasClass = defaultLocator =~ /@class\s*=\s*'[^']+'/
		boolean hasOtherAttrs = defaultLocator =~ /@(?!class)[^=]+\s*=\s*'(?!\s*')/

		return hasClass && !hasOtherAttrs
	}

	/**
	 * Retrieves commonly used attributes from the TestObject definition.
	 */
	static Map<String, String> getAttributeValues(TestObject to) {
		def parser = getXmlParser(to)
		if (!parser) return [:]

		List<String> attributesToExtract = [
			'content-desc',
			'class',
			'resource-id',
			'type',
			'label',
			'text'
		]
		Map<String, String> result = [:]

		attributesToExtract.each { attr ->
			def node = parser.webElementProperties.find { it.name.text() == attr }
			String value = node?.value?.text()?.trim()
			if (value) result[attr] = value
		}

		return result
	}

	static String getBestStableSubstringFromText(String input) {
		// Define known dynamic patterns (e.g., prices, dates, numbers, UUIDs)
		def dynamicPatterns = [
			~/^₹?\d+(\.\d{2})?$/,
			// Price like ₹49.00
			~/^\d+$/,
			// Just numbers
			~/\b(\d{1,2}[\/\-]\d{1,2}[\/\-]\d{2,4})\b/,
			// Date
			~/\b(\d{1,2}:\d{2}(:\d{2})?)\b/,
			// Time
			~/[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}/,
			// UUID
			~/^\s*$/,                                       // Empty or whitespace
		]

		// Identify if a given text is dynamic
		def isTextDynamic = { String text ->
			text = text.trim()
			if (text.length() < 5 && !text.matches(/.*[a-zA-Z].*/)) return true

			for (pattern in dynamicPatterns) {
				if (text ==~ pattern) return true
			}

			// If numeric characters dominate, it's likely dynamic
			def letters = text.findAll { Character.isLetter(it as char) }.size()
			def digits = text.findAll { Character.isDigit(it as char) }.size()
			return digits > letters
		}

		// Split input into individual candidate tokens
		def tokens = input.split(/[,|;\n]/)
				.collect { it.trim() }
				.findAll { it && !isTextDynamic(it) } // keep only stable values

		if (tokens.isEmpty()) return null // nothing stable found

		// Scoring function (prioritize XPath safety, length, and alphabetic richness)
		def scoreText = { String text ->
			int score = 0
			if (!text.contains('"') && !text.contains("'")) score += 2
			if (text.length() > 15) score += 2
			if (text.matches(/.*[a-zA-Z].*/)) score += 3
			return score
		}

		// Choose best among stable tokens
		def best = tokens.collect { [text: it, score: scoreText(it)] }
		.max { it.score }

		return best?.text
	}
	

	/**
	 * Reads and parses the XML content of the .rs file for a given TestObject.
	 */
	private static def getXmlParser(TestObject to) {
		try {
			String testObjectPath = to.getObjectId()
			File xmlFile = new File(RunConfiguration.getProjectDir(), testObjectPath + '.rs')
			if (!xmlFile.exists()) return null

			String xmlContent = xmlFile.getText('UTF-8')
			return new groovy.xml.XmlSlurper().parseText(xmlContent)
		} catch (Exception e) {
			println "Error parsing TestObject XML: ${e.message}"
			return null
		}
	}
}
