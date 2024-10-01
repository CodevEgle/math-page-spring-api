package lt.ca.javau10.controllers;

import java.util.List;

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

import lt.ca.javau10.entities.Topic;
import lt.ca.javau10.entities.Year;
import lt.ca.javau10.service.YearService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class YearTopicController {

	private YearService service;
	
	public YearTopicController (YearService service) {
		this.service = service;
	}
	
	@GetMapping("/years")
	public List<Year> getAllYears(){
		return service.getAllYears();
	}
	
	@GetMapping("years/{id}")
	public Year getYearById (@PathVariable Long id) {
		return service.getYearById(id);
	}
	
	@PostMapping("/years")
	public Year addNewYear (@RequestBody Year year) {
		return service.addNewYear(year);
	}
	
	@PostMapping("years/{yearId}/topics")
    public ResponseEntity<Year> addnewTopicToYear(@PathVariable Long yearId, @RequestBody Topic topic) {
        Year updatedYear = service.addTopicToYear(yearId, topic);
        return ResponseEntity.ok(updatedYear);
    }
	@DeleteMapping("{id}")
	public String removeYearById(@PathVariable Long id) {
        return service.removeYearById(id);
    }
	
	@GetMapping("/topics")
	public List<Topic> getAllTopics(){
		return service.getAllTopics();
	}
	
	@GetMapping("years/{yearId}/topics")
	public List<Topic> getAllTopicsByYear(@PathVariable Long yearId){
		return service.getAllTopicsByYear(yearId);
	}
	
	@DeleteMapping("years/{yearId}/topics/{topicId}")
    public ResponseEntity<Year> removeAndDeleteTopicFromYear(@PathVariable Long yearId, @PathVariable Long topicId) {
        Year updatedYear = service.removeTopicFromYearAndDelete(yearId, topicId);
        return ResponseEntity.ok(updatedYear);
    }
	
	@GetMapping("topics/{id}")
	public Topic getTopicById(@PathVariable Long topicId) {
		return service.getTopicById(topicId);
	}
}
