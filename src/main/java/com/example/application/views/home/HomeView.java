package com.example.application.views.home;

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


@PageTitle("Home")
@Menu(icon = "line-awesome/svg/pencil-ruler-solid.svg", order = 0)
@Route(value = "")
@RouteAlias(value = "")
public class HomeView extends Composite<VerticalLayout> {

    private OpenAIConversation conversation;
    private TextField askText;
    private String question ;
    private Paragraph replyText;
    private Button askButton = new Button();
    private Button questionButton = new Button();
    private String buttonQuestion;
    class MyClickListener
            implements ComponentEventListener<ClickEvent<Button>> {
        int count = 0;

        @Override

        public void onComponentEvent(ClickEvent<Button> event) {
            //event.getSource()
            //        .setText("You have clicked me " + (++count) + " times");

            String reply= conversation.askQuestion("You are Plato", askText.getValue());
            replyText.setText(reply);
        }

    }
    class questionButtonListener
            implements ComponentEventListener<ClickEvent<Button>> {
        int count = 0;

        @Override

        public void onComponentEvent(ClickEvent<Button> event) {
            //event.getSource()
            //        .setText("You have clicked me " + (++count) + " times");
            buttonQuestion = conversation.generateSampleQuestions("San Francisco", 1, 20).get(0);
            questionButton.setText(buttonQuestion);
            String reply= conversation.askQuestion("You are Plato", buttonQuestion);
            replyText.setText(reply);

        }

    }
    public HomeView() {
        conversation = new OpenAIConversation("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", "gpt-4o-mini");
        askText = new TextField();


        replyText = new Paragraph();
        replyText.setWidth("80%");
        replyText.setHeight("300px");
        replyText.getStyle().set("border", "1px solid black");
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        askText.setLabel("Ask Socrates a Question");
        askText.setWidth("min-content");

        //OpenAIConversation newConvo = new OpenAIConversation();
        //for later- create a random variable to get different answer from list...later


        askButton.setText("Ask");
        questionButton.setText("click me");
        askButton.setWidth("min-content");
        questionButton.setWidth("min-content");
        askButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        questionButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Image img = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/6/61/San_Francisco_from_the_Marin_Headlands_in_August_2022.jpg/288px-San_Francisco_from_the_Marin_Headlands_in_August_2022.jpg", "Example Image");

        // Set width and height if necessary
        img.setWidth("300px");
        img.setHeight("200px");

        //replyText.setWidth("min-content");
        getContent().add(askText);
        getContent().add(askButton);
        getContent().add(questionButton);
        getContent().add(replyText);
        getContent().add(img);


        askButton.addClickListener(new MyClickListener());
        int count =0;

            questionButton.addClickListener(new questionButtonListener());
            buttonQuestion = conversation.generateSampleQuestions("San Francisco", 1, 20).get(0);

        }

    }



