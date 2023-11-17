package pwr.ite.bedrylo.lab03.client.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.springframework.web.reactive.function.client.WebClient;
import pwr.ite.bedrylo.lab03.client.dto.RegistrationDto;
import pwr.ite.bedrylo.lab03.client.dto.TreeDto;
import pwr.ite.bedrylo.lab03.client.model.TreeTableModel;
import pwr.ite.bedrylo.lab03.client.service.HttpRequestHandler;
import pwr.ite.bedrylo.lab03.client.service.SceneController;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RegistrationAddViewController {

    @FXML
    private TextField treeNameInput;
    @FXML
    private TextField treeDiameterInput;
    @FXML
    private Button registrationSendButton;
    @FXML
    private Button backButton;
    @FXML
    private TableView treeTable;
    @FXML
    private Button treeAddButton;
    @FXML
    private Label message;

    private ObservableList<TreeDto> treeList;
    
    private HttpRequestHandler<RegistrationDto> sendRegistrationHttpRequestHandler = new HttpRequestHandler<>(WebClient.create());
    
    private HttpRequestHandler<Set<TreeDto>> treeDtoSetHttpRequestHandler = new HttpRequestHandler<>(WebClient.create());
    
    private SceneController sceneController = SceneController.getInstance();

    public void addTree(MouseEvent mouseEvent) {
        if (treeList == null) {
            treeList = FXCollections.observableArrayList();
        }
        TreeDto treeDto = new TreeDto();
        treeDto.setName(treeNameInput.getText());
        treeDto.setDiameter(Float.parseFloat(treeDiameterInput.getText()));
        treeList.add(treeDto);
        treeTable.setItems(treeList.stream().map(TreeTableModel::new).collect(Collectors.toCollection(FXCollections::observableArrayList)));
    }
    
    public void sendRegistration(MouseEvent mouseEvent) {
        if (treeList == null || treeList.isEmpty()) {
            message.setText("Nie można wysłać pustej rejestracji");
            return;
        } else {
            message.setText("");
            RegistrationDto registrationDto = new RegistrationDto();
            registrationDto.setRegisteredBy(sceneController.getActiveUser());
            RegistrationDto responseRegistrationDto = sendRegistrationHttpRequestHandler.sendRequest("/registration/add", "POST", RegistrationDto.class, registrationDto, RegistrationDto.class);
            if (responseRegistrationDto == null) {
                message.setText("Wystąpił błąd");
                return;
            }
            Set<TreeDto> treeDtoSet = new HashSet<>(treeList);
            treeList.forEach(treeDto -> {
                treeDto.setRegistration(responseRegistrationDto);
                treeDtoSet.add(treeDto);
            });
            Set<TreeDto> responseTreeDtoSet = treeDtoSetHttpRequestHandler.sendRequest("/tree/add/bulk", "POST", Set.class, treeDtoSet, Set.class);
            if (responseTreeDtoSet != null) {
                message.setText("Wysłano pomyślnie");
            } else {
                message.setText("Wystąpił błąd");
                return;
            }
        }
    }

    public void goBack(MouseEvent mouseEvent) {
        sceneController.switchScreen("client");
    }
}
