package lt.ca.javau10.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lt.ca.javau10.entities.Topic;
import lt.ca.javau10.entities.Year;
import lt.ca.javau10.repositories.SubTopicRepository;
import lt.ca.javau10.repositories.TopicRepository;
import lt.ca.javau10.repositories.YearRepository;

@Service
public class YearService {

	private YearRepository yRep;
	private TopicRepository topicRep;
	private SubTopicRepository subRep;
	
	public YearService (YearRepository yRep, TopicRepository topicRep, SubTopicRepository subRep) {
		this.yRep = yRep;
		this.topicRep = topicRep;
		this.subRep = subRep;
	}

	public List<Year> getAllYears() {
		return yRep.findAll();
	}
	
	public Year getYearById(Long id) {
		return yRep.findById(id).get();
	}

	public Year addNewYear(Year year) {
		return yRep.save(year);
	}

	
	 public Year addTopicToYear(Long yearId, Topic newTopic) {
	        Optional<Year> yearOptional = yRep.findById(yearId);
	        if (yearOptional.isPresent()) {
	            Year year = yearOptional.get();
	            year.getTopics().add(newTopic);
	            newTopic.getYears().add(year);
	            
	            topicRep.save(newTopic);  
	            return yRep.save(year);
	        }
	        throw new RuntimeException("Year not found with id: " + yearId);
	    }
	 
	 public Year removeTopicFromYearAndDelete(Long yearId, Long topicId) {
	        Optional<Year> yearOptional = yRep.findById(yearId);
	        Optional<Topic> topicOptional = topicRep.findById(topicId);

	        if (yearOptional.isPresent() && topicOptional.isPresent()) {
	            Year year = yearOptional.get();
	            Topic topic = topicOptional.get();

	            year.getTopics().remove(topic);
	            topic.getYears().remove(year);
	            yRep.save(year);

	            if (topic.getYears().isEmpty()) {
	                topicRep.delete(topic);
	            } else {
	                topicRep.save(topic);
	            }

	            return year;
	        }

	        throw new RuntimeException("Year or Topic not found with ids: " + yearId + ", " + topicId);
	    }

	public String removeYearById(Long id) {
		Year year = yRep.findById(id).get();
		yRep.delete(year);
		return "succesfully removed";
	}

	public List<Topic> getAllTopics() {
		return topicRep.findAll();
	}
	
	public Topic getTopicById(Long topicId) {
		return topicRep.findAll().stream().filter(t -> t.getId() == topicId).findFirst().get();
	}
	public List<Topic> getAllTopicsByYear(Long yearId) {
		Year year = yRep.findAll().stream().filter(y -> (y.getId() == yearId)).findFirst().orElse(null);
		return year.getTopics();
	}
	
	
	
	
}
