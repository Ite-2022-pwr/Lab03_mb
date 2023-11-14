package pwr.ite.bedrylo.lab03.server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pwr.ite.bedrylo.lab03.server.model.enums.Role;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "person")
public class Person extends BaseEntity{
    
    @Column(unique = true, nullable = false, length = 11)
    private String pesel;
    
    @Column(nullable = false, length = 50)
    private String name;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @OneToMany(mappedBy = "registeredBy")
    private Set<Registration> registrations = new HashSet<>();
    
    @OneToMany(mappedBy = "approvedBy")
    private Set<Decision> decisions = new HashSet<>();
}
