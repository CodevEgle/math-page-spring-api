package lt.ca.javau10.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lt.ca.javau10.entities.Assessment;
import lt.ca.javau10.entities.Theory;
import lt.ca.javau10.entities.Topic;
import lt.ca.javau10.entities.Year;
import lt.ca.javau10.repositories.TopicRepository;
import lt.ca.javau10.repositories.YearRepository;

@Service
public class YearTopicService {

	private YearRepository yRep;
	private TopicRepository topicRep;
	
	public YearTopicService (YearRepository yRep, TopicRepository topicRep) {
		this.yRep = yRep;
		this.topicRep = topicRep;
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

	
	public Year addNewTopicToYear(Long yearId, Topic newTopic) {
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
	 
	 public Year removeTopicFromYear(Long yearId, Long topicId) {
		 	Optional<Year> yearOptional = yRep.findById(yearId);
	        Optional<Topic> topicOptional = topicRep.findById(topicId);	
	        if (yearOptional.isPresent() && topicOptional.isPresent()) {
	            Year year = yearOptional.get();
	            Topic topic = topicOptional.get();

	            year.getTopics().remove(topic);
	            topic.getYears().remove(year);
	            yRep.save(year);
	            topicRep.save(topic);
	            return year;
	        }
	        throw new RuntimeException("Year or Topic not found with ids: " + yearId + ", " + topicId);
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
	                topicRep.save(topic); //jeigu topikas dar buvo kitiem metam priskirtas, tai jo visiškai neištrina
	            }

	            return year;
	        }

	        throw new RuntimeException("Year or Topic not found with ids: " + yearId + ", " + topicId);
	    }
	public Year addExistingTopicToYear(Long yearId, Long topicId) {
		Optional<Year> yearOptional = yRep.findById(yearId);
	    Optional<Topic> topicOptional = topicRep.findById(topicId);
	    if (yearOptional.isPresent() && topicOptional.isPresent()) {
            Year year = yearOptional.get();
            Topic topic = topicOptional.get();

            List<Topic> topics = year.getTopics();
            List<Year> years = topic.getYears();
            topics.add(topic);
            years.add(year);
            year.setTopics(topics);
            topic.setYears(years);
            yRep.save(year);
            topicRep.save(topic);
            return year;
        }
        throw new RuntimeException("Year or Topic not found with ids: " + yearId + ", " + topicId);
	}
	
	public String removeYearById(Long id) {
		Year year = yRep.findById(id).get();
		year.setTopics(new ArrayList<>());
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



//	public Topic addSubTopicToTopic(Long topicId, SubTopic subtopic) {
//		Optional<Topic> topicOptional = topicRep.findById(topicId);
//        if (topicOptional.isPresent()) {
//            Topic topic = topicOptional.get();
//            topic.getSubTopics().add(subtopic);
//            subtopic.setTopic(topic);
//            
//            subRep.save(subtopic);  
//            return topicRep.save(topic);
//        }
//        throw new RuntimeException("Year not found with id: " + topicId);
//	}

	public Year updateYear(Long id, Year newyear) {
		Year oldYear = getYearById(id);
		oldYear.setName(newyear.getName());
		oldYear.setDescription(newyear.getDescription());
		oldYear.setTopics(newyear.getTopics());
		return yRep.save(oldYear);
	}

	public Topic createNewTopic(Topic topic) {
		return topicRep.save(topic);
	}

	public String removeTopicById(Long id) {
		Topic topic = topicRep.findById(id).orElseThrow();
		topic.setTheories(new ArrayList<>());
		topic.setYears(new ArrayList<>());
		topicRep.delete(topic);
		return "succesfully removed";
	}

//	public Topic addExistingSubTopicToTopic(Long topicId, Long subtopicId) {
//		Optional<SubTopic> subtopicOptional = subRep.findById(subtopicId);
//	    Optional<Topic> topicOptional = topicRep.findById(topicId);
//	    if (subtopicOptional.isPresent() && topicOptional.isPresent()) {
//            Topic topic = topicOptional.get();
//            SubTopic subtopic = subtopicOptional.get();
//            
//            List<SubTopic> subtopics = topic.getSubTopics();
//            subtopics.add(subtopic);
//            topic.setSubTopics(subtopics);
//            
//            subtopic.setTopic(topic);
//            topicRep.save(topic);
//            subRep.save(subtopic);
//            return topic;
//        }
//        throw new RuntimeException("Topic or SubTopic not found with ids: " + topicId + ", " + subtopicId);
//	}

	public List<Theory> getAllTheoriesFromTopic(Long topicId) {
		Topic topic = topicRep.findAll().stream().filter(t -> (t.getId() == topicId)).findFirst().orElseThrow();
		return topic.getTheories();
	}

	public List<Assessment> getAllAssessmentsFromTopic(Long topicId) {
		Topic topic = topicRep.findById(topicId).orElseThrow();
		return topic.getAssessments();
	}


	

	

	
}
