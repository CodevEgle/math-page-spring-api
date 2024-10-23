package lt.ca.javau10.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "Klausimai")
public class Question {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String question;
	
	private List<String> options;
	private String answer;
	private int points;
	
	@ManyToOne
    @JoinColumn(name = "assessment_id", referencedColumnName = "id")
	@JsonIgnore
	private Assessment assessment;

	public Question () {}
	
	public Question(String question, List<String> options, String answer, int points) {
		this.question = question;
		this.options = options;
		this.answer = answer;
		this.points = points;
	}

	public Long getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	public List<String> getOptions() {
		return options;
	}

	public String getAnswer() {
		return answer;
	}

	public int getPoints() {
		return points;
	}

	public Assessment getAssessment() {
		return assessment;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}
	
}
