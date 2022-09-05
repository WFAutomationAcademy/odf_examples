package com.workfusion.examples.aa_examples_bots.translatingwithapi.dto.response.detect;

import com.google.gson.annotations.SerializedName;

public class DetectResponseItem {

    @SerializedName("language")
    private String language;

    @SerializedName("score")
    private Double score;

    @SerializedName("isTranslationSupported")
    private Boolean isTranslationSupported;

    @SerializedName("isTransliterationSupported")
    private Boolean isTransliterationSupported;

    public DetectResponseItem() {
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Boolean getIsTranslationSupported() {
        return isTranslationSupported;
    }

    public void setIsTranslationSupported(Boolean isTranslationSupported) {
        this.isTranslationSupported = isTranslationSupported;
    }

    public Boolean getIsTransliterationSupported() {
        return isTransliterationSupported;
    }

    public void setIsTransliterationSupported(Boolean isTransliterationSupported) {
        this.isTransliterationSupported = isTransliterationSupported;
    }
}
