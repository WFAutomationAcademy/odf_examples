package com.workfusion.examples.aa_examples_bots.steam.automation.pages;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.examples.aa_examples_bots.steam.automation.pages.menu.navigation.StoreNavigationMenu;
import com.workfusion.examples.aa_examples_bots.steam.automation.pages.menu.slider.StoreHorizontalMinislider;

public class MainPage extends BasePage implements StoreNavigationMenu, StoreHorizontalMinislider {

    public MainPage(DriverWrapper driver) {
        super(driver);
    }
}
