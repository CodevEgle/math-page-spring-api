package lt.ca.javau10.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.ca.javau10.entities.MUser;

@Repository
public interface MUserRepository extends JpaRepository<MUser, Long>{

	Optional<MUser> findByUsername(String username);
	
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);

}
