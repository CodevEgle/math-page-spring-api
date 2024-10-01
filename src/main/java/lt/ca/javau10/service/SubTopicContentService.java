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
public class SubTopicContentService { //Theory + subtopic exercises
	
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

	public List<Theory> getTheoryBySubtopic(String title) {
		List<Theory> theories = theoryRep.findAll();
		return theories.stream()
				.filter(th -> th.getSubtopic().getTitle().equals(title))
				.toList();
	}
	
	public Theory getTheoryById(Long id){
		return theoryRep.findById(id).get();
	}

	public String addTheoryToSubtopic(Theory theory, Long subtopicId) {
        Optional<SubTopic> subtopicOptional = subRep.findById(subtopicId);
        
        if (!subtopicOptional.isPresent()) {
            return "Potemė, kurios numeris " + subtopicId + " neegzistuoja. Prašome pirma sukurti potemę, ir tuomet jai priskirti teoriją.";
        }
        SubTopic subtopic = subtopicOptional.get();
        theory.setSubtopic(subtopic);
        theoryRep.save(theory);

        return "Theory created with ID: " + theory.getId();
    }
	
	
	
}
