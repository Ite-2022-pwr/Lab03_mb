package pwr.ite.bedrylo.lab03.client.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RaportDto {
    private UUID id;
    private String description;
    private RegistrationDto registration;
}
