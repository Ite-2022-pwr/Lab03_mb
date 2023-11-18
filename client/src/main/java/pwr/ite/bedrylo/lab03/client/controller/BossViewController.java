package pwr.ite.bedrylo.lab03.client.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.springframework.web.reactive.function.client.WebClient;
import pwr.ite.bedrylo.lab03.client.dto.*;
import pwr.ite.bedrylo.lab03.client.enums.Status;
import pwr.ite.bedrylo.lab03.client.model.EmployeeRegistrationTableModel;
import pwr.ite.bedrylo.lab03.client.model.TreeTableModel;
import pwr.ite.bedrylo.lab03.client.service.HttpRequestHandler;
import pwr.ite.bedrylo.lab03.client.service.SceneController;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BossViewController {

    @FXML
    private TableView<EmployeeRegistrationTableModel> registrationTable;

    @FXML
    private TableView<TreeTableModel> treeTable;

    @FXML
    private Label currentId;

    @FXML
    private ComboBox<Status> statusComboBox;

    @FXML
    private Label currentDate;

    @FXML
    private Label currentRegisteredByName;

    @FXML
    private Label currentRegisteredByPesel;

    @FXML
    private TextFlow raportField;
    
    @FXML
    private TextArea decisionField;

    private RegistrationDto selectedRegistration;

    private RaportDto raportData;
    
    private DecisionDto decisionData;

    private final HttpRequestHandler<TreeDto> treeDtoHttpRequestHandler = new HttpRequestHandler<>(WebClient.create());




    private final SceneController sceneController = SceneController.getInstance();

    private HttpRequestHandler<RegistrationDto> registrationDtoHttpHandler = new HttpRequestHandler<>(WebClient.create());

    private HttpRequestHandler<RaportDto> raportDtoHttpRequestHandler = new HttpRequestHandler<>(WebClient.create());
    
    private HttpRequestHandler<DecisionDto> decisionDtoHttpRequestHandler = new HttpRequestHandler<>(WebClient.create());
    
    public void clearFields(){
        currentId.setText("");
        statusComboBox.setValue(null);
        currentDate.setText("");
        currentRegisteredByName.setText("");
        currentRegisteredByPesel.setText("");
        raportField.getChildren().clear();
        decisionField.setText("");
        treeTable.setItems(FXCollections.observableList(Arrays.stream(new TreeDto[]{}).map(TreeTableModel::new).collect(Collectors.toList())));
    }

    private void updateRegistrationTable() {
        registrationDtoHttpHandler.sendRequest("/registration/update/"+selectedRegistration.getId(), "PUT", RegistrationDto.class, selectedRegistration, RegistrationDto.class);
        var data = registrationDtoHttpHandler.sendRequest("/registration/get/all", "GET", RegistrationDto[].class);
        registrationTable.setItems(FXCollections.observableList(Arrays.stream(data).map(EmployeeRegistrationTableModel::new).collect(Collectors.toList())));
        clearFields();
    }


    public void cellSelectedEvent(MouseEvent mouseEvent) {
        if(registrationTable.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        this.selectedRegistration = registrationTable.getSelectionModel().getSelectedItem().getRegistrationDto();
        currentId.setText(selectedRegistration.getId().toString());
        statusComboBox.setValue(selectedRegistration.getRegistrationStatus());
        currentDate.setText(selectedRegistration.getRegistrationDate().toString());
        currentRegisteredByName.setText(selectedRegistration.getRegisteredBy().getName());
        currentRegisteredByPesel.setText(selectedRegistration.getRegisteredBy().getPesel());
        raportData = raportDtoHttpRequestHandler.sendRequest("/raport/get/registration/"+selectedRegistration.getId(), "GET", RaportDto.class);
        raportField.getChildren().clear();
        if (raportData != null) {
            Text text = new Text(raportData.getDescription());
            raportField.getChildren().add(text);
        }
        var treeData = treeDtoHttpRequestHandler.sendRequest("/tree/get/registration/"+selectedRegistration.getId(), "GET", TreeDto[].class);
        treeTable.setItems(FXCollections.observableList(Arrays.stream(treeData).map(TreeTableModel::new).collect(Collectors.toList())));
        if(selectedRegistration.getRegistrationStatus() == Status.FINISHED) {
            decisionData = decisionDtoHttpRequestHandler.sendRequest("/decision/get/registrationid/"+selectedRegistration.getId(), "GET", DecisionDto.class);
            if (decisionData != null) {
                decisionField.setText(decisionData.getDescription());
            }
        } else {
            decisionField.setText("");
        }
            
            
    }

    public void sendDecision(MouseEvent mouseEvent) {
            if (selectedRegistration == null || decisionField.getText().isEmpty() || this.selectedRegistration.getRegistrationStatus() == Status.CREATED) {
                return;
            } else if (this.decisionData == null) {
                DecisionDto decisionDto = new DecisionDto();
                decisionDto.setDescription(decisionField.getText());
                RegistrationDto decisionRegistrationDto = new RegistrationDto();
                decisionRegistrationDto.setId(selectedRegistration.getId());
                decisionDto.setRegistration(decisionRegistrationDto);
                PersonDto decisionPersonDto = new PersonDto();
                decisionPersonDto.setId(sceneController.getActiveUser().getId());
                decisionDto.setApprovedBy(decisionPersonDto);
                DecisionDto responseDecisionDto = decisionDtoHttpRequestHandler.sendRequest("/decision/add", "POST", DecisionDto.class, decisionDto, DecisionDto.class);
                if (responseDecisionDto == null) {
                    return;
                }
                this.decisionData = responseDecisionDto;
            } else {
                DecisionDto decisionDto = new DecisionDto();
                decisionDto.setDescription(decisionField.getText());
                DecisionDto responseDecisionDto = decisionDtoHttpRequestHandler.sendRequest("/decision/update/"+this.decisionData.getId(), "PUT", DecisionDto.class, decisionDto, DecisionDto.class);
                if (responseDecisionDto == null) {
                    return;
                }
                this.decisionData = responseDecisionDto;
            }
        selectedRegistration.setRegistrationStatus(Status.FINISHED);
        updateRegistrationTable();
    }
    
    public void onUpdateAllClick(MouseEvent mouseEvent) {
        var data = registrationDtoHttpHandler.sendRequest("/registration/get/all", "GET", RegistrationDto[].class);
        registrationTable.setItems(FXCollections.observableList(Arrays.stream(data).map(EmployeeRegistrationTableModel::new).collect(Collectors.toList())));
        clearFields();
    }

    public void onUpdateInProgressClick(MouseEvent mouseEvent) {
        var data = registrationDtoHttpHandler.sendRequest("/registration/get/all/status/in_progress", "GET", RegistrationDto[].class);
        registrationTable.setItems(FXCollections.observableList(Arrays.stream(data).map(EmployeeRegistrationTableModel::new).collect(Collectors.toList())));
        selectedRegistration = null;
        clearFields();
    }

    public void onUpdateCreatedClick(MouseEvent mouseEvent) {
        var data = registrationDtoHttpHandler.sendRequest("/registration/get/all/status/created", "GET", RegistrationDto[].class);
        registrationTable.setItems(FXCollections.observableList(Arrays.stream(data).map(EmployeeRegistrationTableModel::new).collect(Collectors.toList())));
        clearFields();
    }

    public void onChangeStatusButtonClick(MouseEvent mouseEvent) {
        if(selectedRegistration == null) {
            return;
        }
        selectedRegistration.setRegistrationStatus(statusComboBox.getValue());
        updateRegistrationTable();
    }
}
