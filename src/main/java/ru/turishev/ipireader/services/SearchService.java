package ru.turishev.ipireader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.turishev.ipireader.dto.TasksDto;
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
        Page<Task> tasks = tasksRepository.findTasksByVarParam(searchParameters.get(0).getSelectedValue().trim().toUpperCase(),
                                                               searchParameters.get(1).getSelectedValue().trim().toUpperCase(),
                                                               searchParameters.get(2).getSelectedValue().trim().toUpperCase(),
                                                               searchParameters.get(3).getSelectedValue().trim().toUpperCase(),
                                                               searchParameters.get(4).getSelectedValue().trim().toUpperCase(),
                                                               pageable);
        Page<TasksDto> tasksDto = tasks.map(Utils::convertToTasksDto);
        return tasksDto;
    }
}
