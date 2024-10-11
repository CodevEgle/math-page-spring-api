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
import org.springframework.web.bind.annotation.RestController;

import lt.ca.javau10.entities.SubTopic;
import lt.ca.javau10.entities.Topic;
import lt.ca.javau10.entities.Year;
import lt.ca.javau10.service.YearService;

@RestController
@RequestMapping("/api/topics")
@CrossOrigin
public class TopicController {

	private YearService service;
	
	public TopicController (YearService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<Topic> getAllTopics(){
		return service.getAllTopics();
	}
	
	@GetMapping("/{id}")
	public Topic getTopicById(@PathVariable Long topicId) {
		return service.getTopicById(topicId);
	}
	
	@PostMapping("/create")
	public Topic createNewTopic(@RequestBody Topic topic) {
		return service.createNewTopic(topic);
	}
	
	@DeleteMapping("/{id}")
	public String removeTopicById(@PathVariable Long id) {
        return service.removeTopicById(id);
    }
	
	@GetMapping("{topicId}/subtopics")
	public List<SubTopic> getSubtopicsFromTopic(@PathVariable Long topicId){
		return service.getAllSubtopicsFromTopic(topicId);
	}
	//----------------------------------------------------------------------------
	@GetMapping("/subtopics")
	public List<SubTopic> getAllSuTopics() {
		return service.getAllSubtopics();
	}
		
	@GetMapping("/subtopics/{id}")
	public SubTopic getSubTopicById(@PathVariable Long subtopicId) {
		return service.getSubTopicById(subtopicId);
	}
	
	@DeleteMapping("/subtopics/{subtopicId}")
	public String deleteSubTopic(@PathVariable Long subtopicId) {
		return service.deleteSubtopic(subtopicId);
	}
	
	@PostMapping("{topicId}/subtopics")
	public ResponseEntity<Topic> addNewSubTopicToTopic(@PathVariable Long topicId, @RequestBody SubTopic subtopic) {
	Topic updatedTopic = service.addSubTopicToTopic(topicId, subtopic);
	    return ResponseEntity.ok(updatedTopic);
	}
	
	@PostMapping("/{topicId}/subtopics/add/{subtopicId}")
    public ResponseEntity<Topic> addExistingSubTopicToTopic(@PathVariable Long topicId, @PathVariable Long subtopicId ) {
        Topic updatedTopic = service.addExistingSubTopicToTopic(topicId, subtopicId);
        return ResponseEntity.ok(updatedTopic);
    }
	
}
