package com.workfusion.examples.aa_examples_bots.webapplication.automation.pages.invoices;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.elements.web.common.Button;
import com.workfusion.automation.rpa.elements.web.common.ComboBox;
import com.workfusion.automation.rpa.elements.web.common.TextBox;
import com.workfusion.examples.aa_examples_bots.webapplication.automation.dto.InvoiceDto;
import com.workfusion.examples.aa_examples_bots.webapplication.automation.elements.invoices.InvoiceItem;
import com.workfusion.examples.aa_examples_bots.webapplication.automation.pages.BasePage;
import com.workfusion.examples.aa_examples_bots.webapplication.automation.pages.menu.navigation.TopNavigationMenu;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ExtendedInvoiceCreationPage extends BasePage implements TopNavigationMenu {

    private final Button btnAddNewRow = new Button(driver, By.xpath("//div[@id='headerbar']//a[contains(@class, 'btn_add_row')]"),
            "Add new row");
    private final TextBox txbDiscount = new TextBox(driver, By.id("invoice_discount_percent"), "Discount");
    private final ComboBox cmbPaymentMethod = new ComboBox(driver, By.id("payment_method"), "Payment method");
    private final Button btnSave = new Button(driver, By.id("btn_save_invoice"), "Save");

    private static final String LAST_INVOICE_ITEM_SELECTOR_TEMPLATE = "//table[@id='item_table']//tbody[not(@id)][%1$s]";

    private final List<InvoiceItem> invoiceItems = new ArrayList<>();

    public ExtendedInvoiceCreationPage(DriverWrapper driver) {
        super(driver);
        addNewInvoiceItem();
    }

    private void addNewInvoiceItem() {
        String lastInvoiceItemSelector = String.format(LAST_INVOICE_ITEM_SELECTOR_TEMPLATE, invoiceItems.size() + 1);
        invoiceItems.add(new InvoiceItem(driver, By.xpath(lastInvoiceItemSelector), "Invoice Item" + invoiceItems.size() + 1));
    }

    public void fillInvoiceDataAndSave(InvoiceDto invoiceDto) {
        //Set payment method
        cmbPaymentMethod.selectByText(invoiceDto.getPaymentMethod());
        //Set total discount
        txbDiscount.setText(invoiceDto.getTotalDiscount());
        //Iterate through products
        for (int i = 0; i < invoiceDto.getProducts().size(); i++) {
            if (i > 0) {
                //If at least one product already filled with data:
                //click a button to add new row
                btnAddNewRow.click();
                //create new instance of InvoiceItem element to interact with added row
                addNewInvoiceItem();
            }
            //Fill product data on page
            invoiceItems.get(i).setProduct(invoiceDto.getProducts().get(i));
        }
        btnSave.click();
        waitForLoadingSpinnerIsNotDisplayed();
    }
}
