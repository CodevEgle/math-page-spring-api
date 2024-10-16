package lt.ca.javau10.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name ="atsiskaitymai")
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    
    @OneToMany(mappedBy = "assessment", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();
    
    @OneToMany(mappedBy = "assessment", cascade = CascadeType.ALL)
    private List<Exercise> exercises = new ArrayList<>();
           
    @ManyToOne	//čia tam atvejui, jeigu tema bus priskirta, pvz, ir 9 ir 8 kl, ir bus kitas atsiskaitymas be pilnųjų kv lygčių ar pan.
    @JoinColumn(name = "topic_id")   
    private Topic topic;

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public List<Exercise> getExercises() {
		return exercises;
	}
//
//	public int getPoints() {
//		return points;
//	}

	public Topic getTopic() {
		return topic;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}

//	public void setPoints(int points) {
//		this.points = points;
//	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
    
    
    
    
    
}
