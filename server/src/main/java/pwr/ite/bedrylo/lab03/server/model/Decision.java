package pwr.ite.bedrylo.lab03.server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "decision")
@Getter
@Setter
public class Decision extends BaseEntity{
    
    @JoinColumn(nullable = false)
    @OneToOne()
    private Registration registration;
    
    @JoinColumn(nullable = false)
    @ManyToOne()
    private Person approvedBy;
    
    @Column(nullable = false, length = 2000)
    private String description;
    
}
