package pwr.ite.bedrylo.lab03.client.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import org.springframework.web.reactive.function.client.WebClient;
import pwr.ite.bedrylo.lab03.client.dto.DecisionDto;
import pwr.ite.bedrylo.lab03.client.dto.RaportDto;
import pwr.ite.bedrylo.lab03.client.dto.RegistrationDto;
import pwr.ite.bedrylo.lab03.client.dto.TreeDto;
import pwr.ite.bedrylo.lab03.client.enums.Status;
import pwr.ite.bedrylo.lab03.client.model.TreeTableModel;
import pwr.ite.bedrylo.lab03.client.service.HttpRequestHandler;
import pwr.ite.bedrylo.lab03.client.service.SceneController;
import pwr.ite.bedrylo.lab03.client.model.EmployeeRegistrationTableModel;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EmployeeViewController {
    
    @FXML
    private TableView<EmployeeRegistrationTableModel> registrationTable;
    
    @FXML
    private TableView<TreeTableModel> treeTable;
    
    @FXML
    private Label currentId;
    
    @FXML
    private Label currentStatus;
    
    @FXML
    private Label currentDate;
    
    @FXML
    private Label currentRegisteredByName;
    
    @FXML
    private Label currentRegisteredByPesel;
    
    @FXML
    private TextArea raportField;
    
    private RegistrationDto  selectedRegistration;
    
    private RaportDto raportData;

    private final HttpRequestHandler<TreeDto> treeDtoHttpRequestHandler = new HttpRequestHandler<>(WebClient.create());
    
    
    

    private final SceneController sceneController = SceneController.getInstance();
    
    private HttpRequestHandler<RegistrationDto> registrationDtoHttpHandler = new HttpRequestHandler<>(WebClient.create());
    
    private HttpRequestHandler<RaportDto> raportDtoHttpRequestHandler = new HttpRequestHandler<>(WebClient.create());
    
    @FXML
    protected void onUpdateButtonClick(MouseEvent mouseEvent) {
        var data = registrationDtoHttpHandler.sendRequest("/registration/get/all/status/in_progress", "GET", RegistrationDto[].class);
        registrationTable.setItems(FXCollections.observableList(Arrays.stream(data).map(EmployeeRegistrationTableModel::new).collect(Collectors.toList())));
        this.selectedRegistration = null;
        currentId.setText("");
        currentStatus.setText("");
        currentDate.setText("");
        currentRegisteredByName.setText("");
        currentRegisteredByPesel.setText("");
        treeTable.setItems(FXCollections.observableList(Arrays.stream(new TreeDto[]{}).map(TreeTableModel::new).collect(Collectors.toList())));
        
    }

    @FXML
    protected void cellSelectedEvent(MouseEvent mouseEvent) {
        
        if(registrationTable.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        this.selectedRegistration = registrationTable.getSelectionModel().getSelectedItem().getRegistrationDto();
        currentId.setText(selectedRegistration.getId().toString());
        currentStatus.setText(selectedRegistration.getRegistrationStatus().getName());
        currentDate.setText(selectedRegistration.getRegistrationDate().toString());
        currentRegisteredByName.setText(selectedRegistration.getRegisteredBy().getName());
        currentRegisteredByPesel.setText(selectedRegistration.getRegisteredBy().getPesel());
        raportField.setText("");
        var tempRaport = raportDtoHttpRequestHandler.sendRequest("/raport/get/registration/"+selectedRegistration.getId(), "GET", RaportDto.class);
        if(tempRaport != null) {
            this.raportData = tempRaport;
            raportField.setText(this.raportData.getDescription());
        } else {
            this.raportData = null;
        }
        var treeData = treeDtoHttpRequestHandler.sendRequest("/tree/get/registration/"+selectedRegistration.getId(), "GET", TreeDto[].class);
        treeTable.setItems(FXCollections.observableList(Arrays.stream(treeData).map(TreeTableModel::new).collect(Collectors.toList())));
        System.out.println(treeTable.getItems());
        
    }

    public void sendRaport(MouseEvent mouseEvent) {
        if (this.selectedRegistration == null || raportField.getText().isEmpty()) {
            return;
        } else if (this.raportData == null) {
            RaportDto raportDto = new RaportDto();
            raportDto.setDescription(raportField.getText());
            raportDto.setRegistration(this.selectedRegistration);
            RaportDto responseRaportDto = raportDtoHttpRequestHandler.sendRequest("/raport/add", "POST", RaportDto.class, raportDto, RaportDto.class);
            if (responseRaportDto == null) {
                return;
            }
            this.raportData = responseRaportDto;
        } else {
            RaportDto raportDto = new RaportDto();
            raportDto.setDescription(raportField.getText());
            RaportDto responseRaportDto = raportDtoHttpRequestHandler.sendRequest("/raport/update/"+this.raportData.getId(), "PUT", RaportDto.class, raportDto, RaportDto.class);
            if (responseRaportDto == null) {
                return;
            }
            this.raportData = responseRaportDto;
        }
    }
}
