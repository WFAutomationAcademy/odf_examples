package com.workfusion.examples.aa_examples_bots.webapplication.automation.dto;

import com.google.gson.annotations.SerializedName;

public class ProductDto {

    @SerializedName(value = "item")
    private String item;

    @SerializedName(value = "quantity")
    private String quantity;

    @SerializedName(value = "price")
    private String price;

    @SerializedName(value = "description")
    private String description;

    public String getItem() {
        return item;
    }

    public ProductDto setItem(String item) {
        this.item = item;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public ProductDto setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public ProductDto setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
