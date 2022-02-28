package com.workfusion.examples.aa_examples_bots.translatingwithapi.dto.response.translate;

import com.google.gson.annotations.SerializedName;

public class TranslationItem {

    @SerializedName("text")
    private String text;

    @SerializedName("to")
    private String to;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
