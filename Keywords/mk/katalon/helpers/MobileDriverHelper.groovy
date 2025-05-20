package mk.katalon.helpers

import com.kms.katalon.core.configuration.RunConfiguration


public class MobileDriverHelper {
	
	static def getDeviceOS() {
		def executionProperties = RunConfiguration.getExecutionProperties()
		if (executionProperties.containsKey('drivers')) {
			Map<String, Object> drivers = executionProperties.get('drivers')
			if (drivers.containsKey('system')) {
				Map<String, Object> system = drivers.get('system')
				if (system.containsKey('Remote')) {
					Map<String, Object> remote = system.get('Remote')
					if (remote.containsKey('remoteMobileDriver')) {
						String device = remote.get('remoteMobileDriver')
						if (device.contains('ANDROID')) {
							return 'Android'
						} else if (device.contains('IOS')) {
							return 'iOS'
						}
					}
				} else if (system.containsKey('Mobile')) {
					Map<String, Object> mobile = system.get('Mobile')
					if (mobile.containsKey('deviceOS')) {
						return mobile.get('deviceOS')
					}
				}
			}
		}
		return null
	}
}
