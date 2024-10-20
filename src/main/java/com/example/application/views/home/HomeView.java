package com.example.application.views.home;

import com.example.application.City;
import com.example.application.Game;
import com.example.application.Scenario;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
//note for git

import java.util.Random;
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
    //private String evidence = "";  // Class-level variable to store the evidence
    private ArrayList<String> evidence = new ArrayList<>();
    private TextField askText;
    private String question;

    private Paragraph replyText;
    private Button marketButton = new Button("Go to Market");
    private Button cafeButton = new Button("Go to Café");
    private Label cityLabel;
    private Label evidenceList;  // Declare as Label for HTML support
    private TextField addEvidenceTextField = new TextField("Add New Evidence");  // New text field to add evidence
    private Button addEvidenceButton = new Button("Add Evidence");  // Button to add the entered evidence

    private TextField questionTextField = new TextField("Your Question");  // TextField for asking questions
    private Button questionButton = new Button("Submit Question");  // Button for submitting questions

    private Button resetButton = new Button("Reset Game");  // Reset button
    private TextField guessTextField = new TextField("Your Guess");  // New text field for player’s guess
    private Button submitGuessButton = new Button("Submit Guess");  // Button to submit the guess

    private boolean isLastCity = false;

    // Define buttons for each city
    private Button cityButton1 = new Button("Casablanca");
    private Button cityButton2 = new Button("Marrakech");
    private Button cityButton3 = new Button("Fes");
    private Button cityButton4 = new Button("Tangier");
    private Button cityButton5 = new Button("Rabat");

    // Define the game context here
    private static final String GAME_CONTEXT = """
                You are helping a player solve a mystery set in Morocco.
                A famous person is hiding in Morocco, you are trying to figure out who it is.
                The goal is to visit a series of cities, have conversations with locals, and collect evidence.
                At the end of the 5th day, the player must be able to identify the historical character or lose the game.
                Each city offers limited clues, and the player has to strategically choose where to investigate.
                Available actions for the player are: go to market, go to cafe, get in a taxi to travel to the next city.
            """;

    // Listener for Market button
    class MarketClickListener implements ComponentEventListener<ClickEvent<Button>> {
        @Override
        public void onComponentEvent(ClickEvent<Button> event) {
            String location = "market";

            ArrayList<String> marketConversations = currentCity.getMarketConversations();
            if (!marketConversations.isEmpty()) {
                for (String marketEvidence : marketConversations) {
                    evidence.add(marketEvidence);  // Accumulate each piece of evidence
                }
                replyText.setText("You are in the " + location + ". You can ask a local merchant questions about the mystery person.");
            }

            if (isLastCity) {
                promptForGuess();
            }
        }
    }

    // Listener for Café button
    class CafeClickListener implements ComponentEventListener<ClickEvent<Button>> {
        @Override
        public void onComponentEvent(ClickEvent<Button> event) {
            String location = "cafe";
            ArrayList<String> cafeConversations = currentCity.getCafeConversations();
            if (!cafeConversations.isEmpty()) {
                for (String cafeEvidence : cafeConversations) {
                    evidence.add(cafeEvidence);  // Accumulate each piece of evidence
                }

                replyText.setText("You are in the " + location + ". You can ask people at the cafe questions about the mystery person.");
            }

            if (isLastCity) {
                promptForGuess();
            }
        }
    }

    // Listeners for each city button
