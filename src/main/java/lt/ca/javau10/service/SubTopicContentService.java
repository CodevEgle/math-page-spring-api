package lt.ca.javau10.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lt.ca.javau10.entities.SubTopic;
import lt.ca.javau10.entities.SubTopicExercise;
import lt.ca.javau10.entities.Theory;
import lt.ca.javau10.repositories.SubTopicExerciseRepository;
import lt.ca.javau10.repositories.SubTopicRepository;
import lt.ca.javau10.repositories.TheoryRepository;

@Service
public class SubTopicContentService { //Theory + subtopic exercises + test
	
	private SubTopicExerciseRepository subExRep;
	private TheoryRepository theoryRep;
	private SubTopicRepository subRep;
	
	public SubTopicContentService (SubTopicExerciseRepository subExRep, TheoryRepository theoryRep, SubTopicRepository subRep) {
		this.subExRep = subExRep;
		this.theoryRep = theoryRep;
		this.subRep = subRep;
	}

	public List<Theory> getAllTheories() {
		return theoryRep.findAll();
	}

	public List<SubTopicExercise> getAllProblems() {
		return subExRep.findAll();
	}

	public List<Theory> getTheoriesBySubtopicName(String title) {
		List<Theory> theories = theoryRep.findAll();
		return theories.stream()
				.filter(th -> th.getSubtopic().getTitle().equals(title))
				.toList();
	}
	
	public List<Theory> getTheoriesBySubtopicID(Long subtopicId) {
		SubTopic subtopic = subRep.findById(subtopicId).orElseThrow();
		return subtopic.getTheories();
	}
	public Theory getTheoryById(Long id){
		return theoryRep.findById(id).get();
	}


	public Theory createNewTheory(Theory theory) {
		return theoryRep.save(theory);
	}

	public Theory createAndAddNewTheoryToSubTopic(Long subtopicId, Theory theory) {
		SubTopic subtopic = subRep.findById(subtopicId).orElseThrow();
		List<Theory> theories = subtopic.getTheories();
		theories.add(theory);
		subtopic.setTheories(theories);
		theory.setSubtopic(subtopic);
		subRep.save(subtopic);
		return theoryRep.save(theory);
	}

	
	
	
	
}
