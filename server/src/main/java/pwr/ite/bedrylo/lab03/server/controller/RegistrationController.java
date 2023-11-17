package pwr.ite.bedrylo.lab03.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.ite.bedrylo.lab03.server.dto.RegistrationDto;
import pwr.ite.bedrylo.lab03.server.model.Registration;
import pwr.ite.bedrylo.lab03.server.repository.RegistrationRepository;
import pwr.ite.bedrylo.lab03.server.services.RegistrationService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static pwr.ite.bedrylo.lab03.server.model.enums.Status.Status;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    
    private final RegistrationRepository registrationRepository;
    
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationRepository registrationRepository, RegistrationService registrationService) {
        this.registrationRepository = registrationRepository;
        this.registrationService = registrationService;
    }


    @GetMapping("/get/all")
    public ResponseEntity<Set<RegistrationDto>> getAllRegistration() {
        return ResponseEntity.ok(registrationService.createDtoSet(new HashSet<>(registrationRepository.findAll())));
    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity<RegistrationDto> getRegistration(@PathVariable UUID id) {
        return ResponseEntity.ok(registrationService.createDto(registrationRepository.findById(id).orElse(null)));
    }
    
    @GetMapping("/get/person/{id}")
    public ResponseEntity<Set<RegistrationDto>> getRegistrationByPerson(@PathVariable UUID id) {
        return ResponseEntity.ok(registrationService.createDtoSet(new HashSet<>(registrationRepository.findAllByRegisteredBy(id))));
    }
    
    @GetMapping("/get/all/status/{status}")
    public ResponseEntity<Set<RegistrationDto>> getRegistrationByStatus(@PathVariable String status) {
        return ResponseEntity.ok(registrationService.createDtoSet(new HashSet<>(registrationRepository.findAllByRegistrationStatus(Status(status)))));
    }
    
    @PostMapping("/add")
    public ResponseEntity<RegistrationDto> addRegistration(@RequestBody RegistrationDto registrationDto) {
        Registration registration = registrationService.createRegistration(registrationDto);
        registrationRepository.saveAndFlush(registration);
        return ResponseEntity.ok(registrationService.createDto(registration));
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<RegistrationDto> updateRegistration(@PathVariable UUID id, @RequestBody RegistrationDto registrationDto) {
        Registration registration = registrationRepository.findById(id).orElse(null);
        if (registration == null) {
            return ResponseEntity.notFound().build();
        }
        registration = registrationService.updateRegistrationStatus(registration, registrationDto);
        registrationRepository.saveAndFlush(registration);
        return ResponseEntity.ok(registrationService.createDto(registration));
    }
    
}
