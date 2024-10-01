package lt.ca.javau10.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "uzdaviniai")
public class Exercise {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String problem;
	private String answer;
	private int points;
	
	@ManyToOne
    @JoinColumn(name = "assessment_id", referencedColumnName = "id")
	private Assessment assessment;

	public Long getId() {
		return id;
	}

	public String getProblem() {
		return problem;
	}

	public String getAnswer() {
		return answer;
	}

	public Assessment getAssessment() {
		return assessment;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	
	
	
}
