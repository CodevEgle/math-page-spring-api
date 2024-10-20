package lt.ca.javau10.models;

import org.springframework.stereotype.Component;



@Component
public class GradeDto{
//	//private static final long serialVersionUID = -1L;
//	private Long id;
	private Long muserId;
    private Long topicId;
    private Double score;
    
    public GradeDto() {}
    
    public GradeDto(Long muserId, Long topicId, Double score) {
//    	this.id = id;
    	this.muserId = muserId;
    	this.topicId = topicId;
    	this.score = score;
    }

//	public Long getId() {
//		return id;
//	}
//
	public Long getMuserId() {
		return muserId;
	}

	public Long getTopicId() {
		return topicId;
	}

	public Double getScore() {
		return score;
	}

//	public void setId(Long id) {
//		this.id = id;
//	}
//
	public void setMuserId(Long muserId) {
		this.muserId = muserId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public void setScore(Double score) {
		this.score = score;
	}
    
    
}
