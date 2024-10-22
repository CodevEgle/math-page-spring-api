package lt.ca.javau10.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.ca.javau10.entities.Grade;
import lt.ca.javau10.models.GradeDto;
import lt.ca.javau10.services.MUserService;

@CrossOrigin
@RestController
@RequestMapping("/api/grades")
public class GradeController {
	
	private MUserService service;
	public GradeController (MUserService service) {
		this.service = service;
		}
	
	@GetMapping("{userId}")
	public List<Grade> getAllGradesFromUser(@PathVariable Long userId){
		return service.getAllGradesFromUser(userId);
	}

	@PostMapping("/add")
	public Grade addGradeToUser(/*@PathVariable Long userId,*/ @RequestBody GradeDto gradeDto) {
		return service.addGradeToUser(gradeDto).get();
	}
}
