package com.workfusion.examples.aa_examples_bots.translatingwithapi.dto.response.translate;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TranslateResponseItem {

    @SerializedName("translations")
    private List<TranslationItem> translations;

    public TranslateResponseItem() {
    }

    public List<TranslationItem> getTranslations() {
        return translations;
    }

    public void setTranslations(List<TranslationItem> translations) {
        this.translations = translations;
    }
}
