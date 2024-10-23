package lt.ca.javau10.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table (name = "Metai")
public class Year {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String description;
	
	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable(
	        name = "year_topic",
	        joinColumns = @JoinColumn(name = "year_id"),
	        inverseJoinColumns = @JoinColumn(name = "topic_id")
	    )
	//@JsonManagedReference
	private List<Topic> topics = new ArrayList<>();

	public Year() {}
	
	public Year(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public Year(String name, String description, List<Topic> topics) {
		this.name = name;
		this.description = description;
		this.topics = topics;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	
	
}
