package pwr.ite.bedrylo.lab03.client.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;
import pwr.ite.bedrylo.lab03.client.Application;
import pwr.ite.bedrylo.lab03.client.dto.PersonDto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneController {
    private static SceneController INSTANCE;
    private final Map<String, Pane> screens = new HashMap<>();

    @Getter @Setter private PersonDto activeUser;
    @Getter @Setter private Scene mainScene;

    public void addScreen(String scene, String fileName) {
        screens.put(scene, loadFromFxml(fileName));
    }

    public void switchScreen(String scene) {
        
        mainScene.setRoot(screens.get(scene));
        
    }

    private Pane loadFromFxml(String fileName) {
        try {
            return new FXMLLoader(Application.class.getResource(fileName)).load();
        } catch (IOException ex) {
            throw new IllegalArgumentException(String.format("Couldn't load scene from file %s", fileName), ex);
        }
    }

    public static SceneController getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new SceneController();
        }
        return INSTANCE;
    }
}