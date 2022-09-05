package com.workfusion.examples.aa_examples_bots.steam.automation.elements;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.elements.AbstractElement;
import com.workfusion.automation.rpa.elements.web.common.Label;
import org.openqa.selenium.By;

//Class represents custom complex MinisliderGameItem element that contains simple elements (labels)
public class MinisliderGameItem extends AbstractElement {

    private final boolean isNewStyle;

    private final Label lblGameName
            = new Label(this, By.xpath("./descendant::*[(@class='tab_item_name') or contains(@class,'StoreSaleWidgetTitle')]"),
            this.getName() + " name");
    private final Label lblGameDiscount
            = new Label(this, By.xpath("./descendant::*[(@class='discount_pct') or contains(@class,'StoreSaleDiscountBox')]"),
            this.getName() + " discount");

    public MinisliderGameItem(DriverWrapper wrapper, By locator, String name, boolean isNewStyle) {
        super(wrapper, locator, name);
        this.isNewStyle = isNewStyle;
    }

    //If you click old style game tile, game details page will be opened in the same window
    //If you click new style game tile, game details page will be opened in new window
    //This should be taken into account during navigation to GameDetailedInfoPage
    public boolean isNewStyle() {
        return this.isNewStyle;
    }

    public String getGameName() {
        return lblGameName.getText();
    }

    public int getGameDiscount() {
        if (!lblGameDiscount.isPresent()) {
            return 0;
        }
        String discountAsString = lblGameDiscount.getText();
        return Integer.parseInt(discountAsString.replaceAll("[\\D]", ""));
    }

    @Override
    public void click() {
        lblGameName.click();
    }

    @Override
    protected String getElementType() {
        return "Minislider game item";
    }
}
