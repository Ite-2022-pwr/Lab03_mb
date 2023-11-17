package pwr.ite.bedrylo.lab03.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pwr.ite.bedrylo.lab03.server.model.Raport;

import java.util.UUID;

@Repository
public interface RaportRepository extends JpaRepository<Raport, UUID> {
    
    @Query("SELECT r FROM Raport r INNER JOIN r.registration reg WHERE reg.id = :id")
    Raport findByRegistrationId(UUID id);
    
}
