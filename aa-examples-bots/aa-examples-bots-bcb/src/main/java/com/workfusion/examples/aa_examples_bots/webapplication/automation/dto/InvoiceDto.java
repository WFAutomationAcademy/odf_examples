package com.workfusion.examples.aa_examples_bots.webapplication.automation.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InvoiceDto {

    @SerializedName(value = "client")
    private String client;

    @SerializedName(value = "invoice_date")
    private String invoiceDate;

    @SerializedName(value = "pdf_password")
    private String pdfPassword;

    @SerializedName(value = "invoice_group")
    private String invoiceGroup;

    @SerializedName(value = "payment_method")
    private String paymentMethod;

    @SerializedName(value = "total_discount")
    private String totalDiscount;

    @SerializedName(value = "products")
    private List<ProductDto> products;

    public String getClient() {
        return client;
    }

    public InvoiceDto setClient(String client) {
        this.client = client;
        return this;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public InvoiceDto setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
        return this;
    }

    public String getPdfPassword() {
        return pdfPassword;
    }

    public InvoiceDto setPdfPassword(String pdfPassword) {
        this.pdfPassword = pdfPassword;
        return this;
    }

    public String getInvoiceGroup() {
        return invoiceGroup;
    }

    public InvoiceDto setInvoiceGroup(String invoiceGroup) {
        this.invoiceGroup = invoiceGroup;
        return this;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public InvoiceDto setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public String getTotalDiscount() {
        return totalDiscount;
    }

    public InvoiceDto setTotalDiscount(String totalDiscount) {
        this.totalDiscount = totalDiscount;
        return this;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public InvoiceDto setProducts(List<ProductDto> products) {
        this.products = products;
        return this;
    }
}
