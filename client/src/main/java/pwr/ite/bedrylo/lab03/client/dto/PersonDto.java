package pwr.ite.bedrylo.lab03.client.dto;

import lombok.Data;
import pwr.ite.bedrylo.lab03.client.enums.Role;

import java.util.UUID;

@Data
public class PersonDto {
    
    private UUID id;
    private String pesel;
    private String name;
    private Role role;

}
