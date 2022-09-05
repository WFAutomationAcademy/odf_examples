package com.workfusion.examples.aa_examples_bots.notepad.automation.pages.popup;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.elements.web.common.Button;
import com.workfusion.automation.rpa.pages.AbstractPage;
import org.openqa.selenium.By;

public class SaveFilePromptPopup extends AbstractPage {

    private final Button btnSave = new Button(driver, By.cssSelector(".Button[text=\"Save\"]"), "Save");
    private final Button btnDontSave = new Button(driver, By.cssSelector(".Button[text=\"Don't Save\"]"),
            "Don't Save");
    private final Button btnCancel = new Button(driver, By.cssSelector(".Button[text=\"Cancel\"]"), "Cancel");

    private static final String POPUP_SELECTOR = "[class=\"#32770\"][name=\"Notepad\"]";

    public SaveFilePromptPopup(DriverWrapper driver) {
        super(driver);
    }

    public boolean switchToDialog() {
        return driver.switchToWindow(POPUP_SELECTOR);
    }

    public void dontSave() {
        String currentWindowHandle = driver.getWindowHandle();
        btnDontSave.click();
        driver.waitForWindowToClose(currentWindowHandle, driver.getDriverSettings().getWindowSwitchWaitSeconds());
    }

    public void save() {
        String currentWindowHandle = driver.getWindowHandle();
        btnSave.click();
        driver.waitForWindowToClose(currentWindowHandle, driver.getDriverSettings().getWindowSwitchWaitSeconds());
    }
}
