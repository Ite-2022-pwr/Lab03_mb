package pwr.ite.bedrylo.lab03.client.model;

import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;
import pwr.ite.bedrylo.lab03.client.RunApplication;
import pwr.ite.bedrylo.lab03.client.dto.RaportDto;
import pwr.ite.bedrylo.lab03.client.dto.RegistrationDto;

@Getter
@Setter
public class EmployeeRegistrationTableModel {

    private final SimpleStringProperty id = new SimpleStringProperty("");
    private final SimpleStringProperty status = new SimpleStringProperty("");
    private final SimpleStringProperty date = new SimpleStringProperty("");
    private final SimpleStringProperty registeredByName = new SimpleStringProperty("");
    private final SimpleStringProperty registeredByPesel = new SimpleStringProperty("");
    private RegistrationDto registrationDto;
    
    public EmployeeRegistrationTableModel(RegistrationDto registrationDto) {
        id.set(registrationDto.getId().toString());
        status.set(registrationDto.getRegistrationStatus().getName());
        date.set(registrationDto.getRegistrationDate().toString());
        registeredByName.set(registrationDto.getRegisteredBy().getName());
        registeredByPesel.set(registrationDto.getRegisteredBy().getPesel());
        this.registrationDto = registrationDto;
    }
    
    public String getId() {
        return id.get();
    }
    
    public String getStatus() {
        return status.get();
    }
    
    public String getDate() {
        return date.get();
    }
    
    public String getRegisteredByName() {
        return registeredByName.get();
    }
    
    public String getRegisteredByPesel() {
        return registeredByPesel.get();
    }
    
    public void setId(String id) {
        this.id.set(id);
    }
    
    public void setStatus(String status) {
        this.status.set(status);
    }
    
    public void setDate(String date) {
        this.date.set(date);
    }
    
    public void setRegisteredByName(String registeredByName) {
        this.registeredByName.set(registeredByName);
    }
    
    public void setRegisteredByPesel(String registeredByPesel) {
        this.registeredByPesel.set(registeredByPesel);
    }
    
    public RegistrationDto getRegistrationDto() {
        return this.registrationDto;
    }
    
    public void setRegistrationDto(RegistrationDto registrationDto) {
        this.registrationDto = registrationDto;
    }
}
