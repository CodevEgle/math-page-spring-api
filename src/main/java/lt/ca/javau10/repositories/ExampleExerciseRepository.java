package lt.ca.javau10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.ca.javau10.entities.ExampleExercise;

@Repository
public interface ExampleExerciseRepository extends JpaRepository<ExampleExercise, Long> {

}
