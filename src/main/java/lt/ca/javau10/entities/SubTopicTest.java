package lt.ca.javau10.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "testas")
public class SubTopicTest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	@OneToMany(mappedBy="subtopicTest", cascade = CascadeType.ALL)
	private List<SubTopicQuestion> questions;
	
	@OneToOne
    @JoinColumn(name = "subtopic_id")
	@JsonIgnore
    private SubTopic subtopic;
	
	private int points;

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public List<SubTopicQuestion> getQuestions() {
		return questions;
	}

	public int getPoints() {
		return points;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setQuestions(List<SubTopicQuestion> questions) {
		this.questions = questions;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	
}
