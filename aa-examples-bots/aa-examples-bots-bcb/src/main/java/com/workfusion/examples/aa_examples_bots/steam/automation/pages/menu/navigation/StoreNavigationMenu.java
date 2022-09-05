package com.workfusion.examples.aa_examples_bots.steam.automation.pages.menu.navigation;

import com.workfusion.automation.rpa.driver.DriverWrapper;
import com.workfusion.examples.aa_examples_bots.steam.automation.pages.menu.navigation.items.Categories;
import com.workfusion.examples.aa_examples_bots.steam.automation.pages.menu.navigation.items.NewAndNoteworthy;
import com.workfusion.examples.aa_examples_bots.steam.automation.pages.menu.navigation.items.YourStore;

//To add to some page object ability to interact with navigation menu,
//just implement this interface by appropriate page class
public interface StoreNavigationMenu {

    default StoreNavigation storeNavigationMenu() {
        return new StoreNavigation(getWrapper());
    }

    class StoreNavigation {
        DriverWrapper wrapper;

        public StoreNavigation(DriverWrapper wrapper) {
            this.wrapper = wrapper;
        }

        public YourStore yourStore() {
            return new YourStore(wrapper);
        }

        public NewAndNoteworthy newAndNoteworthy() {
            return new NewAndNoteworthy(wrapper);
        }

        public Categories categories() {
            return new Categories(wrapper);
        }
    }

    DriverWrapper getWrapper();
}
