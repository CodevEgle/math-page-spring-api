package lt.ca.javau10.entities.unused;
//
//import java.util.List;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "Potemes_klausimai")
//public class SubTopicQuestion {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	private String question;
//	private List<String> options;
//	private String answer;
//	
//	@ManyToOne
//	@JoinColumn(name = "subtopictest_id", referencedColumnName = "id")
//	private SubTopicTest subtopicTest;
//	
//	public Long getId() {
//		return id;
//	}
//	public String getQuestion() {
//		return question;
//	}
//	public List<String> getOptions() {
//		return options;
//	}
//	public String getAnswer() {
//		return answer;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public void setQuestion(String question) {
//		this.question = question;
//	}
//	public void setOptions(List<String> options) {
//		this.options = options;
//	}
//	public void setAnswer(String answer) {
//		this.answer = answer;
//	}
//	
//	
//}
