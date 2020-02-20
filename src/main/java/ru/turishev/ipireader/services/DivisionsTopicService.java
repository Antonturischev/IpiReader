package ru.turishev.ipireader.services;

import java.util.LinkedList;

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
}
