package mk.katalon.helpers


import static com.kms.katalon.core.model.FailureHandling.STOP_ON_FAILURE
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.nio.file.*

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable


public class HealingInsightHelper {

	private static final String CLICK_LOG_FILE_PATH = RunConfiguration.getProjectDir() + "/action_fallback_log.txt"

	private static void logActionFailure(String testObjectPath, String preferredMethod, String workingMethod) {
		String formattedPath = testObjectPath.replace("Object Repository/", "")
		String timestamp = new Date().format("yyyy-MM-dd HH:mm:ss")

		StringBuilder logEntry = new StringBuilder()
		logEntry.append("${timestamp} | ")
		//logEntry.append("TestCase: ${GlobalVariable.TestCaseId ?: 'Unknown'} | ")
		logEntry.append("Object Id: ${formattedPath} | ")
		logEntry.append("Broken Locator: ${preferredMethod} | ")
		logEntry.append("Proposed Locator: ${workingMethod}\n")

		try {
			Path logFilePath = Paths.get(CLICK_LOG_FILE_PATH)
			Files.write(logFilePath, logEntry.toString().getBytes(),
					StandardOpenOption.CREATE, StandardOpenOption.APPEND)
			KeywordUtil.logInfo("Logged Action fallback to file")
		} catch (IOException e) {
			KeywordUtil.markWarning("Failed to log Action failure: " + e.getMessage())
		}
	}
}
