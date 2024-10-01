package lt.ca.javau10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.ca.javau10.entities.SubTopic;

@Repository
public interface SubTopicRepository extends JpaRepository <SubTopic, Long>{

}
