package pwr.ite.bedrylo.lab03.server.dto;

import lombok.Data;
import pwr.ite.bedrylo.lab03.server.model.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RegistrationDto {
    
    private UUID id;
    
    private PersonDto registeredBy;
    
    private TreeDto[] trees;
    
    private LocalDateTime registrationDate;
    
    private Status registrationStatus;
    
    private RaportDto raport;
    
    private DecisionDto decision;
}
