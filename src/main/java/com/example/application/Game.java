package com.example.application;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    public ArrayList<Scenario> loadScenarios() {
        ArrayList<Scenario> scenarios = new ArrayList<>();

        // Scenario 1: Che Guevara
        ArrayList<City> cheCities = new ArrayList<>();
        cheCities.add(new City("Casablanca",
                new ArrayList<>(Arrays.asList("Vendor mentions seeing a man with a Cuban cigar", "A patron heard about plans to go to Marrakech")),
                new ArrayList<>(Arrays.asList("Café regular discusses Che-like revolutionaries", "Traveler mentions someone heading to Fes"))));

        cheCities.add(new City("Marrakech",
                new ArrayList<>(Arrays.asList("A man bought revolutionary pamphlets", "Talk of moving to the mountains")),
                new ArrayList<>(Arrays.asList("Saw a man in a beret", "Talk of heading to Tangier"))));

        cheCities.add(new City("Fes",
                new ArrayList<>(Arrays.asList("Discussion about Bolivia and Cuba", "Mention of heading north")),
                new ArrayList<>(Arrays.asList("Saw a man with a worn jacket", "Conversation about a ‘final push’"))));

        cheCities.add(new City("Tangier",
                new ArrayList<>(Arrays.asList("Asking about Rabat", "Looked like someone who's traveled a lot")),
                new ArrayList<>(Arrays.asList("Final plans mentioned", "Spoke of disappearing for good"))));

        cheCities.add(new City("Rabat",
                new ArrayList<>(Arrays.asList("Final conversation leading to guess")),
                new ArrayList<>(Arrays.asList())));  // Only final guess in Rabat

        scenarios.add(new Scenario("Che Guevara", cheCities));

        // Scenario 2: Albert Einstein
        ArrayList<City> einsteinCities = new ArrayList<>();
        einsteinCities.add(new City("Casablanca",
                new ArrayList<>(Arrays.asList("A man was seen scribbling equations on napkins", "He was discussing something about relativity")),
                new ArrayList<>(Arrays.asList("A café-goer mentioned someone speaking with a German accent", "He was carrying a violin case"))));

        einsteinCities.add(new City("Marrakech",
                new ArrayList<>(Arrays.asList("Someone was talking about time being relative", "A man was asking for directions to the university")),
                new ArrayList<>(Arrays.asList("There was a man who looked lost in thought while staring at the stars", "Talk about physics and theories"))));

        einsteinCities.add(new City("Fes",
                new ArrayList<>(Arrays.asList("A man was seen sketching diagrams on the market stall", "He mentioned visiting Rabat soon")),
                new ArrayList<>(Arrays.asList("Someone mentioned the atomic bomb and the man didn't look pleased", "He left a piece of paper with equations on the table"))));

        einsteinCities.add(new City("Tangier",
                new ArrayList<>(Arrays.asList("A man with wild hair and a briefcase was seen heading toward the train station", "He was talking about his theories")),
                new ArrayList<>(Arrays.asList("He was heard discussing the nature of time", "Mentions of Princeton and patents"))));

        einsteinCities.add(new City("Rabat",
                new ArrayList<>(Arrays.asList("Final conversation leading to guess")),
                new ArrayList<>(Arrays.asList())));  // Only final guess in Rabat

        scenarios.add(new Scenario("Albert Einstein", einsteinCities));

        // Scenario 3: Frida Kahlo
        ArrayList<City> fridaCities = new ArrayList<>();
        fridaCities.add(new City("Casablanca",
                new ArrayList<>(Arrays.asList("A woman with vibrant clothing was seen painting", "She had an unusual walking cane")),
                new ArrayList<>(Arrays.asList("A café-goer mentioned someone with striking facial features", "She was talking about Diego Rivera"))));


        fridaCities.add(new City("Marrakech",
                new ArrayList<>(Arrays.asList("There was a woman painting surreal self-portraits in the market", "She was asking about a good place to rest")),
                new ArrayList<>(Arrays.asList("Someone mentioned a woman in a wheelchair visiting the museum", "She was looking for inspiration in Marrakech"))));

        fridaCities.add(new City("Fes",
                new ArrayList<>(Arrays.asList("A woman was talking about pain and art", "She mentioned Mexico and political movements")),
                new ArrayList<>(Arrays.asList("Someone mentioned her talking about revolution and art", "She was drawing flowers and animals"))));

        fridaCities.add(new City("Tangier",
                new ArrayList<>(Arrays.asList("A woman with flowers in her hair was seen heading toward the souk", "She had a small easel and paints")),
                new ArrayList<>(Arrays.asList("Someone overheard her discussing her paintings and injuries", "She was on her way to Rabat"))));

        fridaCities.add(new City("Rabat",
                new ArrayList<>(Arrays.asList("Final conversation leading to guess")),
                new ArrayList<>(Arrays.asList())));  // Only final guess in Rabat

        scenarios.add(new Scenario("Frida Kahlo", fridaCities));

        // Scenario 4: Winston Churchill
        ArrayList<City> churchillCities = new ArrayList<>();
        churchillCities.add(new City("Casablanca",
                new ArrayList<>(Arrays.asList("A man with a cigar and a bowler hat was seen", "He was discussing strategy")),
                new ArrayList<>(Arrays.asList("A café-goer overheard someone discussing WWII and Europe", "He was seen with an old map"))));

        churchillCities.add(new City("Marrakech",
                new ArrayList<>(Arrays.asList("Someone mentioned a man giving speeches about leadership", "He was talking about Britain and the war")),
                new ArrayList<>(Arrays.asList("A man was asking about the best place to buy cigars", "He was looking at some historical artifacts"))));

        churchillCities.add(new City("Fes",
                new ArrayList<>(Arrays.asList("He was seen talking about military strategies", "Someone overheard him discussing the Allies")),
                new ArrayList<>(Arrays.asList("He was drawing maps and battle plans", "Someone saw him writing in a journal"))));

        churchillCities.add(new City("Tangier",
                new ArrayList<>(Arrays.asList("He was talking about diplomacy", "Someone saw him heading toward Rabat")),
                new ArrayList<>(Arrays.asList("He was seen with a large map and a glass of whiskey", "Someone overheard him talking about Europe’s future"))));

        churchillCities.add(new City("Rabat",
                new ArrayList<>(Arrays.asList("Final conversation leading to guess")),
                new ArrayList<>(Arrays.asList())));  // Only final guess in Rabat

        scenarios.add(new Scenario("Winston Churchill", churchillCities));

        // Scenario 5: Malcolm X
        ArrayList<City> malcolmCities = new ArrayList<>();
        malcolmCities.add(new City("Casablanca",
                new ArrayList<>(Arrays.asList("A man with glasses and a sharp suit was seen discussing civil rights", "He was talking about his travels to Mecca")),
                new ArrayList<>(Arrays.asList("Someone mentioned a man discussing Islam and equality", "He was asking about African liberation movements"))));

        malcolmCities.add(new City("Marrakech",
                new ArrayList<>(Arrays.asList("A man was seen giving a passionate speech about justice", "He was asking about anti-colonial movements")),
                new ArrayList<>(Arrays.asList("He was seen with a group of people discussing freedom", "He was talking about global struggles for civil rights"))));

        malcolmCities.add(new City("Fes",
                new ArrayList<>(Arrays.asList("Someone saw a man giving speeches about equality", "He was seen heading toward Tangier")),
                new ArrayList<>(Arrays.asList("Someone overheard him discussing the liberation of Africa", "He was speaking about his travels around the world"))));

        malcolmCities.add(new City("Tangier",
                new ArrayList<>(Arrays.asList("He was seen with a group of activists", "Someone overheard him talking about Rabat")),
                new ArrayList<>(Arrays.asList("Someone mentioned a man with a strong voice, discussing justice and equality", "He was preparing to travel to Rabat"))));

        malcolmCities.add(new City("Rabat",
                new ArrayList<>(Arrays.asList("Final conversation leading to guess")),
                new ArrayList<>(Arrays.asList())));  // Only final guess in Rabat

        scenarios.add(new Scenario("Malcolm X", malcolmCities));

        return scenarios;
    }

    public void startNewGame() {
        ArrayList<Scenario> scenarios = loadScenarios();
        Scenario selectedScenario = scenarios.get((int) (Math.random() * scenarios.size()));  // Randomly select a scenario
        // Initialize the game with this scenario's data
    }
}

