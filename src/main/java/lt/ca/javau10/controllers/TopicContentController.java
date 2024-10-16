package lt.ca.javau10.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lt.ca.javau10.entities.ExampleExercise;
import lt.ca.javau10.entities.Theory;
import lt.ca.javau10.service.TopicContentService;

@RestController
@RequestMapping("api/theories")
@CrossOrigin
public class TopicContentController {

	private TopicContentService service;
	
	public TopicContentController (TopicContentService service) {
		this.service = service;
	}
	@GetMapping
	public List<Theory> getAllTheories(){
		return service.getAllTheories();
	}
	
	@GetMapping("/problems")
	public List<ExampleExercise> gelAllExercises(){
		return service.getAllProblems();
	}
	
	@GetMapping("/{topic}")
	public List<Theory> getTheoryByTopicName(@PathVariable String topic){
		return service.getTheoriesByTopicName(topic); 
	}
	
	@GetMapping("/{topicId}")
	public List<Theory> getTheoryByTopicID(@PathVariable Long topicId){
		return service.getTheoriesByTopicID(topicId); 
	}
	
	@GetMapping("/{id}")
	public Theory getTheoryById(@PathVariable Long id){
		return service.getTheoryById(id); 
	}
	
	@PostMapping("/add")
	public Theory createNewTheory(@RequestBody Theory theory) {
		return service.createNewTheory(theory);
	}
	
	@PostMapping("/topic/{topicId}/add")
	public Theory addNewTheoryToTopic(@PathVariable Long topicId, @RequestBody Theory theory) {
		return service.createAndAddNewTheoryToTopic(topicId, theory);
	}
	
	@DeleteMapping("/{theoryId}")
	public String deleteTheory(@PathVariable Long theoryId) {
		return service.deleteTheory(theoryId);
	}
	
	//update theory
}
