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

import java.sql.Timestamp;
import java.util.List;

@Service
public class SearchService {
    @Autowired
    private TasksRepository tasksRepository;

    public Page<TasksDto> getTasksBySearchParameters(List<SearchParameter> searchParameters, Pageable pageable) {
        String datecreatedd=(searchParameters.get(5).getSelectedValue().equals(""))?"1990-01-01 00:00:00":searchParameters.get(5).getSelectedValue()+" 00:00:00";
        String datecreateud=(searchParameters.get(6).getSelectedValue().equals(""))?"2030-01-01 00:00:00":searchParameters.get(6).getSelectedValue()+" 23:59:59";
        Page<Task> tasks = tasksRepository.findTasksByVarParam(searchParameters.get(0).getSelectedValue().trim().toUpperCase(),
                                                               searchParameters.get(1).getSelectedValue().trim().toUpperCase(),
                                                               searchParameters.get(2).getSelectedValue().trim().toUpperCase(),
                                                               searchParameters.get(3).getSelectedValue().trim().toUpperCase(),
                                                               searchParameters.get(4).getSelectedValue().trim().toUpperCase(),
                                                               Timestamp.valueOf(datecreatedd.trim()),
                                                               Timestamp.valueOf(datecreateud.trim()),
                pageable);
        Page<TasksDto> tasksDto = tasks.map(Utils::convertToTasksDto);
        return tasksDto;
    }
}
