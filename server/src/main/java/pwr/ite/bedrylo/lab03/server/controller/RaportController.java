package pwr.ite.bedrylo.lab03.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.ite.bedrylo.lab03.server.dto.RaportDto;
import pwr.ite.bedrylo.lab03.server.model.Raport;
import pwr.ite.bedrylo.lab03.server.repository.RaportRepository;
import pwr.ite.bedrylo.lab03.server.services.RaportService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/raport")
public class RaportController {
    
    private final RaportRepository raportRepository;
    private final RaportService raportService;

    public RaportController(RaportRepository raportRepository, RaportService raportService) {
        this.raportRepository = raportRepository;
        this.raportService = raportService;
    }

    @PostMapping("/add")
    public ResponseEntity<RaportDto> addRaport(@RequestBody RaportDto raportDto) {
        Raport raport = raportService.createRaport(raportDto);
        raportRepository.saveAndFlush(raport);
        return ResponseEntity.ok(raportService.createDto(raportRepository.findById(raport.getId()).orElse(null)));
    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity<RaportDto> getRaport(@PathVariable UUID id) {
        return ResponseEntity.ok(raportService.createDto(raportRepository.findById(id).orElse(null)));
    }
    
    @GetMapping("/get/registration/{id}")
    public ResponseEntity<RaportDto> getRaportByRegistrationId(@PathVariable UUID id) {
        if (raportRepository.findByRegistrationId(id) == null) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(raportService.createDto(raportRepository.findByRegistrationId(id)));
    }
    
    @GetMapping("/get/all")
    public ResponseEntity<Set<RaportDto>> getAllRaport() {
        return ResponseEntity.ok(raportService.createDtoSet(new HashSet<>(raportRepository.findAll())));
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<RaportDto> updateRaport(@PathVariable UUID id, @RequestBody RaportDto raportDto) {
        Raport raport = raportRepository.findById(id).orElse(null);
        if (raport == null) {
            return ResponseEntity.ok(null);
        }
        raport = raportService.updateRaport(raport, raportDto);
        raportRepository.saveAndFlush(raport);
        return ResponseEntity.ok(raportService.createDto(raport));
    }
    
}
