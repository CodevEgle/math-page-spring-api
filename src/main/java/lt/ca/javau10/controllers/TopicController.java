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

import lt.ca.javau10.entities.Theory;
import lt.ca.javau10.entities.Topic;
import lt.ca.javau10.service.YearTopicService;

@RestController
@RequestMapping("/api/topics")
@CrossOrigin
public class TopicController {

	private YearTopicService service;
	
	public TopicController (YearTopicService service) {
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
	
	@GetMapping("{topicId}/theories")
	public List<Theory> getTheoriesFromTopic(@PathVariable Long topicId){
		return service.getAllTheoriesFromTopic(topicId);
	}

	//update topic:
	//
	
}
