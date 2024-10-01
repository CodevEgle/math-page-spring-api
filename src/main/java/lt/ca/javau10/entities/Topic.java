package lt.ca.javau10.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name="Temos")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<SubTopic> subTopics;
    
    @OneToMany(mappedBy = "topic")
    private List<Assessment> assessments;
    
    @ManyToMany(mappedBy = "topics", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Year> years = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public List<SubTopic> getSubTopics() {
		return subTopics;
	}

	public List<Assessment> getAssessments() {
		return assessments;
	}

	public List<Year> getYears() {
		return years;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSubTopics(List<SubTopic> subTopics) {
		this.subTopics = subTopics;
	}

	public void setAssessments(List<Assessment> assessments) {
		this.assessments = assessments;
	}

	public void setYears(List<Year> years) {
		this.years = years;
	}

}
