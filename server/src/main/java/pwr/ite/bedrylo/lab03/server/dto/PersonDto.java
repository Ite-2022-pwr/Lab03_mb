package pwr.ite.bedrylo.lab03.server.dto;

import lombok.Data;
import pwr.ite.bedrylo.lab03.server.model.enums.Role;

import java.util.UUID;

@Data
public class PersonDto {
    
    private UUID id;
    private String pesel;
    private String name;
    private Role role;

}
