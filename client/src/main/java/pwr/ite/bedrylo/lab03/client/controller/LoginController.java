package pwr.ite.bedrylo.lab03.client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.springframework.web.reactive.function.client.WebClient;
import pwr.ite.bedrylo.lab03.client.dto.PersonDto;
import pwr.ite.bedrylo.lab03.client.service.HttpRequestHandler;
import pwr.ite.bedrylo.lab03.client.Application;
import pwr.ite.bedrylo.lab03.client.service.SceneController;

import java.io.IOException;

public class LoginController {

    private final SceneController sceneController = SceneController.getInstance();
    
    @FXML
    private TextField peselInputField;


    @FXML
    private Label welcomeText;
    
    
    @FXML
    protected void onHelloButtonClick() throws IOException {
        HttpRequestHandler<Boolean> httpRequestHandler = new HttpRequestHandler<>(WebClient.create());
        Boolean connection = false;
        try {
            connection = httpRequestHandler.sendRequest("/conn/test", "GET", Boolean.class);
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
            sceneController.switchScreen("error");
        }
        HttpRequestHandler<PersonDto> personDtoHttpRequestHandler = new HttpRequestHandler<>(WebClient.create());
        PersonDto personDto = null;
        try {
            personDto = personDtoHttpRequestHandler.sendRequest("/person/get/" + peselInputField.getText(), "GET", PersonDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (personDto == null) {
            System.out.println(personDto);
            welcomeText.setText("Nie ma takiego użytkownika!");
            sceneController.switchScreen("error");
        } else {
            sceneController.setActiveUser(personDto);
            sceneController.switchScreen(personDto.getRole().getName());
        }
        return;
    }
}