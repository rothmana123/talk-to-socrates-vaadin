package com.example.application.views.home;

import com.example.application.City;
import com.example.application.Game;
import com.example.application.Scenario;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import ai.peoplecode.OpenAIConversation;
import com.vaadin.flow.component.html.Label;

import java.util.ArrayList;

@PageTitle("Home")
@Menu(icon = "line-awesome/svg/pencil-ruler-solid.svg", order = 0)
@Route(value = "")
@RouteAlias(value = "")
public class HomeView extends Composite<VerticalLayout> {

    private Game game;
    private Scenario currentScenario;
    private City currentCity;
    private int currentCityIndex = 0;

    private OpenAIConversation conversation;
    private TextField askText;
    private String question;

    private Paragraph replyText;
    private Button marketButton = new Button("Go to Market");
    private Button cafeButton = new Button("Go to Café");
    private Button taxiButton = new Button("Take Taxi to New City");
    private Label cityLabel;
    //private Paragraph evidenceList;  // Evidence accumulated
    private Label evidenceList;  // Declare as Label for HTML support

    private Button resetButton = new Button("Reset Game");  // New Reset button
    //private String currentCity = "Casablanca";  // Initial city
    private TextField guessTextField = new TextField("Your Guess");  // New text field for player’s guess
    private Button submitGuessButton = new Button("Submit Guess");  // Button to submit the guess


    private boolean isLastCity = false;




    // Define the game context here
    private static final String GAME_CONTEXT = """
        You are helping a player solve a mystery set in Morocco. 
        The goal is to visit a series of cities, have conversations with locals, and collect 5 pieces of evidence.
        At the end of the game, the player must present the evidence to a magistrate and name the historical figure they are chasing.
        Each city offers limited clues, and the player has to strategically choose where to investigate.
        Available actions for the player are: go to market, go to cafe, get in a taxi to travel to the next city.
    """;

    // Listener for Market button
//    class MarketClickListener implements ComponentEventListener<ClickEvent<Button>> {
//        @Override
//        public void onComponentEvent(ClickEvent<Button> event) {
//            // Call the AI and get a response for visiting the market
//            String reply = conversation.askQuestion(GAME_CONTEXT, "Go to market in " + currentCity);
//            replyText.setText(reply);
//            // Add some evidence (this would be more dynamic in a full game)
//            evidenceList.setText(evidenceList.getText() + "\n- Evidence found in the market at " + currentCity);
//        }
//    }
    // Listener for Café button
//    class CafeClickListener implements ComponentEventListener<ClickEvent<Button>> {
//        @Override
//        public void onComponentEvent(ClickEvent<Button> event) {
//            // Call the AI and get a response for visiting the café
//            String reply = conversation.askQuestion(GAME_CONTEXT, "Go to café in " + currentCity);
//            replyText.setText(reply);
//            // Add some evidence (this would be more dynamic in a full game)
//            evidenceList.setText(evidenceList.getText() + "\n- Evidence found in the café at " + currentCity);
//        }
//    }
//
//    // Listener for Taxi button
//    class TaxiClickListener implements ComponentEventListener<ClickEvent<Button>> {
//        @Override
//        public void onComponentEvent(ClickEvent<Button> event) {
//            // Simulate traveling to a new city (this would be dynamic in a full game)
//            currentCity = "Marrakech";  // Example: travel to Marrakech
//            cityLabel.setText("Current City: " + currentCity);
//            replyText.setText("You have traveled to " + currentCity + ". Choose your next action.");
//        }
//    }
//    class ResetClickListener implements ComponentEventListener<ClickEvent<Button>> {
//        @Override
//        public void onComponentEvent(ClickEvent<Button> event) {
//            // Reset all relevant fields to initial state
//            currentCity = "Casablanca";  // Reset to initial city
//            cityLabel.setText("Current City: " + currentCity);  // Update the city label
//            evidenceList.setText("Evidence Accumulated:");  // Clear evidence
//            replyText.setText(GAME_CONTEXT);  // Show initial game context
//        }
//    }

    // Listener for Market button
    class MarketClickListener implements ComponentEventListener<ClickEvent<Button>> {
        @Override
        public void onComponentEvent(ClickEvent<Button> event) {
            // Get the market conversations for the current city
            ArrayList<String> marketConversations = currentCity.getMarketConversations();
            if (!marketConversations.isEmpty()) {
                String conversation = marketConversations.get(0);  // Use the first conversation for simplicity
                replyText.setText(conversation);

                // Add evidence with line break for a new line
                String existingEvidence = evidenceList.getElement().getProperty("innerHTML", "");
                //evidenceList.setText(existingEvidence + "<br>- Evidence: " + conversation);
                evidenceList.getElement().setProperty("innerHTML", existingEvidence + "<br>- Evidence: " + conversation);

            }
            // If this is the last city and both conversations have been visited, prompt for a guess
            if (isLastCity) {
                promptForGuess();
            }
        }
    }
    // Listener for Café button
    class CafeClickListener implements ComponentEventListener<ClickEvent<Button>> {
        @Override
        public void onComponentEvent(ClickEvent<Button> event) {
            // Get the café conversations for the current city
            ArrayList<String> cafeConversations = currentCity.getCafeConversations();
            if (!cafeConversations.isEmpty()) {
                String conversation = cafeConversations.get(0);  // Use the first conversation for simplicity
                replyText.setText(conversation);
                // Add evidence
                String existingEvidence = evidenceList.getElement().getProperty("innerHTML", "");
                //evidenceList.setText(existingEvidence + "<br>- Evidence: " + conversation);
                evidenceList.getElement().setProperty("innerHTML", existingEvidence + "<br>- Evidence: " + conversation);

            }
            // If this is the last city and both conversations have been visited, prompt for a guess
            if (isLastCity) {
                promptForGuess();
            }
        }
    }

