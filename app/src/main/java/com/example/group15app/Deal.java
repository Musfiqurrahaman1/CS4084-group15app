package com.example.group15app;

public class Deal {
    private String title, store, discount, description, category, url, address, eircode;

    public Deal(String title, String store, String discount, String description,
                String category, String url, String address, String eircode) {
        this.title = title; this.store = store; this.discount = discount;
        this.description = description; this.category = category;
        this.url = url; this.address = address; this.eircode = eircode;
    }

    public String getTitle()       { return title; }
    public String getStore()       { return store; }
    public String getDiscount()    { return discount; }
    public String getDescription() { return description; }
    public String getCategory()    { return category; }
    public String getUrl()         { return url; }
    public String getAddress()     { return address; }
    public String getEircode()     { return eircode; }
}
