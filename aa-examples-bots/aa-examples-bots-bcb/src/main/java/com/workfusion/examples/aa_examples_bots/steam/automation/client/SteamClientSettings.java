package com.workfusion.examples.aa_examples_bots.steam.automation.client;

import java.util.Date;

public class SteamClientSettings {

    private static final Date DEFAULT_BIRTHDATE = new Date(0L);

    private Date birthdate = DEFAULT_BIRTHDATE;

    public Date getBirthdate() {
        return birthdate;
    }

    public SteamClientSettings setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
        return this;
    }
}
