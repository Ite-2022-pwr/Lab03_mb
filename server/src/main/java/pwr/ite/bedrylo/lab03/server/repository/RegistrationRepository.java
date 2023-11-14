package pwr.ite.bedrylo.lab03.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.ite.bedrylo.lab03.server.model.Registration;

import java.util.UUID;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, UUID> {
}
