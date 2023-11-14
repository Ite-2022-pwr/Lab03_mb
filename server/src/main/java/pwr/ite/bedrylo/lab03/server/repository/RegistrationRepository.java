package pwr.ite.bedrylo.lab03.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pwr.ite.bedrylo.lab03.server.model.Registration;
import pwr.ite.bedrylo.lab03.server.model.Tree;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, UUID> {

    @Query("SELECT r FROM Registration r INNER JOIN  r.registeredBy p WHERE p.id = :registeredBy")
    Collection<Registration> findAllByRegisteredBy(UUID registeredBy);
    
}
