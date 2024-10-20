package com.example.application;

import java.util.ArrayList;

public class Scenario {
    private String criminal;
    private String gender;
    private String initialDetail;
    private ArrayList<City> cities;

    public Scenario(String criminal, String gender, String initialDetail, ArrayList<City> cities) {
        this.criminal = criminal;
        this.gender = gender;
        this.initialDetail = initialDetail;
        this.cities = cities;
    }

    public String getCriminal() {
        return criminal;
    }

    public String getGender() {
        return gender;
    }

    public String getInitialDetail() {
        return initialDetail;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    // Method to get the correct next city
    public City getNextCity(City currentCity) {
        int currentIndex = cities.indexOf(currentCity);
        if (currentIndex < cities.size() - 1) {
            return cities.get(currentIndex + 1);  // Return the next city in the list
        } else {
            return null;  // If it's the last city, return null
        }
    }
}
