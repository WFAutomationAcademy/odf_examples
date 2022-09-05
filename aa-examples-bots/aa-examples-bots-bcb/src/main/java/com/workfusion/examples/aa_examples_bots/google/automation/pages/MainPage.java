package com.workfusion.examples.aa_examples_bots.google.automation.pages;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.elements.web.common.Button;
import com.workfusion.automation.rpa.elements.web.common.TextBox;
import com.workfusion.rpa.helpers.RPA;
import org.openqa.selenium.By;

public class MainPage extends BasePage {

    TextBox txbSearch = new TextBox(driver, By.xpath("//input[@name='q']"), "Search");
    Button btnSearch = new Button(driver, By.xpath("//input[@name='btnK']"), "Search");

    public MainPage(DriverWrapper driver) {
        super(driver);
    }

    public ResultsPage makeSearch(String textToSearch) {
        //Make a search and return page with results
        txbSearch.setText(textToSearch);
        RPA.pressTab();
        btnSearch.click();
        return new ResultsPage(driver);
    }
}
