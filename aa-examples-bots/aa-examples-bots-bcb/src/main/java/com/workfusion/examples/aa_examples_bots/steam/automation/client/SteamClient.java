package com.workfusion.examples.aa_examples_bots.steam.automation.client;

import com.workfusion.automation.rpa.driver.DriverSettings;
import com.workfusion.automation.rpa.driver.DriverType;
import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.driver.RobotDriverWrapper;
import com.workfusion.examples.aa_examples_bots.steam.automation.enums.Language;
import com.workfusion.examples.aa_examples_bots.steam.automation.pages.MainPage;
import com.workfusion.rpa.helpers.RPA;
import org.slf4j.Logger;

public class SteamClient {

    private static final String DEFAULT_STEAM_URL = "https://store.steampowered.com/";

    private static final long DEFAULT_ELEMENT_WAIT_SECONDS = 90L;
    private static final long DEFAULT_WAIT_CONDITION_SECONDS = 90L;
    private static final long DEFAULT_PAGE_LOAD_WAIT_SECONDS = 360L;
    private static final long DEFAULT_WAIT_POLLING_INTERVAL_MILLIS = 500L;

    private final DriverWrapper wrapper;

    private static SteamClientSettings clientSettings;

    public SteamClient(Logger logger) {
        this.wrapper = new RobotDriverWrapper(logger, getDefaultSteamDriverSettings());
    }

    public SteamClient(Logger logger, DriverSettings driverSettings) {
        this.wrapper = new RobotDriverWrapper(logger, driverSettings);
    }

    public static SteamClientSettings getSteamClientSettings() {
        if (clientSettings == null) {
            clientSettings = new SteamClientSettings();
        }
        return clientSettings;
    }

    public SteamClient setSteamClientSettings(SteamClientSettings steamClientSettings) {
        clientSettings = steamClientSettings;
        return this;
    }

    public MainPage getMainPage() {
        return getMainPage(DEFAULT_STEAM_URL);
    }

    public MainPage getMainPage(String url) {
        //Switch driver to CHROME and open provided url
        wrapper.switchDriver(DriverType.CHROME);
        RPA.openChrome(url);
        MainPage mainPage = new MainPage(wrapper);
        //To use other languages (not english), some selectors should be modified
        //To be sure that active language is English, switch to English
        mainPage.changeLanguage(Language.ENGLISH);
        return mainPage;
    }

    private DriverSettings getDefaultSteamDriverSettings() {
        return new DriverSettings()
                .setElementWaitSeconds(DEFAULT_ELEMENT_WAIT_SECONDS)
                .setWaitConditionSeconds(DEFAULT_WAIT_CONDITION_SECONDS)
                .setPageLoadWaitSeconds(DEFAULT_PAGE_LOAD_WAIT_SECONDS)
                .setWaitPollingIntervalMilliseconds(DEFAULT_WAIT_POLLING_INTERVAL_MILLIS);
    }
}
