package lt.ca.javau10.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.ca.javau10.models.ERole;
import lt.ca.javau10.models.Role;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {
	Optional<Role> findByName(ERole name);

}
