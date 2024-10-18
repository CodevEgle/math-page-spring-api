package lt.ca.javau10.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lt.ca.javau10.entities.Assessment;
import lt.ca.javau10.entities.Question;
import lt.ca.javau10.entities.Topic;
import lt.ca.javau10.repositories.AssessmentRepository;
import lt.ca.javau10.repositories.ExerciseRepository;
import lt.ca.javau10.repositories.QuestionRepository;
import lt.ca.javau10.repositories.TopicRepository;

@Service
public class AssessmentService {

	private AssessmentRepository assessmentRep;
	private ExerciseRepository exRep;
	private QuestionRepository qRep;
	private TopicRepository topicRep;
	
	public AssessmentService (AssessmentRepository assessmentRep, ExerciseRepository exRep, QuestionRepository qRep,
			TopicRepository topicRep) {
		this.assessmentRep = assessmentRep;
		this.exRep = exRep;
		this.qRep = qRep;
		this.topicRep = topicRep;
	}

	public List<Assessment> getAllAssessments() {
		return assessmentRep.findAll();
	}

	public Assessment getAssessmentById(Long id) {
		return assessmentRep.findById(id).orElseThrow();
	}

	public Assessment createNewAssessment(Assessment assessment) {
		return assessmentRep.save(assessment);
	}

	public Assessment createAndAddNewAssessmentToTopic(Long topicId, Assessment assessment) {
		Topic topic = topicRep.findById(topicId).orElseThrow();
		List<Assessment> assessments = topic.getAssessments();
		assessments.add(assessment);
		topic.setAssessments(assessments);
		assessment.setTopic(topic);
		topicRep.save(topic);
		return assessmentRep.save(assessment);
	}

	public List<Question> getAllQuestionsFromAssessment(Long assessmentId) {
		Assessment assessment = assessmentRep.findById(assessmentId).orElseThrow();
		return assessment.getQuestions();
	}

	public Question createAndAddNewQuestionToAssessment(Long assessmentId, Question question) {
		Assessment assessment = assessmentRep.findById(assessmentId).orElseThrow();
		List<Question> questions = assessment.getQuestions();
		questions.add(question);
		assessment.setQuestions(questions);
		question.setAssessment(assessment);
		assessmentRep.save(assessment);
		return question;
	}

	public String deleteQuestion(Long questionId) {
		Question question = qRep.findById(questionId).orElseThrow();
		question.setAssessment(new Assessment());
		qRep.delete(question);
		return "succesfully removed";
	}
	
	
	
	

}
