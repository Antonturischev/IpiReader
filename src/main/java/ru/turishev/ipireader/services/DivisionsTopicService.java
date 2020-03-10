package ru.turishev.ipireader.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.turishev.ipireader.model.DivisionsTopic;
import ru.turishev.ipireader.repositories.DivisionsTopicRepository;

@Service
public class DivisionsTopicService {
	@Autowired
	DivisionsTopicRepository divisionsTopicRepository;

    public static LinkedList<DivisionsTopic> getParentTopics(DivisionsTopic topic) {
    	LinkedList<DivisionsTopic> result= new LinkedList<DivisionsTopic>();
    	result.add(topic);
    	if(topic.getParent()!=null) {
    		result.addAll(0,getParentTopics(topic.getParent()));
    	}
		return result;
    }
    public static List<DivisionsTopic> getChildTopics(DivisionsTopic topic) {
    	List<DivisionsTopic> result = new ArrayList<>();
    	result.add(topic);
    	if(topic.getChildren()!=null) {
    		for(DivisionsTopic child : topic.getChildren()) {
				result.addAll(DivisionsTopicService.getChildTopics(child));
			}
		}
    	return result;
	}

	public static List<String> getChildTopicsAsList(DivisionsTopic topic) {
		List<DivisionsTopic> topics = DivisionsTopicService.getChildTopics(topic);
		List<String> topicList = topics.stream().map(x->x.getId().toString()).collect(Collectors.toList());
		return topicList;
	}

	public List<DivisionsTopic> getRootDivisionTopic() {
    	return divisionsTopicRepository.findAllByMpttLevel(0);
	}
}
