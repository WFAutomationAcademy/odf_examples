package com.workfusion.examples.aa_examples_bots.steam.automation.pages;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.elements.web.common.Button;
import com.workfusion.automation.rpa.elements.web.common.ComboBox;
import com.workfusion.examples.aa_examples_bots.steam.automation.client.SteamClient;
import com.workfusion.examples.aa_examples_bots.steam.automation.pages.menu.navigation.StoreNavigationMenu;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class AgeCheckPage extends BasePage implements StoreNavigationMenu {


    public static final By PAGE_UNIQUE_LOCATOR = By.id("app_agegate");

    private final ComboBox cmbDay = new ComboBox(driver, By.id("ageDay"), "Day selector");
    private final ComboBox cmbMonth = new ComboBox(driver, By.id("ageMonth"), "Month selector");
    private final ComboBox cmbYear = new ComboBox(driver, By.id("ageYear"), "Year selector");
    private final Button btnSubmit = new Button(driver, By.xpath("//*[contains(@onclick,'ViewProductPage()')]"), "View Page");

    private static final String BIRTHDATE_TEMPLATE = "d MMMM yyyy";
    private static final SimpleDateFormat BIRTHDATE_FORMAT
            = new SimpleDateFormat(BIRTHDATE_TEMPLATE, new Locale("en", "US"));

    AgeCheckPage(DriverWrapper driver) {
        super(driver);
    }

    public GameDetailedInfoPage setBirthdateAndGoToGamePage() {
        String[] birthdate = BIRTHDATE_FORMAT.format(SteamClient.getSteamClientSettings().getBirthdate()).split("\\s");
        cmbDay.selectByValue(birthdate[0]);
        cmbMonth.selectByValue(birthdate[1]);
        cmbYear.selectByValue(birthdate[2]);
        btnSubmit.click();
        return new GameDetailedInfoPage(driver);
    }
}
