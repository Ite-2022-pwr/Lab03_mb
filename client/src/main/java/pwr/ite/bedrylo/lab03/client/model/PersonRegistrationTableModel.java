package pwr.ite.bedrylo.lab03.client.model;

import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;
import pwr.ite.bedrylo.lab03.client.dto.RegistrationDto;

@Getter
@Setter
public class PersonRegistrationTableModel {
    
    private final SimpleStringProperty id = new SimpleStringProperty("");
    private final SimpleStringProperty status = new SimpleStringProperty("");
    private final SimpleStringProperty date = new SimpleStringProperty("");
    private RegistrationDto registrationDto;
    

    public PersonRegistrationTableModel(RegistrationDto registrationDto) {
        id.set(registrationDto.getId().toString());
        status.set(registrationDto.getRegistrationStatus().getName());
        date.set(registrationDto.getRegistrationDate().toString());
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
    
    public void setId(String id) {
        this.id.set(id);
    }
    
    public void setStatus(String status) {
        this.status.set(status);
    }
    
    public void setDate(String date) {
        this.date.set(date);
    }

    public RegistrationDto getRegistrationDto() {
        return this.registrationDto;
    }
    
    public void setRegistrationDto(RegistrationDto registrationDto) {
        this.registrationDto = registrationDto;
    }
}
