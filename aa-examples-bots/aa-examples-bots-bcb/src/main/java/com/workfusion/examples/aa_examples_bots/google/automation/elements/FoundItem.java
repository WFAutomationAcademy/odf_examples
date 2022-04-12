package com.workfusion.examples.aa_examples_bots.google.automation.elements;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.elements.AbstractElement;
import com.workfusion.automation.rpa.elements.web.common.Label;
import com.workfusion.automation.rpa.elements.web.common.Link;
import org.openqa.selenium.By;

//Class represents custom complex InvoiceItem element that contains simple elements (link and labels)
public class FoundItem extends AbstractElement {

    private final Link lnkTarget = new Link(this, By.xpath("./descendant::a[descendant::h3]"), "Target");
    private final Label lblTitle = new Label(this, By.xpath("./descendant::a//h3"), "Title");
    private final Label lblDescription = new Label(this, By.xpath("./descendant::div[contains(@style,'-webkit-line-clamp')]//span"), "Short description");

    public FoundItem(DriverWrapper wrapper, By locator, String name) {
        super(wrapper, locator, name);
    }

    public String getLink() {
        return lnkTarget.getHref();
    }

    public String getTitle() {
        return lblTitle.getText();
    }

    public String getShortDescription() {
        return lblDescription.getText();
    }

    @Override
    protected String getElementType() {
        return "Found item";
    }
}