    // Listener for Taxi button
    class TaxiClickListener implements ComponentEventListener<ClickEvent<Button>> {
        @Override
        public void onComponentEvent(ClickEvent<Button> event) {
            // Move to the next city in the scenario
            if (currentCityIndex < currentScenario.getCities().size() - 1) {
                currentCityIndex++;
                currentCity = currentScenario.getCities().get(currentCityIndex);
                cityLabel.setText("Current City: " + currentCity.getName());
                replyText.setText("You have traveled to " + currentCity.getName() + ". Choose your next action.");

                // Check if this is the last city
                if (currentCityIndex == currentScenario.getCities().size() - 1) {
                    isLastCity = true;
                }
            }
        }
    }

    // Listener for Reset button
    class ResetClickListener implements ComponentEventListener<ClickEvent<Button>> {
        @Override
        public void onComponentEvent(ClickEvent<Button> event) {
            // Reset the game by starting a new scenario
            startNewGame();
        }
    }

    class SubmitGuessListener implements ComponentEventListener<ClickEvent<Button>> {
        @Override
        public void onComponentEvent(ClickEvent<Button> event) {
            String guess = guessTextField.getValue().trim();
            if (guess.equalsIgnoreCase(currentScenario.getCriminal())) {
                replyText.setText("Correct! The famous person hiding in Morocco is " + currentScenario.getCriminal() + "!");
            } else {
                replyText.setText("Incorrect. Try again.");
            }
        }
    }

//



    // Constructor for setting up the UI
    public HomeView() {
        // Create the OpenAIConversation object
        conversation = new OpenAIConversation("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", "gpt-4o-mini");

        // Create the city label
        cityLabel = new Label("Current City: ");  // Display the initial city

        // Create the evidence list
        evidenceList = new Label("Evidence Accumulated:");  // Display accumulated evidence

        //evidenceList = new Paragraph("Evidence Accumulated:");  // Display accumulated evidence
        evidenceList.setWidth("80%");
        evidenceList.getStyle().set("border", "1px solid black");

        replyText = new Paragraph();
        replyText.setWidth("80%");
        replyText.setHeight("300px");
        replyText.getStyle().set("border", "1px solid black");

        getContent().setWidth("100%");

        // Set up buttons and layout
        marketButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cafeButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        taxiButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        resetButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);  // Reset button style
        submitGuessButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);  // Style for the guess button



        // Set the initial instructions (game context) in the replyText
        replyText.setText(GAME_CONTEXT);

        // Add components to the layout
        getContent().add(cityLabel);  // Add the current city label
        getContent().add(marketButton);
        getContent().add(cafeButton);
        getContent().add(taxiButton);
        getContent().add(replyText);
        getContent().add(evidenceList);
        getContent().add(guessTextField);  // Add the guess input field
        getContent().add(submitGuessButton);
        getContent().add(resetButton);  // Add the reset button


        // Attach click listeners to the buttons
        marketButton.addClickListener(new MarketClickListener());
        cafeButton.addClickListener(new CafeClickListener());
        taxiButton.addClickListener(new TaxiClickListener());
        resetButton.addClickListener(new ResetClickListener());
        submitGuessButton.addClickListener(new SubmitGuessListener());


        // Start the first game when the UI is initialized
        startNewGame();
    }
    // Start a new game by selecting a random scenario
    private void startNewGame() {
        // Initialize the game and load scenarios
        game = new Game();
        currentScenario = game.loadScenarios().get((int) (Math.random() * game.loadScenarios().size()));

        // Start in the first city of the scenario
        currentCityIndex = 0;
        currentCity = currentScenario.getCities().get(currentCityIndex);
        cityLabel.setText("Current City: " + currentCity.getName());

        // Clear evidence and set initial game context
        evidenceList.getElement().setProperty("innerHTML", "Evidence Accumulated: ");  // Clear accumulated evidence
        replyText.setText("You have started a new game! The first city is " + currentCity.getName() + ".");
        guessTextField.clear();  // Clear the guess field for the new game
        isLastCity = false;  // Reset last city flag
    }
    private void promptForGuess() {
        replyText.setText("You have gathered enough evidence! Who do you think the famous person hiding in Morocco is?");
        // In a real implementation, you'd allow the player to input their guess and check if it's correct
    }
}
