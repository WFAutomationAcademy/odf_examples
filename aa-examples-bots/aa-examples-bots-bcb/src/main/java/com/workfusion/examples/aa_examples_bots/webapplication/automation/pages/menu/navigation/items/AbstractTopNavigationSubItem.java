package com.workfusion.examples.aa_examples_bots.webapplication.automation.pages.menu.navigation.items;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.elements.web.common.Label;
import com.workfusion.examples.aa_examples_bots.webapplication.automation.pages.BasePage;
import org.openqa.selenium.By;

public abstract class AbstractTopNavigationSubItem extends BasePage {

    private static final String NAV_MENU_ITEM_SELECTOR_TEMPLATE = "//div[@id='ip-navbar-collapse']//li[a/span[text()='%1$s']]";
    private static final String NAV_MENU_SUB_ITEM_RELATIVE_SELECTOR_TEMPLATE = "./descendant::*[contains(text(), 'Create Invoice')]";

    private final String itemName;

    public AbstractTopNavigationSubItem(DriverWrapper driver, String itemName) {
        super(driver);
        this.itemName = itemName;
    }

    //Navigates to requested page using menu at the top of page and returns new instance of requested page
    protected <T extends BasePage> T navigateTo(String subItemName, Class<T> destinationPage) {
        //Create menu item selector using menu name
        String itemXpathSelector = String.format(NAV_MENU_ITEM_SELECTOR_TEMPLATE, itemName);
        Label lblMenuItem = new Label(driver, By.xpath(itemXpathSelector), "Menu " + itemName);
        //Check if menu is already opened or not
        if (!lblMenuItem.getAttribute("class").contains("open")) {
            //Click if not opened
            lblMenuItem.click();
        }
        //Create menu sub item selector using sub item name
        String subMenuItemName = String.format("Menu %1$s item %2$s", itemName, subItemName);
        Label lblSubItem = new Label(lblMenuItem, By.xpath(NAV_MENU_SUB_ITEM_RELATIVE_SELECTOR_TEMPLATE), subMenuItemName);
        //Click sub item
        lblSubItem.click();
        //Get instance of target page and return it
        return getPageInstanceByClass(destinationPage);
    }
}
