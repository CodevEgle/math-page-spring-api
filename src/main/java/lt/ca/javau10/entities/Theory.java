package lt.ca.javau10.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Teorija")
public class Theory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
    private String title;
	
	@Column(length = 5000)
	private String content;
	
	@OneToMany(mappedBy = "theory", cascade = CascadeType.ALL)
	private List<ExampleExercise> exampleExercises = new ArrayList<>();
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
	
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
	
	public List<ExampleExercise> getExampleExercises() {
		return exampleExercises;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setExampleExercises(List<ExampleExercise> exampleExercises) {
		this.exampleExercises = exampleExercises;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
