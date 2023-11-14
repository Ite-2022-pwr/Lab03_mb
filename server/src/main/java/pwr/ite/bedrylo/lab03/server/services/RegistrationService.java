package pwr.ite.bedrylo.lab03.server.services;

import org.springframework.stereotype.Service;
import pwr.ite.bedrylo.lab03.server.dto.RegistrationDto;
import pwr.ite.bedrylo.lab03.server.model.Person;
import pwr.ite.bedrylo.lab03.server.model.Registration;
import pwr.ite.bedrylo.lab03.server.model.enums.Status;
import pwr.ite.bedrylo.lab03.server.repository.PersonRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class RegistrationService {

    private final PersonService personService;
    private final PersonRepository personRepository;

    public RegistrationService(PersonService personService, PersonRepository personRepository) {
        this.personService = personService;
        this.personRepository = personRepository;
    }

    public RegistrationDto createDto(Registration registration) {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setId(registration.getId());
        registrationDto.setRegisteredBy(personService.createDto(registration.getRegisteredBy()));
        registrationDto.setRegistrationDate(registration.getDate());
        registrationDto.setRegistrationStatus(registration.getStatus());
        return registrationDto;
    }
    
    public Set<RegistrationDto> createDtoSet(Set<Registration> registrationSet){
        Set<RegistrationDto> registrationDtos = new HashSet<>();
        registrationDtos.addAll(registrationSet.stream().map(this::createDto).collect(Collectors.toSet()));
        return registrationDtos;
    }
    
    public Registration createRegistration(RegistrationDto registrationDto){
        Registration registration = new Registration();
        Person person = personRepository.findById(registrationDto.getRegisteredBy().getId()).orElse(null);
        registration.setRegisteredBy(person);
        LocalDateTime localDateTime = LocalDateTime.now();
        registration.setDate(localDateTime);
        registration.setStatus(Status.CREATED);
        return registration;
    }

}
