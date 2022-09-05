package com.workfusion.examples.aa_examples_bots.webapplication.automation.pages;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasePage extends AbstractPage {

    private static final String LOADING_SPINNER_SELECTOR = "//div[@id='fullpage-loader']";

    public BasePage(DriverWrapper driver) {
        super(driver);
        waitForWebPageToLoad();
    }

    public DriverWrapper getWrapper() {
        return this.driver;
    }

    protected void waitForLoadingSpinnerIsNotDisplayed() {
        //Wait for document['readyState'] equals to 'complete'
        waitForWebPageToLoad();
        //Wait for loading spinner on a page is not shown
        wait.waitForTrue(y -> {
            List<WebElement> elements = driver.findElementsNative(driver.getDriver(), By.xpath(LOADING_SPINNER_SELECTOR));
            return elements.isEmpty() || !elements.get(0).isDisplayed();
        });
    }

}
