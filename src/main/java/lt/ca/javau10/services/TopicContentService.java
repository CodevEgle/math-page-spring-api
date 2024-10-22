package lt.ca.javau10.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lt.ca.javau10.entities.ExampleExercise;
import lt.ca.javau10.entities.Theory;
import lt.ca.javau10.entities.Topic;
import lt.ca.javau10.repositories.AssessmentRepository;
import lt.ca.javau10.repositories.ExampleExerciseRepository;
import lt.ca.javau10.repositories.TheoryRepository;
import lt.ca.javau10.repositories.TopicRepository;

@Service
public class TopicContentService {
	
	private ExampleExerciseRepository exRep;
	private TheoryRepository theoryRep;
	private TopicRepository topicRep;
	private AssessmentRepository aRep;
	
	public TopicContentService (ExampleExerciseRepository exRep, TheoryRepository theoryRep, TopicRepository topicRep,
			AssessmentRepository aRep) {
		this.exRep = exRep;
		this.theoryRep = theoryRep;
		this.topicRep = topicRep;
		this.aRep = aRep;
	}

	public List<Theory> getAllTheories() {
		return theoryRep.findAll();
	}

	public List<ExampleExercise> getAllProblems() {
		return exRep.findAll();
	}

	public List<Theory> getTheoriesByTopicName(String title) {
		List<Theory> theories = theoryRep.findAll();
		return theories.stream()
				.filter(th -> th.getTopic().getTitle().equals(title))
				.toList();
	}
	
	public List<Theory> getTheoriesByTopicID(Long topicId) {
		Topic topic = topicRep.findById(topicId).orElseThrow();
		return topic.getTheories();
	}
	public Theory getTheoryById(Long id){
		return theoryRep.findById(id).get();
	}


	public Theory createNewTheory(Theory theory) {
		return theoryRep.save(theory);
	}

	public Theory createAndAddNewTheoryToTopic(Long topicId, Theory theory) {
		Topic topic = topicRep.findById(topicId).orElseThrow();
		List<Theory> theories = topic.getTheories();
		theories.add(theory);
		topic.setTheories(theories);
		theory.setTopic(topic);
		topicRep.save(topic);
		return theoryRep.save(theory);
	}

	public String deleteTheory(Long theoryId) {
		Theory theory = theoryRep.findById(theoryId).orElseThrow();
		theory.setTopic(new Topic());
		theory.setExampleExercises(new ArrayList<>());
		theoryRep.delete(theory);
		return "succesfully removed";
	}

	public Theory updateTheory(Long theoryId, Theory theory) {
		Theory oldTheory = theoryRep.findById(theoryId).orElseThrow();
		oldTheory.setTitle(theory.getTitle());
		oldTheory.setContent(theory.getContent());
		oldTheory.setExampleExercises(theory.getExampleExercises());
		return theoryRep.save(oldTheory);
	}

	
	
	
	
}
