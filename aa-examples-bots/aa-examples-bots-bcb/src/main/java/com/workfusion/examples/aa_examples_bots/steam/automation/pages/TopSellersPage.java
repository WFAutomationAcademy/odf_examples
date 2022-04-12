package com.workfusion.examples.aa_examples_bots.steam.automation.pages;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.examples.aa_examples_bots.steam.automation.pages.menu.navigation.StoreNavigationMenu;
import com.workfusion.examples.aa_examples_bots.steam.automation.pages.menu.slider.StoreHorizontalMinislider;

public class TopSellersPage extends BasePage implements StoreNavigationMenu, StoreHorizontalMinislider {

    public TopSellersPage(DriverWrapper driver) {
        super(driver);
    }
}
