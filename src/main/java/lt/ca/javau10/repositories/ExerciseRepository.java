package lt.ca.javau10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.ca.javau10.entities.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}
