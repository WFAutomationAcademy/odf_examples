package com.workfusion.examples.aa_examples_bots.webapplication.automation.elements.invoices;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.elements.AbstractElement;
import com.workfusion.automation.rpa.elements.web.common.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class ClientComboBox extends AbstractElement {

    private final TextBox txbClient = new TextBox(wrapper, By.className("select2-search__field"), COMBO_BOX_NAME);

    private static final By COMBO_BOX_LOCATOR
            = By.xpath("//*[@role='combobox' and ./*[@id='select2-client_name-container']]");
    private static final String COMBO_BOX_NAME = "Client";

    public ClientComboBox(DriverWrapper wrapper) {
        super(wrapper, COMBO_BOX_LOCATOR, COMBO_BOX_NAME);
    }

    public void setClient(String client) {
        //Check if client dropdown is already expanded or not
        if (!"true".equalsIgnoreCase(this.getAttribute("aria-expanded"))) {
            this.click();
        }

        //Set client name
        txbClient.setText(client);
        txbClient.sendKeys(Keys.ENTER);
    }

    @Override
    protected String getElementType() {
        return "Custom Combo Box";
    }
}
