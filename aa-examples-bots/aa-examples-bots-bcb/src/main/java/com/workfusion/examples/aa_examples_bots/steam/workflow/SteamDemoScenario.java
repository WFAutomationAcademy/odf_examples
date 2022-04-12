package com.workfusion.examples.aa_examples_bots.steam.workflow;

import com.workfusion.automation.rpa.driver.DriverSettings;
import com.workfusion.examples.aa_examples_bots.steam.automation.client.SteamClient;
import com.workfusion.examples.aa_examples_bots.steam.automation.elements.MinisliderGameItem;
import com.workfusion.examples.aa_examples_bots.steam.automation.pages.GameDetailedInfoPage;
import com.workfusion.examples.aa_examples_bots.steam.automation.pages.MainPage;
import com.workfusion.examples.aa_examples_bots.steam.automation.pages.SpecialOffersPage;
import com.workfusion.examples.aa_examples_bots.steam.automation.pages.menu.slider.items.StoreMinisliderItem;
import org.slf4j.Logger;

public class SteamDemoScenario {

    public static GameDetailedInfoPage executeSteamDemoScenario(Logger logger) {
        //Create client to be an entry point
        SteamClient client = new SteamClient(logger, getDriverSettings());

        logger.info("Open Steam");
        MainPage mainPage = client.getMainPage();

        logger.info("Navigate to Current Specials");
        //Open "Current Specials" page by interacting with navigation menu
        SpecialOffersPage specialOffersPage = mainPage
                .storeNavigationMenu().newAndNoteworthy().currentSpecials();

        logger.info("Open 'Top Sellers'");
        //Open "Top Sellers" tab by interacting with mini slider menu
        specialOffersPage.minisliderGamesList().openTab(StoreMinisliderItem.TOP_SELLERS);

        //Find game with max discount on current page
        MinisliderGameItem gameItem = specialOffersPage.minisliderGamesList().getGameWithMaxDiscount();

        if (gameItem == null) {
            throw new RuntimeException("A game with max discount wasn't found");
        }
        logger.info("Game with max discount is '{}'. Discount is '{}'%", gameItem.getGameName(), gameItem.getGameDiscount());
        return specialOffersPage.openGame(gameItem);
    }

    private static DriverSettings getDriverSettings() {
        return new DriverSettings()
                .setElementWaitSeconds(90L)
                .setWaitConditionSeconds(90L)
                .setPageLoadWaitSeconds(360L)
                .setWaitPollingIntervalMilliseconds(500L)
                .setNeedToLogElementsActions(true);
    }
}
