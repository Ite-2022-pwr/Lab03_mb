package pwr.ite.bedrylo.lab03.server.services;

import org.springframework.stereotype.Service;
import pwr.ite.bedrylo.lab03.server.dto.DecisionDto;
import pwr.ite.bedrylo.lab03.server.dto.RaportDto;
import pwr.ite.bedrylo.lab03.server.model.Decision;
import pwr.ite.bedrylo.lab03.server.model.Person;
import pwr.ite.bedrylo.lab03.server.model.Raport;
import pwr.ite.bedrylo.lab03.server.model.Registration;
import pwr.ite.bedrylo.lab03.server.repository.DecisionRepository;
import pwr.ite.bedrylo.lab03.server.repository.PersonRepository;
import pwr.ite.bedrylo.lab03.server.repository.RegistrationRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DecisionService {
    
    private final DecisionRepository decisionRepository;
    private final RegistrationRepository regstrationRepository;
    private final PersonRepository personRepository;
    private final RegistrationService registrationService;
    private final PersonService personService;

    public DecisionService(DecisionRepository decisionRepository, 
                           RegistrationRepository regstrationRepository, 
                           PersonRepository personRepository, 
                           RegistrationService registrationService, 
                           PersonService personService) {
        this.decisionRepository = decisionRepository;
        this.regstrationRepository = regstrationRepository;
        this.personRepository = personRepository;
        this.registrationService = registrationService;
        this.personService = personService;
    }
    
    public DecisionDto createDto(Decision decision){
        DecisionDto decisionDto = new DecisionDto();
        decisionDto.setId(decision.getId());
        decisionDto.setDescription(decision.getDescription());
        Registration registration = regstrationRepository.findById(decision.getRegistration().getId()).orElse(null);
        Person approvedBy = personRepository.findById(decision.getApprovedBy().getId()).orElse(null);
        decisionDto.setApprovedBy(personService.createDto(approvedBy));
        decisionDto.setRegistration(registrationService.createDto(registration));
        return decisionDto;
    }
    
    public Set<DecisionDto> createDtoSet(Set<Decision> decisionSet){
        Set<DecisionDto> decisionDtos = decisionSet.stream().map(this::createDto).collect(Collectors.toSet());
        return decisionDtos;
    }
    
    public Decision createDecision(DecisionDto decisionDto){
        Decision decision = new Decision();
        decision.setDescription(decisionDto.getDescription());
        Registration registration = regstrationRepository.findById(decisionDto.getRegistration().getId()).orElse(null);
        Person approvedBy = personRepository.findById(decisionDto.getApprovedBy().getId()).orElse(null);
        decision.setApprovedBy(approvedBy);
        decision.setRegistration(registration);
        return decision;
    }

    public Decision updateDecision(Decision decision, DecisionDto decisionDto) {
        decision.setDescription(decisionDto.getDescription());
        return decision;
    }
}
