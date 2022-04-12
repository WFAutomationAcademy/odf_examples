package com.workfusion.examples.aa_examples_bots.steam.automation.pages;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.elements.AbstractElement;
import com.workfusion.automation.rpa.elements.ElementState;
import com.workfusion.automation.rpa.elements.web.common.Label;
import com.workfusion.automation.rpa.pages.AbstractPage;
import com.workfusion.automation.rpa.utils.ElementSearchCriteria;
import com.workfusion.automation.rpa.utils.ElementSearchCriteriaBuilder;
import com.workfusion.examples.aa_examples_bots.steam.automation.elements.MinisliderGameItem;
import com.workfusion.examples.aa_examples_bots.steam.automation.enums.Language;
import com.workfusion.examples.aa_examples_bots.steam.automation.exceptions.SteamApplicationException;
import com.workfusion.rpa.helpers.Script;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class BasePage extends AbstractPage {

    private String currentWindowHandle;

    protected Label lblGameTilesHolder = new Label(driver,
            By.xpath("//*[contains(@class,'sale_item_browser') or @class='store_horizontal_minislider_ctn']"), "Game tiles holder");

    private static final String CHANGE_LANGUAGE_SCRIPT_TEMPLATE = "ChangeLanguage('%1$s');";

    private static final String LOADING_SPINNER_XPATH_SELECTOR = "//*[contains(@class,'LoadingWrapper')]";

    protected BasePage(DriverWrapper driver) {
        this(driver, true);
    }

    protected BasePage(DriverWrapper driver, boolean isNeedToWaitForPageIsLoaded) {
        super(driver);
        if (isNeedToWaitForPageIsLoaded) {
            waitForWebPageToLoad();
        }
    }

    public DriverWrapper getWrapper() {
        return this.driver;
    }

    public void changeLanguage(Language language) {
        //Execute script to change language and wait for web page is fully loaded
        driver.executeScript(String.format(CHANGE_LANGUAGE_SCRIPT_TEMPLATE, language.getName()), "JAVA_SCRIPT");
        waitForWebPageToLoad();
    }

    //Driver can be switched to new window (with Game details page) by openGame method
    //This method can be used to get handle of previous window to switch driver to it
    public String getWindowHandle() {
        if (StringUtils.isBlank(currentWindowHandle)) {
            return this.driver.getWindowHandle();
        }
        return currentWindowHandle;
    }

    public GameDetailedInfoPage openGame(MinisliderGameItem gameElement) {
        this.currentWindowHandle = driver.getWindowHandle();
        Set<String> currentWindowHandles = driver.getWindowHandles();
        //Scroll needed element into view (to be interactable)
        scrollIntoViewCenter(getAsWebElement(gameElement, ElementState.EXISTS));
        //Click
        gameElement.click();
        //If game tile is new styled, game details page will be opened in new window
        if (gameElement.isNewStyle()) {
            String newWindowHandle = driver.waitAnyNewWindow(currentWindowHandles,
                    driver.getDriverSettings().getWindowSwitchWaitSeconds());
            if (StringUtils.isBlank(newWindowHandle)) {
                throw new SteamApplicationException("New window with Game Details page wasn't opened");
            }
            driver.switchToWindow(newWindowHandle);
        }
        //Wait for web page is fully loaded
        waitForWebPageToLoad();
        AtomicBoolean isAgeCheckFound = new AtomicBoolean(false);

        //Wait for ame Detailed Info page or Age Check page is shown
        boolean isFoundExpected = wait.waitForTrue(y -> {
            if (isPageFound(AgeCheckPage.PAGE_UNIQUE_LOCATOR)) {
                isAgeCheckFound.set(true);
                return true;
            }
            return isPageFound(GameDetailedInfoPage.PAGE_UNIQUE_LOCATOR);
        }, driver.getDriverSettings().getPageLoadWaitSeconds());

        if (!isFoundExpected) {
            String message = String.format("Neither Game Detailed Info page nor Age Check page have appeared during %1$s seconds",
                    driver.getDriverSettings().getPageLoadWaitSeconds());
            throw new SteamApplicationException(message);
        }

        if (isAgeCheckFound.get()) {
            //If Age Check page is shown, set birthdate and submit form to go to Game Detailed Info page
            AgeCheckPage ageCheckPage = new AgeCheckPage(driver);
            return ageCheckPage.setBirthdateAndGoToGamePage();
        }
        return new GameDetailedInfoPage(driver);
    }

    protected boolean isPageFound(By pageUniqueLocator) {
        return !driver.findElementsNative(driver.getDriver(), pageUniqueLocator).isEmpty();
    }

    protected void waitForLoadingSpinnerDisappears() {
        wait.waitForTrue(y -> {
            List<WebElement> elements = getWrapper()
                    .findElementsNative(getWrapper().getDriver(), By.xpath(LOADING_SPINNER_XPATH_SELECTOR));
            for (WebElement element : elements) {
                if (element.isDisplayed()) {
                    if (!isVisibleInViewport(element)) {
                        //If loading spinner is displayed but it's not in view port,
                        //need to scroll it into view port to trigger loading of data
                        scrollIntoViewCenter(element);
                    }
                    return false;
                }
            }
            return true;
        });
    }

    protected boolean isVisibleInViewport(WebElement element) {
        return (boolean) driver.executeScript("var elem; if (arguments[0]===\"" + Script.JAVA_SCRIPT_TYPE + "\") {elem = arguments[1];} else {elem = arguments[0];} var box = elem.getBoundingClientRect(), cx = box.left + box.width / 2,  cy = box.top + box.height / 2,  e = document.elementFromPoint(cx, cy); for (; e; e = e.parentElement) { if (e === elem) return true;} return false;", Script.JAVA_SCRIPT_TYPE, element);
    }

    protected WebElement getAsWebElement(AbstractElement customElement, ElementState state) {
        ElementSearchCriteria criteria = new ElementSearchCriteriaBuilder(customElement.getLocator())
                .withSearchContext(driver.getDriver())
                .withTimeout(driver.getDriverSettings().getElementWaitSeconds())
                .withState(state)
                .build();
        return driver.findElement(criteria);
    }

    protected void scrollIntoViewCenter(WebElement element) {
        driver.executeScript("if (arguments[0]===\"" + Script.JAVA_SCRIPT_TYPE + "\") {arguments[1].scrollIntoView({block: \"center\"});} else {arguments[0].scrollIntoView({block: \"center\"});}", Script.JAVA_SCRIPT_TYPE, element);
    }

    @Override
    public void waitForWebPageToLoad() {
        super.waitForWebPageToLoad();
        //When page is already loaded, wait for the loading spinner disappears
        waitForLoadingSpinnerDisappears();
    }
}
