package pwr.ite.bedrylo.lab03.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pwr.ite.bedrylo.lab03.server.model.Tree;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TreeRepository extends JpaRepository<Tree, UUID> {
    
    @Query("SELECT t FROM Tree t INNER JOIN  t.registration r WHERE r.id = :registrationId")
    Collection<Tree> findAllByRegistration(UUID registrationId);
    
}
