package pwr.ite.bedrylo.lab03.server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import pwr.ite.bedrylo.lab03.server.model.enums.Status;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "registration")
@Getter
@Setter
public class Registration extends BaseEntity {
    
    @JoinColumn(nullable = false)
    @ManyToOne()
    private Person registeredBy;
    
    @OneToMany(mappedBy = "registration")
    private Set<Tree> trees = new HashSet<>();
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Column(nullable = false)
    private LocalDateTime date;
    
    @OneToOne(optional = true, mappedBy = "registration")
    private Raport raport;
    
    @OneToOne(optional = true, mappedBy = "registration")
    private Decision decision;
}
