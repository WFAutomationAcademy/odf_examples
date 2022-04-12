package com.workfusion.examples.aa_examples_bots.google.automation.entities;

import java.util.LinkedHashMap;
import java.util.Map;

public class FoundItemDto {

    private String title;
    private String link;
    private String shortDescription;

    private static final String TITLE_COLUMN_NAME = "title";
    private static final String LINK_COLUMN_NAME = "link";
    private static final String DESCRIPTION_COLUMN_NAME = "description";

    public String getTitle() {
        return title;
    }

    public FoundItemDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getLink() {
        return link;
    }

    public FoundItemDto setLink(String link) {
        this.link = link;
        return this;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public FoundItemDto setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public Map<String, String> convertToMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(TITLE_COLUMN_NAME, this.title);
        map.put(LINK_COLUMN_NAME, this.link);
        map.put(DESCRIPTION_COLUMN_NAME, this.shortDescription);
        return map;
    }
}
