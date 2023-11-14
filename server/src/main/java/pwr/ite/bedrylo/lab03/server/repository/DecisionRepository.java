package pwr.ite.bedrylo.lab03.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.ite.bedrylo.lab03.server.model.Decision;

import java.util.UUID;

@Repository
public interface DecisionRepository extends JpaRepository<Decision, UUID> {
}
