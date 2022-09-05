package com.workfusion.examples.aa_examples_bots.steam.automation.pages.menu.navigation.items;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.examples.aa_examples_bots.steam.automation.pages.SpecialOffersPage;
import com.workfusion.examples.aa_examples_bots.steam.automation.pages.TopSellersPage;

public class NewAndNoteworthy extends AbstractNavMenuItemWithSubItems {

    private static final String TOP_SELLERS_SUB_ITEM = "Top Sellers";
    private static final String CURRENT_SPECIALS_SUB_ITEM = "Current Specials";

    public NewAndNoteworthy(DriverWrapper driver) {
        super(driver, "New & Noteworthy");
    }

    public TopSellersPage topSellers() {
        return navigateTo(TOP_SELLERS_SUB_ITEM, TopSellersPage.class);
    }

    public SpecialOffersPage currentSpecials() {
        return navigateTo(CURRENT_SPECIALS_SUB_ITEM, SpecialOffersPage.class);
    }
}
