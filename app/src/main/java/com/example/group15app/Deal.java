package com.example.group15app;

public class Deal {
    private String title;
    private String store;
    private String discount;
    private String description;
    private String category;
    private String url;

    public Deal(String title, String store, String discount,
                String description, String category, String url) {
        this.title       = title;
        this.store       = store;
        this.discount    = discount;
        this.description = description;
        this.category    = category;
        this.url         = url;
    }

    public String getTitle()       { return title; }
    public String getStore()       { return store; }
    public String getDiscount()    { return discount; }
    public String getDescription() { return description; }
    public String getCategory()    { return category; }
    public String getUrl()         { return url; }
}
