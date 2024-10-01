package lt.ca.javau10.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "Ivertinimai")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "muser_id")
    private MUser muser;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
    
    private Integer score;

	public Long getId() {
		return id;
	}

	public MUser getMUser() {
		return muser;
	}

	public Topic getTopic() {
		return topic;
	}

	public Integer getScore() {
		return score;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMUser(MUser muser) {
		this.muser = muser;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

    
}
