package pwr.ite.bedrylo.lab03.server.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.ite.bedrylo.lab03.server.dto.DecisionDto;
import pwr.ite.bedrylo.lab03.server.model.Decision;
import pwr.ite.bedrylo.lab03.server.repository.DecisionRepository;
import pwr.ite.bedrylo.lab03.server.services.DecisionService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/decision")
public class DecisionController {
    
    private final DecisionRepository decisionRepository;
    private final DecisionService decisionService;


    public DecisionController(DecisionRepository decisionRepository, DecisionService decisionService) {
        this.decisionRepository = decisionRepository;
        this.decisionService = decisionService;
    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity<DecisionDto> getDecision(@PathVariable UUID id) {
        return ResponseEntity.ok(decisionService.createDto(decisionRepository.findById(id).orElse(null)));
    }
    
    @GetMapping("/get/all")
    public ResponseEntity<Set<DecisionDto>> getAllDecisions() {
        return ResponseEntity.ok(decisionService.createDtoSet(new HashSet<>(decisionRepository.findAll())));
    }
    
    @PostMapping("/add")
    public ResponseEntity<DecisionDto> addDecision(@RequestBody DecisionDto decisionDto) {
        Decision decision = decisionService.createDecision(decisionDto);
        decisionRepository.saveAndFlush(decision);
        return ResponseEntity.ok(decisionService.createDto(decisionRepository.findById(decision.getId()).orElse(null)));
    }
    
    
    
}
