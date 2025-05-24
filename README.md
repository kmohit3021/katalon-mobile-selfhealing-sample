**We are excited to announce that the Mobile Self-Healing feature is now ready for initial review. This implementation closely aligns with the existing Web Self-Healing approach and is built to enhance resilience in test execution.**

If the default locator strategy fails, the plugin automatically attempts alternative methods such as:

    Attributes
    
    XPath
    
    Android UI Automator
    
    Accessibility identifiers
    
    iOS Predicate String
    
    Custom

    And more

**Additionally, the plugin generates a detailed log file capturing:**

    Timestamp
    
    Original Object ID
    
    Broken Object
    
    Proposed Object

The approval flow for approving or discarding proposed objects is still under development.

**How to Use the Plugin:**

1. Download the plugin [[link here](https://github.com/kmohit3021/SharedToALL/blob/main/katalon_mobile_selfhealing_sample-all.jar)] and place it in your project's Plugins folder.

2. Reload and refresh the project in Katalon Studio.

3. Update your test steps as follows:

    Mobile.tap → CustomKeywords.'MobileHelper.tap'
    
    Mobile.setText → CustomKeywords.'MobileHelper.setText'
    
    Mobile.sendKeys → CustomKeywords.'MobileHelper.sendKeys'

4. Intentionally break the object locator in your test to trigger the healing mechanism.

5. Execute your test case and observe the self-healing behavior in action.

6. After execution, review the file action_fallback_log.txt in your project directory for healing details.

**I’d love to hear your feedback on this early version. If you find it valuable, I’ll proceed with the official plugin release to make it available to all users.**
