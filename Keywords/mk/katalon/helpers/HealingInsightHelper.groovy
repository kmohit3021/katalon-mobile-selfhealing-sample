package mk.katalon.helpers

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil

import java.nio.file.*
import java.text.SimpleDateFormat

public class HealingInsightHelper {

	private static final String LOG_FILE_NAME = "action_fallback_log.html"
	private static final String LOG_FILE_PATH = RunConfiguration.getProjectDir() + "/" + LOG_FILE_NAME
	private static boolean headerWritten = false

	private static String getCurrentTimestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
	}

	private static void ensureHeaderExists() {
		Path logFilePath = Paths.get(LOG_FILE_PATH)
		if (!Files.exists(logFilePath)) {
			String header = """<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Healing Insight Log</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f5f5f5; padding: 20px; }
        h2 { color: #333; }
        table { width: 100%; border-collapse: collapse; background: white; }
        th, td { padding: 10px; border: 1px solid #ccc; text-align: left; }
        th { background-color: #007BFF; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <h2>Healing Insight Log</h2>
    <table>
        <thead>
            <tr>
                <th>Timestamp</th>
                <th>Object ID</th>
                <th>Broken Locator</th>
                <th>Proposed Locator</th>
            </tr>
        </thead>
        <tbody>
"""
			Files.write(logFilePath, header.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE)
			headerWritten = true
		}
	}

	private static void appendLogRow(String timestamp, String objectId, String brokenLocator, String proposedLocator) {
		String row = """
        <tr>
            <td>${timestamp}</td>
            <td>${objectId}</td>
            <td>${brokenLocator}</td>
            <td>${proposedLocator}</td>
        </tr>
"""
		Path logFilePath = Paths.get(LOG_FILE_PATH)
		Files.write(logFilePath, row.getBytes(), StandardOpenOption.APPEND)
	}

	private static void ensureFooterExists() {
		Path logFilePath = Paths.get(LOG_FILE_PATH)
		List<String> lines = Files.readAllLines(logFilePath)
		if (!lines.any { it.contains("</tbody>") }) {
			Files.write(logFilePath, "</tbody>\n</table>\n</body>\n</html>".getBytes(), StandardOpenOption.APPEND)
		}
	}

	public static void logActionFailure(String testObjectPath, String preferredMethod, String workingMethod) {
		String formattedPath = testObjectPath.replace("Object Repository/", "")
		String timestamp = getCurrentTimestamp()

		try {
			ensureHeaderExists()
			appendLogRow(timestamp, formattedPath, preferredMethod, workingMethod)
			KeywordUtil.logInfo("Logged Action fallback to HTML report")
		} catch (IOException e) {
			KeywordUtil.markWarning("Failed to log action failure to HTML: " + e.getMessage())
		}
	}
}
