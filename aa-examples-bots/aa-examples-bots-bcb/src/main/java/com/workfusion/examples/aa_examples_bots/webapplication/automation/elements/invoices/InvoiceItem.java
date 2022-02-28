package com.workfusion.examples.aa_examples_bots.webapplication.automation.elements.invoices;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.elements.AbstractElement;
import com.workfusion.automation.rpa.elements.web.common.TextBox;
import com.workfusion.examples.aa_examples_bots.webapplication.automation.dto.ProductDto;
import org.openqa.selenium.By;

//Class represents custom complex InvoiceItem element that contains simple elements (text boxes)
public class InvoiceItem extends AbstractElement {

    private final TextBox txbItem = new TextBox(this, By.xpath("./descendant::input[@name='item_name']"), getName() + " Item");
    private final TextBox txbQuantity = new TextBox(this, By.xpath("./descendant::input[@name='item_quantity']"), getName() + " Quantity");
    private final TextBox txbPrice = new TextBox(this, By.xpath("./descendant::input[@name='item_price']"), getName() + " Price");
    private final TextBox txbDescription = new TextBox(this, By.xpath("./descendant::textarea[@name='item_description']"), getName() + " Description");

    public InvoiceItem(DriverWrapper wrapper, By locator, String name) {
        super(wrapper, locator, name);
    }

    public InvoiceItem(AbstractElement parentElement, By locator, String name) {
        super(parentElement, locator, name);
    }

    public void setProduct(ProductDto productDto) {
        txbItem.setText(productDto.getItem());
        txbQuantity.setText(productDto.getQuantity());
        txbPrice.setText(productDto.getPrice());
        txbDescription.setText(productDto.getDescription());
    }

    @Override
    protected String getElementType() {
        return "Invoice Item";
    }
}
