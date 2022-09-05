package com.workfusion.examples.aa_examples_bots.steam.automation.pages.menu.slider.items;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.elements.ElementState;
import com.workfusion.automation.rpa.elements.web.common.Label;
import com.workfusion.examples.aa_examples_bots.steam.automation.elements.MinisliderGameItem;
import com.workfusion.examples.aa_examples_bots.steam.automation.exceptions.SteamApplicationException;
import com.workfusion.examples.aa_examples_bots.steam.automation.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

public class MinisliderGamesList extends BasePage {

    private static final String GAMES_ON_NEW_STYLE_ACTIVE_TAB_COMMON_XPATH_SELECTOR
            = "//*[contains(@class,'SaleItemBrowserRow')]";

    private static final String GAMES_ON_OLD_STYLE_ACTIVE_TAB_COMMON_XPATH_SELECTOR
            = "//*[(@class='tab_content_section_ctn' or @class='tab_content') and not(contains(@style,'display: none;'))]//a[contains(@class,'tab_item')]";

    public MinisliderGamesList(DriverWrapper driver) {
        super(driver);
    }

    public void openTab(StoreMinisliderItem storeMinisliderItem) {
        //Scroll Game tiles holder into view to trigger loading of tiles
        lblGameTilesHolder.scrollIntoView();

        AtomicBoolean isNewStyle = new AtomicBoolean(false);
        AtomicReference<By> tabLocator = new AtomicReference<>();
        //Find needed tab and check style (Tabs with game tiles can be 2 types. Logic of interaction will be different)
        boolean isTabFound = wait.waitForTrue(y -> {
            if (!driver.findElementsNative(driver.getDriver(), storeMinisliderItem.getOldStyleTabLocator()).isEmpty()) {
                isNewStyle.set(false);
                tabLocator.set(storeMinisliderItem.getOldStyleTabLocator());
                return true;
            }
            if (!driver.findElementsNative(driver.getDriver(), storeMinisliderItem.getNewStyleTabLocator()).isEmpty()) {
                isNewStyle.set(true);
                tabLocator.set(storeMinisliderItem.getNewStyleTabLocator());
                return true;
            }
            return false;
        });

        if (!isTabFound) {
            throw new SteamApplicationException("Tab " + storeMinisliderItem.getName() + " wasn't found");
        }


        Label lblTabName = new Label(driver, tabLocator.get(), storeMinisliderItem.getName());
        //Check if needed tab is already opened or not
        String tabClassAttribute = lblTabName.getAttribute("class");
        if (!(tabClassAttribute.contains("SelectedFlavor") || tabClassAttribute.contains("active"))) {
            scrollIntoViewCenter(getAsWebElement(lblTabName, ElementState.EXISTS));
            lblTabName.click();

            if (isNewStyle.get()) {//New style tab
                waitForWebPageToLoad();
                //Wait for needed tab is activated
                wait.waitForTrue(y -> lblTabName.getAttribute("class").contains("SelectedFlavor"));
                //Wait for data loading is finished
                waitForLoadingSpinnerDisappears();
            } else {//Old style tab
                Label lblTabContentHolder = new Label(driver, storeMinisliderItem.getOldStyleTabHolderLocator(),
                        storeMinisliderItem.getName() + " content holder");
                //Wait for needed tab is activated
                wait.waitForTrue(y -> lblTabName.getAttribute("class").contains("active"));
                //Wait for content is switched to required
                wait.waitForTrue(y -> lblTabContentHolder.isVisible());

            }
        }
    }

    public List<MinisliderGameItem> getAllGamesFromActiveTab() {
        AtomicBoolean isNewStyle = new AtomicBoolean(false);
        //Find needed tab and check style (Tabs with game tiles can be 2 types. Logic of interaction will be different)
        boolean isGameFound = wait.waitForTrue(y -> {
            if (!driver.findElementsNative(driver.getDriver(),
                    By.xpath(GAMES_ON_OLD_STYLE_ACTIVE_TAB_COMMON_XPATH_SELECTOR)).isEmpty()) {
                isNewStyle.set(false);
                return true;
            }
            if (!driver.findElementsNative(driver.getDriver(),
                    By.xpath(GAMES_ON_NEW_STYLE_ACTIVE_TAB_COMMON_XPATH_SELECTOR)).isEmpty()) {
                isNewStyle.set(true);
                return true;
            }
            return false;
        });

        if (!isGameFound) {
            throw new SteamApplicationException("Game tiles weren't found");
        }

        String gamesSelector = isNewStyle.get() ? GAMES_ON_NEW_STYLE_ACTIVE_TAB_COMMON_XPATH_SELECTOR
                : GAMES_ON_OLD_STYLE_ACTIVE_TAB_COMMON_XPATH_SELECTOR;
        List<MinisliderGameItem> result = new ArrayList<>();
        //Find all games as list of raw web elements
        List<WebElement> tempElements = driver.findElements(By.xpath(gamesSelector));
        for (int i = 0; i < tempElements.size(); i++) {
            //Create wrapped elements (MinisliderGameItem) to return list of wrapped elements
            String elementUniqueXpathSelector = String.format(gamesSelector + "[%1$s]", i + 1);
            result.add(new MinisliderGameItem(driver, By.xpath(elementUniqueXpathSelector), "Game item " + (i + 1), isNewStyle.get()));
        }
        return result;
    }

    public MinisliderGameItem getFirstGameByCondition(Predicate<? super MinisliderGameItem> predicate) {
        List<MinisliderGameItem> games = getAllGamesFromActiveTab();
        return games.stream().filter(predicate).findFirst().orElse(null);
    }

    public MinisliderGameItem getGameWithMaxDiscount() {
        List<MinisliderGameItem> games = getAllGamesFromActiveTab();
        return games.stream().max(Comparator.comparingInt(MinisliderGameItem::getGameDiscount)).orElse(null);
    }
}
