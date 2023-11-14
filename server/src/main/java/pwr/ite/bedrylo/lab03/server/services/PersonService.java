package pwr.ite.bedrylo.lab03.server.services;

import org.springframework.stereotype.Service;
import pwr.ite.bedrylo.lab03.server.dto.PersonDto;
import pwr.ite.bedrylo.lab03.server.model.Person;
import pwr.ite.bedrylo.lab03.server.repository.PersonRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    
    public PersonDto createDto(Person person){
        PersonDto personDto = new PersonDto();
        personDto.setName(person.getName());
        personDto.setPesel(person.getPesel());
        personDto.setRole(person.getRole());
        return personDto;
    }
    
    public Set<PersonDto> createDtoSet(Set<Person> personSet){
        Set<PersonDto> personDtos = new HashSet<>();
        personDtos.addAll(personSet.stream().map(this::createDto).collect(Collectors.toSet()));
        return personDtos;
    }
    
    public Person createPerson(PersonDto personDto){
        Person person = new Person();
        person.setName(personDto.getName());
        person.setPesel(personDto.getPesel());
        person.setRole(personDto.getRole());
        return person;
    }
    
    
}
