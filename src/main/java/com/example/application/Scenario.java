package com.example.application;

import java.util.ArrayList;

public class Scenario {
    private String criminal;
    private ArrayList<City> cities;

    public Scenario(String criminal, ArrayList<City> cities) {
        this.criminal = criminal;
        this.cities = cities;
    }

    public String getCriminal() {
        return criminal;
    }

    public ArrayList<City> getCities() {
        return cities;
    }
}


