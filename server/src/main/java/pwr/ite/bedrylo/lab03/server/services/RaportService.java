package pwr.ite.bedrylo.lab03.server.services;

import pwr.ite.bedrylo.lab03.server.model.Registration;
import org.springframework.stereotype.Service;
import pwr.ite.bedrylo.lab03.server.dto.RaportDto;
import pwr.ite.bedrylo.lab03.server.model.Raport;
import pwr.ite.bedrylo.lab03.server.repository.RaportRepository;
import pwr.ite.bedrylo.lab03.server.repository.RegistrationRepository;

import java.util.Set;

@Service
public class RaportService {

    private final RaportRepository raportRepository;
    private final RegistrationService registrationService;
    private final PersonService personService;
    private final RegistrationRepository registrationRepository;
    

    public RaportService(RaportRepository raportRepository, RegistrationService registrationService, PersonService personService, RegistrationRepository registrationRepository) {
        this.raportRepository = raportRepository;
        this.registrationService = registrationService;
        this.personService = personService;
        this.registrationRepository = registrationRepository;
    }
    
    public RaportDto createDto(Raport raport){
        RaportDto raportDto = new RaportDto();
        raportDto.setId(raport.getId());
        raportDto.setDescription(raport.getDescription());
        raportDto.setRegistration(registrationService.createDto(raport.getRegistration()));
        return raportDto;
    }
    
    public Set<RaportDto> createDtoSet(Set<Raport> raportSet){
        Set<RaportDto> raportDtos = raportSet.stream().map(this::createDto).collect(java.util.stream.Collectors.toSet());
        return raportDtos;
    }
    
    public Raport createRaport(RaportDto raportDto){
        Raport raport = new Raport();
        raport.setDescription(raportDto.getDescription());
        Registration registration = registrationRepository.findById(raportDto.getRegistration().getId()).orElse(null);
        System.out.println(registration);
        raport.setRegistration(registration);
        return raport;
    }


    public Raport updateRaport(Raport raport, RaportDto raportDto) {
        raport.setDescription(raportDto.getDescription());
        return raport;
    }
}
