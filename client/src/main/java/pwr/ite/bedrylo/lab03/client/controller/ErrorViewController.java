package pwr.ite.bedrylo.lab03.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ErrorViewController {
    
    @FXML
    private Button closeButton;
    
    @FXML
    protected void onErrorScreenButton(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    } 
    
}
