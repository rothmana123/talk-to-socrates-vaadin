package com.example.application;

import ai.peoplecode.OpenAIConversation;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.ArrayList;

public class Scenario1 {
    private String person;
    private String gender;
    private String initialDetail;
    private ArrayList<City> cities;

    // Constructor to initialize using OpenAI API
    public Scenario1(OpenAIConversation conversation) {
        // Fetch criminal, gender, and initial detail using OpenAI API
        this.person = fetchCriminalFromAI(conversation);
        this.gender = fetchGenderFromAI(conversation);
        this.initialDetail = fetchInitialDetailFromAI(conversation);

        // Assuming cities are hardcoded or fetched separately
        //cities contains the scenarios for each city
        this.cities = new ArrayList<>();
    }

    // Getter methods
    public String getCriminal() {
        return person;
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

    // Method to fetch criminal from OpenAI
    private String fetchCriminalFromAI(OpenAIConversation conversation) {
        String question = "Generate a famous person from the 20th or 21st century.  No criminals or questionable poltical people.";
        String person = conversation.askQuestion("You are an expert in the 20th and 21st centuries", question);
        return person;
    }

    // Method to fetch gender from OpenAI
    private String fetchGenderFromAI(OpenAIConversation conversation) {
        String question = "What is the gender of the person? Only say male or female";
        String gender = conversation.askQuestion("You are describing the famous person for the game", question);
        return gender;
    }

    // Method to fetch initial detail from OpenAI
    private String fetchInitialDetailFromAI(OpenAIConversation conversation) {
        String question = "Provide an initial clue or detail about the famous persons actions or whereabouts.";
        String clue = conversation.askQuestion("You are describing a first clue about the famous person", question);
        return clue;
    }

    public static void main(String[] args) {
        // Replace with your actual OpenAI API key
        Dotenv dotenv = Dotenv.load();
        // Access the  secret
        String secret = dotenv.get("SECRET");
        OpenAIConversation conversation = new OpenAIConversation(secret, "gpt-4");

        // Create a new Scenario using the OpenAI conversation
        Scenario1 scenario = new Scenario1(conversation);

        // Print the generated details
        System.out.println("Generated Scenario:");
        System.out.println("Criminal: " + scenario.getCriminal());
        System.out.println("Gender: " + scenario.getGender());
        System.out.println("Initial Detail: " + scenario.getInitialDetail());

        // You can also check the cities list if initialized
        System.out.println("Cities: " + scenario.getCities());
    }
}



