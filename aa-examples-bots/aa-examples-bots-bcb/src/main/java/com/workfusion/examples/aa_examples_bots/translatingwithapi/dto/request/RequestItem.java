package com.workfusion.examples.aa_examples_bots.translatingwithapi.dto.request;

import com.google.gson.annotations.SerializedName;

public class RequestItem {

    @SerializedName("text")
    private String text;

    public RequestItem() {
    }

    public String getText() {
        return text;
    }

    public RequestItem setText(String text) {
        this.text = text;
        return this;
    }
}
