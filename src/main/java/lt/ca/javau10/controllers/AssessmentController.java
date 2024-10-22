package lt.ca.javau10.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.ca.javau10.entities.Assessment;
import lt.ca.javau10.entities.Question;
import lt.ca.javau10.services.AssessmentService;

@RestController
@RequestMapping("api/assessments")
@CrossOrigin
public class AssessmentController {
	
	private AssessmentService service;
	
	public AssessmentController (AssessmentService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<Assessment> getAllAssessments(){
		return service.getAllAssessments();
	}
	
	@GetMapping("/{id}")
	public Assessment getAssessmentById(@PathVariable Long id){
		return service.getAssessmentById(id); 
	}
	
	@PostMapping 
	public Assessment createNewAssessment(@RequestBody Assessment assessment) {
		return service.createNewAssessment(assessment);
	}
	
	@PostMapping("/topic/{topicId}/add")
	public Assessment addNewAssessmentToTopic(@PathVariable Long topicId, @RequestBody Assessment assessment) {
		return service.createAndAddNewAssessmentToTopic(topicId, assessment);
	}
	
	@GetMapping("/{assessmentId}/questions")
	public List<Question> getAllQuestionsFromAssessment(@PathVariable Long assessmentId) {
		return service.getAllQuestionsFromAssessment(assessmentId);
	}
	
	@PostMapping ("/{assessmentId}/questions/add")
	public Question createAndAddNewQuestion(@PathVariable Long assessmentId, @RequestBody Question question) {
		return service.createAndAddNewQuestionToAssessment(assessmentId, question);
	}
	
	@DeleteMapping("/questions/{questionId}")
	public String deleteQuestion (@PathVariable Long questionId) {
		return service.deleteQuestion(questionId);
	}
	
	@PutMapping ("/questions/{questionId}/update")
	public Question updateQuestion(@PathVariable Long questionId, @RequestBody Question question) {
		return service.updateQuestionByQuestionId(questionId, question);
	}
	//update assessment
	//delete question,assessment
	//get exercises
	
}
