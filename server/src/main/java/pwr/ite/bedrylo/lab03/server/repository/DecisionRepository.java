package pwr.ite.bedrylo.lab03.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pwr.ite.bedrylo.lab03.server.model.Decision;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface DecisionRepository extends JpaRepository<Decision, UUID> {

    @Query("SELECT d FROM Decision d INNER JOIN d.registration r INNER JOIN r.registeredBy p WHERE p.id = :id")
    Collection<Decision> findAllByRegistration_RegisteredBy(UUID id);
    
    @Query("SELECT d FROM Decision d INNER JOIN d.registration r WHERE r.id = :id")
    Decision findByRegistration_Id(UUID id);
}
