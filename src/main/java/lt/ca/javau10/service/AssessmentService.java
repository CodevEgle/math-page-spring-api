package lt.ca.javau10.service;

import org.springframework.stereotype.Service;

import lt.ca.javau10.repositories.AssessmentRepository;
import lt.ca.javau10.repositories.ExerciseRepository;
import lt.ca.javau10.repositories.QuestionRepository;

@Service
public class AssessmentService {

	private AssessmentRepository assessmentRep;
	private ExerciseRepository exRep;
	private QuestionRepository qRep;
	
	public AssessmentService (AssessmentRepository assessmentRep, ExerciseRepository exRep, QuestionRepository qRep) {
		this.assessmentRep = assessmentRep;
		this.exRep = exRep;
		this.qRep = qRep;
	}

}
