package com.workfusion.examples.aa_examples_bots.scrapwebtable.automation.client;

import com.workfusion.automation.rpa.driver.DriverType;
import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.driver.RobotDriverWrapper;
import com.workfusion.examples.aa_examples_bots.scrapwebtable.automation.pages.W3SchoolsTablesPage;
import com.workfusion.rpa.helpers.RPA;
import org.slf4j.Logger;

public class W3SchoolsClient {

    private final DriverWrapper wrapper;

    private static final String TABLES_PAGE_URL = "https://www.w3schools.com/html/html_tables.asp";

    public W3SchoolsClient(Logger logger) {
        this.wrapper = new RobotDriverWrapper(logger);
    }

    //Switches driver to ChromeDriver, opens url in ChromeBrowser
    //and Returns instance of class represents appropriate page object
    public W3SchoolsTablesPage getTablesPage() {
        wrapper.switchDriver(DriverType.CHROME);
        RPA.openChrome(TABLES_PAGE_URL);
        return new W3SchoolsTablesPage(wrapper);
    }
}
