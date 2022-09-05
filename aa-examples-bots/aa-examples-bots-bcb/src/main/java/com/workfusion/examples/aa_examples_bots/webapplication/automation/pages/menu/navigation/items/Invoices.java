package com.workfusion.examples.aa_examples_bots.webapplication.automation.pages.menu.navigation.items;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.examples.aa_examples_bots.webapplication.automation.pages.invoices.CreateInvoicePage;

//Class represents "Invoices" menu
public class Invoices extends AbstractTopNavigationSubItem {

    //Create invoice menu sub item name
    private static final String CREATE_INVOICE_SUB_ITEM = "Create Invoice";

    public Invoices(DriverWrapper driver) {
        super(driver, "Invoices");
    }

    public CreateInvoicePage createInvoice() {
        return navigateTo(CREATE_INVOICE_SUB_ITEM, CreateInvoicePage.class);
    }
}
