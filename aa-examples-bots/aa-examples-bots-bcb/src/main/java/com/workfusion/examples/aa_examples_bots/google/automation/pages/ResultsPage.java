package com.workfusion.examples.aa_examples_bots.google.automation.pages;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.examples.aa_examples_bots.google.automation.elements.FoundItem;
import com.workfusion.examples.aa_examples_bots.google.automation.entities.FoundItemDto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultsPage extends BasePage {

    private static final String FOUND_ELEMENT_COMMON_XPATH_SELECTOR
            = "//a[descendant::cite]//h3//ancestor::div[@class='g']//div[@data-hveid and descendant::div[contains(@style,'-webkit-line-clamp')]]";

    private static final String FOUND_ELEMENT_UNIQUE_XPATH_SELECTOR_TEMPLATE = "(%1$s)[%2$s]";

    public ResultsPage(DriverWrapper driver) {
        super(driver);
    }

    public List<FoundItem> getFoundItems() {
        List<FoundItem> result = new ArrayList<>();
        //Find all results as list of raw web elements
        List<WebElement> tempElements = driver.findElements(By.xpath(FOUND_ELEMENT_COMMON_XPATH_SELECTOR));
        for (int i = 0; i < tempElements.size(); i++) {
            //Create wrapped elements (FoundItem) to return list of wrapped elements
            String uniqueSelector = String.format(FOUND_ELEMENT_UNIQUE_XPATH_SELECTOR_TEMPLATE, FOUND_ELEMENT_COMMON_XPATH_SELECTOR, i + 1);
            result.add(new FoundItem(driver, By.xpath(uniqueSelector), "Item " + (i + 1)));
        }
        return result;
    }

    //Covert wrapped web elements into data transfer objects (to not interact with them but just to store data)
    public List<FoundItemDto> getFoundItemsDto() {
        List<FoundItem> result = getFoundItems();
        return result.stream().map(item -> new FoundItemDto()
                .setTitle(item.getTitle())
                .setLink(item.getLink())
                .setShortDescription(item.getShortDescription())).collect(Collectors.toList());
    }
}
