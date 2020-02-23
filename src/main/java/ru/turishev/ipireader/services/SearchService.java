package ru.turishev.ipireader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.turishev.ipireader.dto.TasksDto;
import ru.turishev.ipireader.forms.SearchForm;
import ru.turishev.ipireader.model.Task;
import ru.turishev.ipireader.repositories.TasksRepository;
import ru.turishev.ipireader.utils.SearchParameter;
import ru.turishev.ipireader.utils.Utils;

import java.util.List;


@Service
public class SearchService {
    @Autowired
    private TasksRepository tasksRepository;

    public Page<TasksDto> getTasksBySearchParameters(List<SearchParameter> searchParameters, Pageable pageable) {
        Page<Task> tasks = tasksRepository.findTasksByVarParam(searchParameters.get(0).getSelectedValue().toUpperCase(),
                                                               searchParameters.get(1).getSelectedValue().toUpperCase(),
                                                               searchParameters.get(2).getSelectedValue().toUpperCase(),
                                                               searchParameters.get(3).getSelectedValue().toUpperCase(),
                                                               pageable);
        Page<TasksDto> tasksDto = tasks.map(Utils::convertToTasksDto);
        return tasksDto;
    }
}
