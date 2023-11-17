package pwr.ite.bedrylo.lab03.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pwr.ite.bedrylo.lab03.client.service.SceneController;

import java.io.IOException;

public class Application extends javafx.application.Application {
    
    private final SceneController sceneController = SceneController.getInstance();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1000,600);
        stage.setTitle("Wycinka drzew manager 3000turbo++");
        sceneController.setMainScene(scene);
        sceneController.addScreen("login", "login-view.fxml");
        sceneController.addScreen("client", "client-view.fxml");
        sceneController.addScreen("employee", "employee-view.fxml");
        sceneController.addScreen("boss", "boss-view.fxml");
        sceneController.addScreen("error", "error-view.fxml");
        sceneController.addScreen("registration", "registration-add-view.fxml");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}