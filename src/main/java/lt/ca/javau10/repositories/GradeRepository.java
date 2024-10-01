package lt.ca.javau10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.ca.javau10.entities.Grade;


@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

}
