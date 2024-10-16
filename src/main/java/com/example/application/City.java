package com.example.application;

import java.util.ArrayList;

public class City {
    private String name;
    private ArrayList<String> marketConversations;
    private ArrayList<String> cafeConversations;

    public City(String name, ArrayList<String> marketConversations, ArrayList<String> cafeConversations) {
        this.name = name;
        this.marketConversations = marketConversations;
        this.cafeConversations = cafeConversations;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getMarketConversations() {
        return marketConversations;
    }

    public ArrayList<String> getCafeConversations() {
        return cafeConversations;
    }
}

