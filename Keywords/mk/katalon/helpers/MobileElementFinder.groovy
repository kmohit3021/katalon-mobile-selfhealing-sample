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
		// Check if the given text is a price
		def isPrice = { str ->
			str = str?.trim()
			if (!str) return false
			str = str.replace("₹", "")
			try {
				str.toBigDecimal()
				return true
			} catch (Exception e) {
				return false
			}
		}

		// Check if the string is an integer
		def isInteger = { str ->
			try {
				str?.toInteger()
				return true
			} catch (Exception e) {
				return false
			}
		}

		// Check if the string is a date
		def isDate = { str ->
			def formats = [
				'dd/MM/yyyy',
				'MM-dd-yyyy',
				'd/M/yy',
				'dd-MM-yyyy',
				'MM/dd/yyyy'
			]
			for (f in formats) {
				try {
					def sdf = new java.text.SimpleDateFormat(f)
					sdf.setLenient(false)
					sdf.parse(str)
					return true
				} catch (Exception ignored) {}
			}
			return false
		}

		// Check if the string is a time
		def isTime = { str ->
			def formats = ['HH:mm', 'HH:mm:ss', 'h:mm a']
			for (f in formats) {
				try {
					def sdf = new java.text.SimpleDateFormat(f)
					sdf.setLenient(false)
					sdf.parse(str)
					return true
				} catch (Exception ignored) {}
			}
			return false
		}

		// Check if the string is a UUID
		def isUUID = { str ->
			try {
				UUID.fromString(str)
				return true
			} catch (Exception e) {
				return false
			}
		}

		// Check if the string is blank or whitespace
		def isBlank = { str ->
			str?.trim().isEmpty()
		}

		// Identify if a given text is dynamic
		def isTextDynamic = { String text ->
			text = text.trim()
			if (text.length() < 5 && !text.any { Character.isLetter(it as char) }) return true
			if (isBlank(text) || isPrice(text) || isInteger(text) || isDate(text) || isTime(text) || isUUID(text)) return true

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
			if (text.any { Character.isLetter(it as char) }) score += 3
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
