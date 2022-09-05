package com.workfusion.examples.aa_examples_bots.webapplication.automation.client;

import com.workfusion.automation.rpa.driver.DriverType;
import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.driver.RobotDriverWrapper;
import com.workfusion.examples.aa_examples_bots.webapplication.automation.pages.LoginPage;
import com.workfusion.rpa.helpers.RPA;
import org.slf4j.Logger;

public class InvoicePlaneClient {

    private final DriverWrapper wrapper;

    private static final String INVOICE_PLANE_URL = "https://train-invoiceplane.workfusion.com";

    public InvoicePlaneClient(Logger logger) {
        this.wrapper = new RobotDriverWrapper(logger);
    }

    //Switches driver to ChromeDriver, opens url in ChromeBrowser
    //and Returns instance of class represents appropriate page object
    public LoginPage getLoginPage() {
        wrapper.switchDriver(DriverType.CHROME);
        RPA.openChrome(INVOICE_PLANE_URL);
        return new LoginPage(wrapper);
    }
}
