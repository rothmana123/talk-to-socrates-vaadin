package com.example.application;

public class City1 {
    private String name;
    private String marketConversation;
    private String cafeConversation;

    public City1(String name, String marketConversation, String cafeConversation) {
        this.name = name;
        this.marketConversation = marketConversation;
        this.cafeConversation = cafeConversation;
    }

    public String getName() {
        return name;
    }

    public String getMarketConversation() {
        return marketConversation;
    }

    public String getCafeConversation() {
        return cafeConversation;
    }
}

