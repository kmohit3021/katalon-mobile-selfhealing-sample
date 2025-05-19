package mk.katalon.helpers

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.TestObject

class MobileElementFinder {

	static Map<String, String> extractLocatorsFromTestObject(TestObject to) {
		def testObjectPath = to.getObjectId()
		String xmlString = new File(RunConfiguration.getProjectDir(), testObjectPath + '.rs').getText('UTF-8')
		def parser = new groovy.xml.XmlSlurper().parseText(xmlString)

		def locators = [:]
		parser.locatorCollection.entry.each {
			def key = it.key.text()
			def value = it.value.text()
			locators[key] = value
		}
		return locators
	}
}
