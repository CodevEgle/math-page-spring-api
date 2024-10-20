package lt.ca.javau10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.ca.javau10.entities.Topic;

@Repository
public interface TopicRepository extends JpaRepository <Topic, Long> {

}
