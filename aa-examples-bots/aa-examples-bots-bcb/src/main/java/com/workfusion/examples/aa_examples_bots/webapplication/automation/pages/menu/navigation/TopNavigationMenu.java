package com.workfusion.examples.aa_examples_bots.webapplication.automation.pages.menu.navigation;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.automation.rpa.elements.web.common.Button;
import com.workfusion.examples.aa_examples_bots.webapplication.automation.pages.menu.navigation.items.Invoices;
import org.openqa.selenium.By;

//To add to some page object ability to interact with navigation menu,
//just implement this interface by appropriate page class
public interface TopNavigationMenu {

    default TopNavigation topNavigation() {
        return new TopNavigation(getWrapper());
    }

    class TopNavigation {

        DriverWrapper wrapper;

        public TopNavigation(DriverWrapper wrapper) {
            this.wrapper = wrapper;
        }

        public Invoices invoices() {
            return new Invoices(wrapper);
        }

        public void logout() {
            new Button(wrapper, By.xpath("//*[contains(@class,'logout')]"), "Logout").click();
        }
    }

    DriverWrapper getWrapper();
}
