package pwr.ite.bedrylo.lab03.client.service;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import lombok.Getter;
import lombok.Setter;
import pwr.ite.bedrylo.lab03.client.Application;
import pwr.ite.bedrylo.lab03.client.dto.PersonDto;

import java.util.HashMap;

public class ScreenController {
    
    private static ScreenController instance;
    private  final HashMap<String, Pane> screenMap = new HashMap<>();
    
    @Getter
    @Setter
    private  Scene main;
    
    @Getter
    @Setter
    private PersonDto activeUser;

    public ScreenController(Scene main) {
        this.main = main;
    }

    protected void addScene(String name, String paneName){
        screenMap.put(name, loadFXMLFile(paneName));
    }

    protected void removeScreen(String name){
        screenMap.remove(name);
    }

    protected void activate(String name){
        main.setRoot( screenMap.get(name) );
    }
    
    public static ScreenController getInstance() {
        if (instance == null) {
            instance = new ScreenController(null);
        }
        return instance;
    }
    
    private Pane loadFXMLFile(String paneName) {
        Pane pane = null;
        try {
            pane = FXMLLoader.load(Application.class.getResource("/" + paneName + ".fxml").load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pane;
    }
}