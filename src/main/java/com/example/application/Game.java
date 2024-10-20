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
                new ArrayList<>(Arrays.asList("Café regular discusses Che-like revolutionaries", "He was overheard planning a journey to Marrakech"))));

        cheCities.add(new City("Marrakech",
                new ArrayList<>(Arrays.asList("A man bought revolutionary pamphlets", "Talk of moving to the mountains")),
                new ArrayList<>(Arrays.asList("Saw a man in a beret", "Talk of heading to Fes"))));

        cheCities.add(new City("Fes",
                new ArrayList<>(Arrays.asList("Discussion about Bolivia and Cuba", "Mention of heading north")),
                new ArrayList<>(Arrays.asList("Saw a man with a worn jacket", "Conversation about going to Tangier"))));

        cheCities.add(new City("Tangier",
                new ArrayList<>(Arrays.asList("Asking about Rabat", "Looked like someone who's traveled a lot")),
                new ArrayList<>(Arrays.asList("Final plans mentioned", "Spoke of disappearing in Rabat"))));

        cheCities.add(new City("Rabat",
                new ArrayList<>(Arrays.asList("Final conversation leading to guess")),
                new ArrayList<>(Arrays.asList())));  // Only final guess in Rabat

        scenarios.add(new Scenario("Che Guevara", "Male", "He was often seen with a cigar and spoke passionately about revolution.", cheCities));

        // Scenario 2: Albert Einstein
        ArrayList<City> einsteinCities = new ArrayList<>();
        einsteinCities.add(new City("Casablanca",
                new ArrayList<>(Arrays.asList("A man was seen scribbling equations on napkins", "He was discussing something about Marrakech")),
                new ArrayList<>(Arrays.asList("A café-goer mentioned someone speaking with a German accent", "He said he'd be heading to Marrakech"))));

        einsteinCities.add(new City("Marrakech",
                new ArrayList<>(Arrays.asList("Someone was talking about time being relative", "A man was asking for directions to Fes")),
                new ArrayList<>(Arrays.asList("There was a man who looked lost in thought", "Mentioned plans to travel to Fes"))));

        einsteinCities.add(new City("Fes",
                new ArrayList<>(Arrays.asList("A man was seen sketching diagrams", "He mentioned traveling to Tangier")),
                new ArrayList<>(Arrays.asList("Someone overheard him mentioning theories", "He left some papers saying he would head to Tangier"))));

        einsteinCities.add(new City("Tangier",
                new ArrayList<>(Arrays.asList("A man with wild hair was seen heading toward Rabat", "He was talking about his theories")),
                new ArrayList<>(Arrays.asList("Mentions of Princeton", "Someone said he was going to Rabat"))));

        einsteinCities.add(new City("Rabat",
                new ArrayList<>(Arrays.asList("Final conversation leading to guess")),
                new ArrayList<>(Arrays.asList())));  // Only final guess in Rabat

        scenarios.add(new Scenario("Albert Einstein", "Male", "He often carried a notebook and scribbled equations.", einsteinCities));

        // Scenario 3: Frida Kahlo
        ArrayList<City> fridaCities = new ArrayList<>();
        fridaCities.add(new City("Casablanca",
                new ArrayList<>(Arrays.asList("An artist mentioned meeting a woman with bold colors", "She talked about heading to Marrakech")),
                new ArrayList<>(Arrays.asList("A café patron described someone with striking eyebrows", "She mentioned traveling to Marrakech"))));

        fridaCities.add(new City("Marrakech",
                new ArrayList<>(Arrays.asList("A woman was seen carrying art supplies", "She mentioned finding inspiration in the mountains")),
                new ArrayList<>(Arrays.asList("A painter mentioned meeting a woman with vibrant clothing", "She was heading to Fes"))));

        fridaCities.add(new City("Fes",
                new ArrayList<>(Arrays.asList("Talk of an artist who paints about pain", "A discussion about her moving on to Tangier")),
                new ArrayList<>(Arrays.asList("Someone saw a woman painting at a café", "She mentioned going to Tangier"))));

        fridaCities.add(new City("Tangier",
                new ArrayList<>(Arrays.asList("A woman with colorful clothing was seen heading to Rabat", "She spoke of personal struggles")),
                new ArrayList<>(Arrays.asList("Talk about her planning to vanish in Rabat"))));

        fridaCities.add(new City("Rabat",
                new ArrayList<>(Arrays.asList("Final conversation leading to guess")),
                new ArrayList<>(Arrays.asList())));  // Only final guess in Rabat

        scenarios.add(new Scenario("Frida Kahlo", "Female", "She was often seen with vibrant clothing and a bold artistic style.", fridaCities));

        // Scenario 4: Winston Churchill
        ArrayList<City> winstonCities = new ArrayList<>();
        winstonCities.add(new City("Casablanca",
                new ArrayList<>(Arrays.asList("A man with a bowler hat was seen smoking a cigar", "He was overheard talking about war strategies")),
                new ArrayList<>(Arrays.asList("A café patron heard a man with a British accent talking about Marrakech"))));

        winstonCities.add(new City("Marrakech",
                new ArrayList<>(Arrays.asList("Someone spotted an older man discussing military tactics", "He mentioned heading to Fes for a meeting")),
                new ArrayList<>(Arrays.asList("A café visitor mentioned a man with a bowler hat", "Talk of plans to travel to Fes"))));

        winstonCities.add(new City("Fes",
                new ArrayList<>(Arrays.asList("A man was heard discussing international politics", "He mentioned traveling to Tangier")),
                new ArrayList<>(Arrays.asList("He was talking about meeting someone in Tangier"))));

        winstonCities.add(new City("Tangier",
                new ArrayList<>(Arrays.asList("A man with a bowler hat was seen heading to Rabat", "He talked about important negotiations")),
                new ArrayList<>(Arrays.asList("Talk of final plans in Rabat"))));

        winstonCities.add(new City("Rabat",
                new ArrayList<>(Arrays.asList("Final conversation leading to guess")),
                new ArrayList<>(Arrays.asList())));  // Only final guess in Rabat

        scenarios.add(new Scenario("Winston Churchill", "Male", "He was often seen with a bowler hat and a cigar, discussing politics.", winstonCities));

        // Scenario 5: Stephen Curry
        ArrayList<City> curryCities = new ArrayList<>();
        curryCities.add(new City("Casablanca",
                new ArrayList<>(Arrays.asList("Someone mentioned a tall man dribbling a basketball", "He was overheard talking about Marrakech")),
                new ArrayList<>(Arrays.asList("A café patron noticed a man with incredible shooting form", "He was heading to Marrakech"))));

        curryCities.add(new City("Marrakech",
                new ArrayList<>(Arrays.asList("A man was seen practicing basketball shots", "He mentioned heading to Fes for a charity event")),
                new ArrayList<>(Arrays.asList("A basketball fan mentioned meeting a man who looked like a famous player", "Talk of traveling to Fes"))));

        curryCities.add(new City("Fes",
                new ArrayList<>(Arrays.asList("Someone was excited about seeing a basketball player", "He mentioned going to Tangier")),
                new ArrayList<>(Arrays.asList("A café owner recognized him from TV", "He was heading to Tangier"))));

        curryCities.add(new City("Tangier",
                new ArrayList<>(Arrays.asList("A tall man with a basketball was seen heading to Rabat", "He was discussing a charity match")),
                new ArrayList<>(Arrays.asList("Talk of final plans in Rabat"))));

        curryCities.add(new City("Rabat",
                new ArrayList<>(Arrays.asList("Final conversation leading to guess")),
                new ArrayList<>(Arrays.asList())));  // Only final guess in Rabat

        scenarios.add(new Scenario("Stephen Curry", "Male", "He was seen dribbling a basketball and is known for his incredible shooting form.", curryCities));

        return scenarios;
    }
}
