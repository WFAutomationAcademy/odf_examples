package com.workfusion.examples.aa_examples_bots.steam.automation.pages;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.elements.web.common.Label;
import com.workfusion.examples.aa_examples_bots.steam.automation.pages.menu.navigation.StoreNavigationMenu;
import org.openqa.selenium.By;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameDetailedInfoPage extends BasePage implements StoreNavigationMenu {

    public static final By PAGE_UNIQUE_LOCATOR = By.xpath("//*[contains(@class,'game_title_area')]");

    private final Label lblGameName = new Label(driver, By.xpath("//*[contains(@class,'game_title_area')]//h2 | //*[@id='appHubAppName']"), "Game name");
    //Can be absent on some pages.
    //But date can be got directly and no need to search the date inside text got from Details block.
    private final Label lblReleaseDateOnGlanceBlock = new Label(driver, By.xpath("//*[@class='release_date']//*[@class='date']"),
            "Release date on Glance block");
    private final Label lblDetailsBlock = new Label(driver, By.xpath("//*[@class='details_block' and contains(.,'Title')]"), "Details block");

    private static final String RELEASE_DATE_TEMPLATE = "\\d{1,2}\\s[a-zA-z]{3},\\s*\\d{4}";

    public GameDetailedInfoPage(DriverWrapper driver) {
        super(driver);
    }

    public String getGameName() {
        return lblGameName.getText();
    }

    public String getGameReleaseDate() {
        if (lblReleaseDateOnGlanceBlock.isPresent()) {
            //If release date exist as separate element, get date directly from it
            return lblReleaseDateOnGlanceBlock.getText();
        }
        String releaseDate = "";
        if (lblDetailsBlock.isPresent()) {
            //If Details block present, try to parse it to get release date
            String rawDetailsBlockText = lblDetailsBlock.getText();
            return getGameReleaseDateFromDetailsBlockText(rawDetailsBlockText);

        }
        return releaseDate;
    }

    private String getGameReleaseDateFromDetailsBlockText(String detailsBlockText) {
        //Remove extra spaces and new line symbols
        String preparedDetailsBlockText = detailsBlockText.replaceAll("\n", " ")
                .replaceAll("\\s+", " ");
        if (preparedDetailsBlockText.toLowerCase().contains("release date")) {
            //Parse prepared text with regular expression to get release date
            Matcher matcher = Pattern.compile(RELEASE_DATE_TEMPLATE).matcher(preparedDetailsBlockText);
            if (matcher.find()) {
                return matcher.group();
            }
        }
        return "";
    }
}
