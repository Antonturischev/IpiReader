package ru.turishev.ipireader.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.turishev.ipireader.dto.TasksDto;
import ru.turishev.ipireader.model.DivisionsTopic;
import ru.turishev.ipireader.services.DivisionsTopicService;
import ru.turishev.ipireader.services.TasksService;

@Controller
public class TopicController {
    @Autowired
    TasksService tasksService;
    @Autowired 
    DivisionsTopicService divisionsTopicService;

    @GetMapping("/topic/{topic}")
    public String getTasksByTopic(@PathVariable DivisionsTopic topic, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        Page<TasksDto> page = tasksService.getTasksByTopic(topic, pageable);
        model.addAttribute("topic", topic.getTitle());
        model.addAttribute("page", page);
        model.addAttribute("url","/topic/"+topic.getId().toString());
        return "topic";
    }

    @GetMapping("/topics")
    public String getTopics(Model model) {
    	model.addAttribute("topics", divisionsTopicService.getRootDivisionTopic());
        return "topics";
    }
}
