package mk.katalon.helpers

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil

class FailureHandler {

	static void handle(FailureHandling strategy, String message) {
		switch (strategy) {
			case FailureHandling.CONTINUE_ON_FAILURE:
				KeywordUtil.logInfo("Failure Heandling: CONTINUE_ON_FAILURE")
				KeywordUtil.markFailed(message)
				break
			case FailureHandling.STOP_ON_FAILURE:
				KeywordUtil.logInfo("Failure Heandling: STOP_ON_FAILURE")
				KeywordUtil.markFailedAndStop(message)
				break
			case FailureHandling.OPTIONAL:
				KeywordUtil.logInfo("Failure Heandling: OPTIONAL")
				KeywordUtil.markWarning("Optional Failure: " + message)
				break
			default:
				KeywordUtil.markFailedAndStop("Unexpected failure: " + message)
		}
	}
}
