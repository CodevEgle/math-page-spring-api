package lt.ca.javau10.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "Potemes")
public class SubTopic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	@OneToMany(mappedBy = "subtopic")
	private List<Theory> theories;
	
	@OneToMany(mappedBy = "subtopic", cascade = CascadeType.ALL)
	private List<SubTopicExercise> subTopicExercises;
	
	@OneToOne(mappedBy = "subtopic", cascade = CascadeType.ALL) 
	private SubTopicTest subTopicTest;
	
	@ManyToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
	@JsonIgnore
    private Topic topic;

	public SubTopic () {}
	
	public SubTopic (String title) {
		this.title = title;
	}
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public List<Theory> getTheories() {
		return theories;
	}

	public List<SubTopicExercise> getSubTopicExercises() {
		return subTopicExercises;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTheories(List<Theory> theories) {
		this.theories = theories;
	}

	public void setSubTopicExercises(List<SubTopicExercise> subTopicExercises) {
		this.subTopicExercises = subTopicExercises;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
}
