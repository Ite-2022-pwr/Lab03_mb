package pwr.ite.bedrylo.lab03.server.services;

import org.springframework.stereotype.Service;
import pwr.ite.bedrylo.lab03.server.dto.TreeDto;
import pwr.ite.bedrylo.lab03.server.model.Registration;
import pwr.ite.bedrylo.lab03.server.model.Tree;
import pwr.ite.bedrylo.lab03.server.repository.RegistrationRepository;
import pwr.ite.bedrylo.lab03.server.repository.TreeRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TreeService {
    
    private final TreeRepository treeRepository;
    private final RegistrationService registrationService;
    private final RegistrationRepository registrationRepository;


    public TreeService(TreeRepository treeRepository, RegistrationService registrationService, RegistrationRepository registrationRepository) {
        this.treeRepository = treeRepository;
        this.registrationService = registrationService;
        this.registrationRepository = registrationRepository;
    }
    
    public TreeDto createDto(Tree tree){
        TreeDto treeDto = new TreeDto();
        treeDto.setId(tree.getId());
        treeDto.setName(tree.getName());
        treeDto.setDiameter(tree.getDiameter());
        treeDto.setRegistration(registrationService.createDto(tree.getRegistration()));
        return treeDto;
    }
    
    public Set<TreeDto> createDtoSet(Set<Tree> treeSet){
        Set<TreeDto> treeDtos = new HashSet<TreeDto>();
        treeDtos.addAll(treeSet.stream().map(this::createDto).collect(Collectors.toSet()));
        return treeDtos;
    }
    
    
    
    public Tree createTree(TreeDto treeDto){
        Tree tree = new Tree();
        tree.setName(treeDto.getName());
        tree.setDiameter(treeDto.getDiameter());
        Registration registration = registrationRepository.findById(treeDto.getRegistration().getId()).orElse(null);
        tree.setRegistration(registration);
        return tree;
    }
    
}