//    class CityButtonListener implements ComponentEventListener<ClickEvent<Button>> {
//        private final int cityIndex;
//
//        public CityButtonListener(int cityIndex) {
//            this.cityIndex = cityIndex;
//        }
//
//        @Override
//        public void onComponentEvent(ClickEvent<Button> event) {
//            // Travel to the selected city
//            currentCityIndex = cityIndex;
//            currentCity = currentScenario.getCities().get(currentCityIndex);
//            cityLabel.setText("Current City: " + currentCity.getName());
//            replyText.setText("You have traveled to " + currentCity.getName() + ". Choose your next action.");
//
//            if (currentCityIndex == currentScenario.getCities().size() - 1) {
//                isLastCity = true;
//            }
//        }
//    }
    // Listeners for each city button
    class CityButtonListener implements ComponentEventListener<ClickEvent<Button>> {
        private final int cityIndex;
        private final String cityName;

        public CityButtonListener(int cityIndex, String cityName) {
            this.cityIndex = cityIndex;
            this.cityName = cityName;
        }

        @Override
        public void onComponentEvent(ClickEvent<Button> event) {
            // Travel to the selected city
            currentCityIndex = cityIndex;
            currentCity = currentScenario.getCities().get(currentCityIndex);
            cityLabel.setText("Current City: " + currentCity.getName());
            replyText.setText("You have traveled to " + currentCity.getName() + ". Choose your next action.");

            // Check if the chosen city is correct
            checkCity(cityName);

            if (currentCityIndex == currentScenario.getCities().size() - 1) {
                isLastCity = true;
            }
        }
    }

    // Listener for Reset button
    class ResetClickListener implements ComponentEventListener<ClickEvent<Button>> {
        @Override
        public void onComponentEvent(ClickEvent<Button> event) {
            startNewGame();
        }
    }

    // Listener for adding evidence
    class AddEvidenceListener implements ComponentEventListener<ClickEvent<Button>> {
        @Override
        public void onComponentEvent(ClickEvent<Button> event) {
            String newEvidence = addEvidenceTextField.getValue();
            if (!newEvidence.isEmpty()) {
                String existingEvidence = evidenceList.getElement().getProperty("innerHTML", "");
                evidenceList.getElement().setProperty("innerHTML", existingEvidence + "<br>- " + newEvidence);
                addEvidenceTextField.clear();  // Clear the input field after adding
            }
        }
    }

    // Listener for submitting questions (conversation with ChatGPT)
    // Listener for submitting questions (conversation with ChatGPT)
    class QuestionButtonListener implements ComponentEventListener<ClickEvent<Button>> {
        @Override
        public void onComponentEvent(ClickEvent<Button> event) {
            String question = questionTextField.getValue();

            // Accumulate all evidence for context
//            StringBuilder evidenceDetails = new StringBuilder();
//            for (String clue : evidence) {
//                evidenceDetails.append("\n- ").append(clue);
//            }

            // Include all collected evidence in the conversation context
            String context = "You are a merchant in " + currentCity.getName() + ". The evidence collected so far is: " + evidence.toString() + ". Please answer the player's question based on this information.  Do not mention that you are AI.  You are either a merchant or a cafe owner.  Also, dont mention evidence that indicates that the suspect is coming to the current city, only evidence that mentions the next possible city.  Lastly, you may add additional details from your knowledge of the person, beyond what is provided as evidence";
            String reply = conversation.askQuestion(context, question);

            // Display the response from the API
            replyText.setText(reply);
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

    public HomeView() {
        conversation = new OpenAIConversation("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", "gpt-4");

        cityLabel = new Label("Current City: ");
        evidenceList = new Label("Evidence Accumulated:");
        evidenceList.setWidth("80%");
        evidenceList.getStyle().set("border", "1px solid black");

        replyText = new Paragraph();
        replyText.setWidth("80%");
        replyText.setHeight("300px");
        replyText.getStyle().set("border", "1px solid black");

        getContent().setWidth("100%");

        marketButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cafeButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        resetButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitGuessButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addEvidenceButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        questionButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // Buttons for each city
        cityButton1.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cityButton2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cityButton3.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cityButton4.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cityButton5.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        replyText.setText(GAME_CONTEXT);

        // Add the city buttons to a HorizontalLayout
        HorizontalLayout cityButtonsLayout = new HorizontalLayout(cityButton1, cityButton2, cityButton3, cityButton4, cityButton5);

        // Add components to the layout
        getContent().add(cityLabel);
        getContent().add(questionTextField);
        getContent().add(questionButton);
        getContent().add(marketButton);
        getContent().add(cafeButton);
        getContent().add(cityButtonsLayout);  // Add the city buttons layout here
        getContent().add(replyText);
        getContent().add(evidenceList);
        getContent().add(addEvidenceTextField);
        getContent().add(addEvidenceButton);
        getContent().add(guessTextField);
        getContent().add(submitGuessButton);
        getContent().add(resetButton);

        // Attach click listeners
        marketButton.addClickListener(new MarketClickListener());
        cafeButton.addClickListener(new CafeClickListener());
        cityButton1.addClickListener(new CityButtonListener(0, "Casablanca"));
        cityButton2.addClickListener(new CityButtonListener(1, "Marrakech"));
        cityButton3.addClickListener(new CityButtonListener(2, "Fes"));
        cityButton4.addClickListener(new CityButtonListener(3, "Tangier"));
        cityButton5.addClickListener(new CityButtonListener(4, "Rabat"));
        resetButton.addClickListener(new ResetClickListener());
        submitGuessButton.addClickListener(new SubmitGuessListener());
        addEvidenceButton.addClickListener(new AddEvidenceListener());
        questionButton.addClickListener(new QuestionButtonListener());

        startNewGame();
    }

    private void startNewGame() {
        game = new Game();
        currentScenario = game.loadScenarios().get((int) (Math.random() * game.loadScenarios().size()));

        currentCityIndex = 0;
        currentCity = currentScenario.getCities().get(currentCityIndex);
        cityLabel.setText("Current City: " + currentCity.getName());

        String initialClue = "The person you're looking for is " + currentScenario.getGender() + ". " + currentScenario.getInitialDetail();

        evidenceList.getElement().setProperty("innerHTML", "Evidence Accumulated: ");
        replyText.setText("You have started a new game! The first city is " + currentCity.getName() + ".\n" + initialClue);
        guessTextField.clear();
        isLastCity = false;
    }

    private void checkCity(String cityName) {
        if (currentCity.getName().equals(cityName)) {
            replyText.setText("Correct city! You may now investigate.");
        } else {
            replyText.setText("This isn't the correct city. The person hasn't been seen here.");
        }
    }

    private void promptForGuess() {
        replyText.setText("You have gathered enough evidence! Who do you think the famous person hiding in Morocco is?");
    }
}

