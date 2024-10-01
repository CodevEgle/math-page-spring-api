package lt.ca.javau10.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.ca.javau10.entities.Theory;

@Repository
public interface TheoryRepository extends JpaRepository<Theory, Long> {

	
}
