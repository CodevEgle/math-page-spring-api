package lt.ca.javau10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.ca.javau10.entities.Year;

@Repository
public interface YearRepository extends JpaRepository<Year, Long> {

}
