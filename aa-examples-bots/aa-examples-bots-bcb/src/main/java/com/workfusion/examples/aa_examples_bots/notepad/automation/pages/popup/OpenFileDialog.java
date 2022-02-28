package com.workfusion.examples.aa_examples_bots.notepad.automation.pages.popup;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.elements.web.common.Button;
import com.workfusion.automation.rpa.elements.web.common.TextBox;
import com.workfusion.automation.rpa.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class OpenFileDialog extends AbstractPage {

    private final Button btnOpen = new Button(driver, By.cssSelector(".Button[name=\"Open\"]"), "Open");
    private final Button btnCancel = new Button(driver, By.cssSelector(".Button[name=\"Cancel\"]"), "Cancel");
    private final TextBox txbFileName = new TextBox(driver, By.cssSelector(".Edit[name=\"File name:\"]"), "File name");

    private static final String DIALOG_SELECTOR = "[class=\"#32770\"][title=\"Open\"]";

    public OpenFileDialog(DriverWrapper driver) {
        super(driver);
    }

    public boolean switchToDialog() {
        return driver.switchToWindow(DIALOG_SELECTOR);
    }

    public void openFile(String pathToFile) {
        //Input file name, click button "Open" and wait for open file dialog is closed
        String currentWindowHandle = driver.getWindowHandle();
        txbFileName.setText(pathToFile);
        txbFileName.sendKeys(Keys.TAB);
        btnOpen.click();
        driver.waitForWindowToClose(currentWindowHandle, driver.getDriverSettings().getWindowSwitchWaitSeconds());
    }
}
