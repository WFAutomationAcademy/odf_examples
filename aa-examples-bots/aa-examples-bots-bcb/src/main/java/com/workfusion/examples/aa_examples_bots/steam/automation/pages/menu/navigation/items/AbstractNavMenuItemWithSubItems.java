package com.workfusion.examples.aa_examples_bots.steam.automation.pages.menu.navigation.items;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.elements.web.common.Label;
import com.workfusion.examples.aa_examples_bots.steam.automation.pages.BasePage;
import org.openqa.selenium.By;

public abstract class AbstractNavMenuItemWithSubItems extends BasePage {

    private final String itemName;

    private static final String NAV_MENU_ITEM_SELECTOR_TEMPLATE = "(//a[contains(.,'%s')]/ancestor::*[contains(@class,'tab')])[last()]";
    private static final String NAV_MENU_SUB_ITEM_SELECTOR_TEMPLATE = "//a[@class='popup_menu_item' and contains(text(),'%s')]";

    public AbstractNavMenuItemWithSubItems(DriverWrapper driver, String itemName) {
        super(driver, true);
        this.itemName = itemName;
    }

    //Navigates to requested page using menu at the top of page and returns new instance of requested page
    protected <T extends BasePage> T navigateTo(String subItemName, Class<T> destinationPage) {
        //Create menu item selector using menu name
        String itemXpathSelector = String.format(NAV_MENU_ITEM_SELECTOR_TEMPLATE, itemName);
        //Dispatch "Mouse Over" event on menu item to expand sub items
        new Label(driver, By.xpath(itemXpathSelector), itemName).dispatchMouseOverEvent();
        //Create menu sub item selector using sub item name
        String subItemXpathSelector = String.format(NAV_MENU_SUB_ITEM_SELECTOR_TEMPLATE, subItemName);
        //Click sub item
        new Label(driver, By.xpath(subItemXpathSelector), subItemName).click();
        //Get instance of target page and return it
        return getPageInstanceByClass(destinationPage);
    }
}
