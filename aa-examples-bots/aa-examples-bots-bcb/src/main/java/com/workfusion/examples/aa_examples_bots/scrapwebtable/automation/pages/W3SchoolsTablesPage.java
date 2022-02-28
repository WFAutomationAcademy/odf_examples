package com.workfusion.examples.aa_examples_bots.scrapwebtable.automation.pages;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.elements.web.common.table.Table;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Map;

public class W3SchoolsTablesPage extends BasePage {

    private final Table tblCustomers = new Table(driver, By.id("customers"), "Customers");

    public W3SchoolsTablesPage(DriverWrapper driver) {
        super(driver);
    }

    //Get table values by executing appropriate method of Table object
    public List<Map<String, String>> getCustomersTableData() {
        return this.tblCustomers.getTableAsListOfMaps();
    }
}
