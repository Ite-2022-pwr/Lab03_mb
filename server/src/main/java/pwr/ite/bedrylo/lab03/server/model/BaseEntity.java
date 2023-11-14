package pwr.ite.bedrylo.lab03.server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;
}
