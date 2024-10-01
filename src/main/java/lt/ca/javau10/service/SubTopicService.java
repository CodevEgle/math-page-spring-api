package lt.ca.javau10.service;

import org.springframework.stereotype.Service;

import lt.ca.javau10.entities.SubTopicQuestion;
import lt.ca.javau10.repositories.SubTopicQuestionRepository;
import lt.ca.javau10.repositories.SubTopicRepository;
import lt.ca.javau10.repositories.SubTopicTestRepository;

@Service
public class SubTopicService {
	
	private SubTopicRepository subTopicRep;
	private SubTopicTestRepository sbTestRep;
	private SubTopicQuestionRepository sbquestionRep;
	
	
	public SubTopicService (SubTopicRepository subTopicRep, SubTopicTestRepository sbTestRep, SubTopicQuestionRepository sbquestionRep) {
		this.subTopicRep = subTopicRep;
		this.sbTestRep = sbTestRep;
		this.sbquestionRep =sbquestionRep;
	}

}
