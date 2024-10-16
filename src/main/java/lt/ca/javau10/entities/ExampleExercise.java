package lt.ca.javau10.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name="Uzdaviniu-pavyzdziai")
public class ExampleExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String hint;
    private String solution;
    private String answer;

    @ManyToOne
    @JoinColumn(name = "theory_id", referencedColumnName = "id")
    private Theory theory;

	public Long getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	public String getHint() {
		return hint;
	}

	public String getSolution() {
		return solution;
	}

	public String getAnswer() {
		return answer;
	}

	public Theory getTheory() {
		return theory;
	}

	public void setTheory(Theory theory) {
		this.theory = theory;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
    
    
    
}
