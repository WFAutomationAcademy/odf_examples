package com.workfusion.examples.aa_examples_bots.webapplication.automation.pages.invoices;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.elements.web.common.Button;
import com.workfusion.automation.rpa.elements.web.common.ComboBox;
import com.workfusion.automation.rpa.elements.web.common.TextBox;
import com.workfusion.examples.aa_examples_bots.webapplication.automation.dto.InvoiceDto;
import com.workfusion.examples.aa_examples_bots.webapplication.automation.elements.invoices.ClientComboBox;
import com.workfusion.examples.aa_examples_bots.webapplication.automation.pages.BasePage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class CreateInvoicePage extends BasePage {

    private final ClientComboBox cmbClient = new ClientComboBox(driver);
    private final TextBox txbInvoiceDate = new TextBox(driver, By.id("invoice_date_created"), "Invoice Date");
    private final TextBox txbPDFPassword = new TextBox(driver, By.id("invoice_password"), "PDF password");
    private final ComboBox cmbInvoiceGroup = new ComboBox (driver, By.id("invoice_group_id"), "Invoice Group");
    private final Button btnConfirm = new Button(driver, By.id("invoice_create_confirm"), "Confirm");

    public CreateInvoicePage(DriverWrapper driver) {
        super(driver);
    }

    public ExtendedInvoiceCreationPage createInvoice(InvoiceDto invoiceDto) {
        //Set client name
        cmbClient.setClient(invoiceDto.getClient());

        //Set invoice date
        txbInvoiceDate.setText(invoiceDto.getInvoiceDate());
        txbInvoiceDate.sendKeys(Keys.ENTER);

        //Set PDF password field if it's set in invoiceDto (optional field)
        if (StringUtils.isNotBlank(invoiceDto.getPdfPassword())) {
            txbPDFPassword.setText(invoiceDto.getPdfPassword());
        }

        //Set invoice group
        cmbInvoiceGroup.selectByText(invoiceDto.getInvoiceGroup());

        btnConfirm.click();

        return new ExtendedInvoiceCreationPage(driver);
    }
}
