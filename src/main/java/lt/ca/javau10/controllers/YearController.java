package lt.ca.javau10.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lt.ca.javau10.entities.Topic;
import lt.ca.javau10.entities.Year;
import lt.ca.javau10.services.YearTopicService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class YearController {

	private YearTopicService service;
	
	public YearController (YearTopicService service) {
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
	
	@PutMapping("/years/edit/{id}")
	public Year updateYear(@PathVariable Long id, @RequestBody Year year) {
		return service.updateYear(id, year);
	}
	
	@PostMapping("years/{yearId}/topics/add")
    public ResponseEntity<Year> addNewTopicToYear(@PathVariable Long yearId, @RequestBody Topic topic) {
        Year updatedYear = service.addNewTopicToYear(yearId, topic);
        return ResponseEntity.ok(updatedYear);
    }
	
	@PostMapping("years/{yearId}/topics/add/{topicId}")
    public ResponseEntity<Year> addExistingTopicToYear(@PathVariable Long yearId, @PathVariable Long topicId ) {
        Year updatedYear = service.addExistingTopicToYear(yearId, topicId);
        return ResponseEntity.ok(updatedYear);
    }
	@DeleteMapping("/years/{id}")
	public String removeYearById(@PathVariable Long id) {
        return service.removeYearById(id);
    }
	
	@GetMapping("years/{yearId}/topics")
	public List<Topic> getAllTopicsByYear(@PathVariable Long yearId){
		return service.getAllTopicsByYear(yearId);
	}
	
	@DeleteMapping("years/{yearId}/topics/{topicId}")
    public ResponseEntity<Year> removeTopicFromYear(@PathVariable Long yearId, @PathVariable Long topicId) {
        Year updatedYear = service.removeTopicFromYear(yearId, topicId);
        return ResponseEntity.ok(updatedYear);
    }

	
	
}
