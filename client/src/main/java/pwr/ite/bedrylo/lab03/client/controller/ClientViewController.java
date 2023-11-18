package pwr.ite.bedrylo.lab03.client.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.springframework.web.reactive.function.client.WebClient;
import pwr.ite.bedrylo.lab03.client.dto.DecisionDto;
import pwr.ite.bedrylo.lab03.client.dto.RegistrationDto;
import pwr.ite.bedrylo.lab03.client.dto.TreeDto;
import pwr.ite.bedrylo.lab03.client.enums.Status;
import pwr.ite.bedrylo.lab03.client.model.PersonRegistrationTableModel;
import pwr.ite.bedrylo.lab03.client.model.TreeTableModel;
import pwr.ite.bedrylo.lab03.client.service.HttpRequestHandler;
import pwr.ite.bedrylo.lab03.client.service.SceneController;

import java.util.Arrays;
import java.util.stream.Collectors;


public class ClientViewController {
    
    private final SceneController sceneController = SceneController.getInstance();
    private final HttpRequestHandler<RegistrationDto> registrationDtoHttpRequestHandler = new HttpRequestHandler<>(WebClient.create());
    
    private final HttpRequestHandler<TreeDto> treeDtoHttpRequestHandler = new HttpRequestHandler<>(WebClient.create());
    
    @FXML
    private TableView<PersonRegistrationTableModel> tableView;
    
    @FXML
    private Label currentId;
    
    @FXML
    private Label currentStatus;
    
    @FXML
    private Label currentDate;
    
    @FXML
    private TextFlow currentDecision;
    
    @FXML 
    private TableView<TreeTableModel> treeTable;
    
    @FXML
    private void onUpdateButtonClick(){
        var data = registrationDtoHttpRequestHandler.sendRequest("/registration/get/person/"+sceneController.getActiveUser().getId(), "GET", RegistrationDto[].class);
        tableView.setItems(FXCollections.observableList(Arrays.stream(data).map(PersonRegistrationTableModel::new).collect(Collectors.toList())));
    }

    @FXML
    protected void cellSelectedEvent(MouseEvent mouseEvent) {
        if(tableView.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        var selectedRegistration = tableView.getSelectionModel().getSelectedItem().getRegistrationDto();
        currentId.setText(selectedRegistration.getId().toString());
        currentStatus.setText(selectedRegistration.getRegistrationStatus().getName());
        currentDate.setText(selectedRegistration.getRegistrationDate().toString());
        
        var treeData = treeDtoHttpRequestHandler.sendRequest("/tree/get/registration/"+selectedRegistration.getId(), "GET", TreeDto[].class);
        treeTable.setItems(FXCollections.observableList(Arrays.stream(treeData).map(TreeTableModel::new).collect(Collectors.toList())));
        System.out.println(treeTable.getItems());
        if(selectedRegistration.getRegistrationStatus() == Status.FINISHED) {
            HttpRequestHandler<DecisionDto> decisionDtoHttpRequestHandler = new HttpRequestHandler<>(WebClient.create());
            var decision = decisionDtoHttpRequestHandler.sendRequest("/decision/get/registrationid/"+selectedRegistration.getId(), "GET", DecisionDto.class);
            Text decisionText = new Text();
            decisionText.setText(decision.getDescription());
            currentDecision.getChildren().add(decisionText);
        } else 
            currentDecision.getChildren().clear();
    }


    public void onAddButtonClick(ActionEvent actionEvent) {
        sceneController.switchScreen("registration");
    }
}
