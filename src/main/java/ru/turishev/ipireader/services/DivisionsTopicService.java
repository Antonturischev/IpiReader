package ru.turishev.ipireader.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import ru.turishev.ipireader.model.DivisionsTopic;

public class DivisionsTopicService {
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

	public static String getChildTopicsAsString(DivisionsTopic topic) {
		StringJoiner sj = new StringJoiner("','","'","'");
		List<DivisionsTopic> topics = DivisionsTopicService.getChildTopics(topic);
		List<String> topicList = topics.stream().map(x->x.getId().toString()).collect(Collectors.toList());
		topicList.forEach(x->sj.add(x));
		return sj.toString();
	}

	public static List<String> getChildTopicsAsList(DivisionsTopic topic) {
		List<DivisionsTopic> topics = DivisionsTopicService.getChildTopics(topic);
		List<String> topicList = topics.stream().map(x->x.getId().toString()).collect(Collectors.toList());
		return topicList;
	}
}
