package pwr.ite.bedrylo.lab03.server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "raport")
@Getter
@Setter
public class Raport extends BaseEntity{
    
    @OneToOne(optional = false)
    @JoinColumn(nullable = false, unique = true)
    private Registration registrationId;
    
    @Column(nullable = false, length = 2000)
    private String description;
    
}
