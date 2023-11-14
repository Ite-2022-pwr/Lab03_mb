package pwr.ite.bedrylo.lab03.server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tree")
public class Tree extends BaseEntity {
    
    @Column(nullable = false, length = 50)
    private String name;
    
    @Column(nullable = false)
    private float diameter;
    
    @JoinColumn(nullable = false)
    @ManyToOne()
    private Registration registration;
}
