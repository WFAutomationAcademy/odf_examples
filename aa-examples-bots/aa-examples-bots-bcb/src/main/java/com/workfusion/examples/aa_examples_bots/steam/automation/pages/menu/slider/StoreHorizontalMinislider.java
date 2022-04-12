package com.workfusion.examples.aa_examples_bots.steam.automation.pages.menu.slider;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.examples.aa_examples_bots.steam.automation.pages.menu.slider.items.MinisliderGamesList;

public interface StoreHorizontalMinislider {

    default MinisliderGamesList minisliderGamesList() {
        return new MinisliderGamesList(getWrapper());
    }

    DriverWrapper getWrapper();
}
