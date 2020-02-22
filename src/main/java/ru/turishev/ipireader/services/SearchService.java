package ru.turishev.ipireader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.turishev.ipireader.dto.TasksDto;
import ru.turishev.ipireader.forms.SearchForm;
import ru.turishev.ipireader.model.Task;
import ru.turishev.ipireader.repositories.TasksRepository;

@Service
public class SearchService {
    @Autowired
    TasksRepository tasksRepository;
    public Page<TasksDto> getTasksBySearchForm(SearchForm searchForm,  Pageable pageable) {
        String param = "author";
      //  Page<Task> tasks = tasksRepository.findTasksByVarParam(pageable, paramName, paramValue );
        return null;
    }
}
