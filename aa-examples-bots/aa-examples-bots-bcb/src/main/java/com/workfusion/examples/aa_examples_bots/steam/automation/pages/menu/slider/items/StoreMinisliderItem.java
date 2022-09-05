package com.workfusion.examples.aa_examples_bots.steam.automation.pages.menu.slider.items;

import org.openqa.selenium.By;

public enum StoreMinisliderItem {

    NEW_AND_TRENDING("tab_newreleases_content_trigger,tab_select_NewReleases", "tab_newreleases_content,tab_content_NewReleases", "New & Trending"),
    TOP_SELLERS("tab_topsellers_content_trigger,tab_select_TopSellers", "tab_topsellers_content,tab_content_TopSellers", "Top Sellers");

    private final String itemIds;
    private final String holderIds;
    private final String name;

    private static final String TAB_ITEM_NEW_STYLE_XPATH_SELECTOR_TEMPLATE
            = "//*[contains(@class,'FlavorLabel') and text() = '%1$s']";

    private static final String TAB_ITEM_OLD_STYLE_XPATH_SELECTOR_TEMPLATE
            = "//*[@id='%1$s' or @id='%2$s']";

    StoreMinisliderItem(String itemIds, String holderIds, String name) {
        this.itemIds = itemIds;
        this.holderIds = holderIds;
        this.name = name;
    }

    public By getNewStyleTabLocator() {
        return By.xpath(String.format(TAB_ITEM_NEW_STYLE_XPATH_SELECTOR_TEMPLATE, getName()));
    }

    public By getOldStyleTabLocator() {
        return By.xpath(getTabOrTabContentXpath(this.itemIds));
    }

    public By getOldStyleTabHolderLocator() {
        return By.xpath(getTabOrTabContentXpath(this.holderIds));
    }

    public String getName() {
        return name;
    }

    private String getTabOrTabContentXpath(String idsSplitByCommas) {
        String[] ids = idsSplitByCommas.split(",");
        return String.format(TAB_ITEM_OLD_STYLE_XPATH_SELECTOR_TEMPLATE, ids[0], ids[1]);
    }
}
