package com.workfusion.examples.aa_examples_bots.google.automation.pages;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.pages.AbstractPage;

public class BasePage extends AbstractPage {

    public BasePage(DriverWrapper driver) {
        super(driver);
        waitForWebPageToLoad();
    }
}
