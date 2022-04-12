package com.workfusion.examples.aa_examples_bots.google.automation.client;

import com.workfusion.automation.rpa.driver.DriverType;
import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.driver.RobotDriverWrapper;
import com.workfusion.examples.aa_examples_bots.google.automation.pages.MainPage;
import com.workfusion.rpa.helpers.RPA;
import org.slf4j.Logger;

public class GoogleClient {

    private final DriverWrapper wrapper;

    public GoogleClient(Logger logger) {
        this.wrapper = new RobotDriverWrapper(logger);
    }

    public MainPage getMainPage(String url) {
        //Switch driver to CHROME and open provided url
        wrapper.switchDriver(DriverType.CHROME);
        RPA.openChrome(url);
        return new MainPage(wrapper);
    }
}
