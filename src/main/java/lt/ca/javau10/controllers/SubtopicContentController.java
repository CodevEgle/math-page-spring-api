package lt.ca.javau10.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lt.ca.javau10.entities.SubTopicExercise;
import lt.ca.javau10.entities.Theory;
import lt.ca.javau10.service.SubTopicContentService;

@RestController
@RequestMapping("api/subtopic/content")
@CrossOrigin
public class SubtopicContentController {

	private SubTopicContentService service;
	
	public SubtopicContentController (SubTopicContentService service) {
		this.service = service;
	}
	@GetMapping ("/theory")
	public List<Theory> getAllTheories(){
		return service.getAllTheories();
	}
	
	@GetMapping("/problems")
	public List<SubTopicExercise> gelAllExercises(){
		return service.getAllProblems();
	}
	
	@GetMapping("/theory/{subtopic}")
	public List<Theory> getTheoryBySubtopicName(@PathVariable String subtopic){
		return service.getTheoriesBySubtopicName(subtopic); 
	}
	
	@GetMapping("/theory/{subtopicId}")
	public List<Theory> getTheoryBySubtopic(@PathVariable Long subtopicId){
		return service.getTheoriesBySubtopicID(subtopicId); 
	}
	@GetMapping("/theory/{id}")
	public Theory getTheoryById(@PathVariable Long id){
		return service.getTheoryById(id); 
	}
	
	@PostMapping("/theory")
	public Theory createNewTheory(@RequestBody Theory theory) {
		return service.createNewTheory(theory);
	}
//	@PostMapping("/theory")
//	 public String /*ResponseEntity<String> */ createTheory(@RequestBody Map<String, Object> theoryMap) {
//        String title = (String) theoryMap.get("title");
//        String content = (String) theoryMap.get("content");
//        Long subtopicId = ((Number) theoryMap.get("subtopicId")).longValue();
//        Theory theory = new Theory();
//        theory.setTitle(title);
//        theory.setContent(content);
//
//        return service.addTheoryToSubtopic(theory, subtopicId);
//    }
	
	@PostMapping("/{subtopicId}/theory")
	public Theory createTheoryAndAddToSubTopic(@PathVariable Long subtopicId, @RequestBody Theory theory) {
		return service.createAndAddNewTheoryToSubTopic(subtopicId, theory);
	}
}
