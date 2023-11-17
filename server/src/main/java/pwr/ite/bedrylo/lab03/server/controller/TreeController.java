package pwr.ite.bedrylo.lab03.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.ite.bedrylo.lab03.server.dto.TreeDto;
import pwr.ite.bedrylo.lab03.server.model.Tree;
import pwr.ite.bedrylo.lab03.server.repository.TreeRepository;
import pwr.ite.bedrylo.lab03.server.services.TreeService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/tree")
public class TreeController {
    
    private final TreeRepository treeRepository;
    private final TreeService treeService;


    public TreeController(TreeRepository treeRepository, TreeService treeService) {
        this.treeRepository = treeRepository;
        this.treeService = treeService;
    }
    
    @PostMapping("/add")
    public ResponseEntity<TreeDto> addTree(@RequestBody TreeDto treeDto){
        Tree tree = treeService.createTree(treeDto);
        treeRepository.saveAndFlush(tree);
        return ResponseEntity.ok(treeService.createDto(tree));
    }
    
    @PostMapping("/add/bulk")
    public ResponseEntity<Set<TreeDto>> addBulkTree(@RequestBody Set<TreeDto> treeDtoSet){
        Set<Tree> treeSet= new HashSet<Tree>();
        for (TreeDto treeDto: treeDtoSet) {
            Tree tree = treeService.createTree(treeDto);
            treeSet.add(tree);
            treeRepository.saveAndFlush(tree);
        }
        return ResponseEntity.ok(treeService.createDtoSet(treeSet));
    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity<TreeDto> getTree(@PathVariable UUID id){
        return ResponseEntity.ok(treeService.createDto(treeRepository.findById(id).orElse(null)));
    }
    
    @GetMapping("/get/registration/{id}")
    public ResponseEntity<Set<TreeDto>> getTreesByRegistration(@PathVariable UUID id){
        return ResponseEntity.ok(treeService.createDtoSet(new HashSet<>(treeRepository.findAllByRegistration(id))));
    }
    
    @GetMapping("/get/all")
    public ResponseEntity<Iterable<TreeDto>> getAllTrees(){
        return ResponseEntity.ok(treeService.createDtoSet(new HashSet<>(treeRepository.findAll())));
    }
}
