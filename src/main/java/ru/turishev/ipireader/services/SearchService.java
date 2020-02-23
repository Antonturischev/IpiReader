package ru.turishev.ipireader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.turishev.ipireader.dto.TasksDto;
import ru.turishev.ipireader.forms.SearchForm;
import ru.turishev.ipireader.model.Task;
import ru.turishev.ipireader.repositories.TasksRepository;
import ru.turishev.ipireader.utils.Utils;

import java.util.List;


@Service
public class SearchService {
    @Autowired
    private TasksRepository tasksRepository;

    public Page<TasksDto> getTasksBySearchForm(SearchForm searchForm,  Pageable pageable) {
        String param = "по сопровождению";
        //List<Task> task = tasksRepository.findTasksByTest("petrov", "по", "Описание", "к заявке");
//        Page<Task> tasks = tasksRepository.findTasksByVarParam(pageable,
//                searchForm.getAuthor(),
//                searchForm.getTheme(),
//                searchForm.getDescription(),
//                searchForm.getComment());
        Page<Task> tasks = tasksRepository.findTasksByVarParam(param,pageable);
        Page<TasksDto> tasksDto = tasks.map(Utils::convertToTasksDto);
        return tasksDto;
    }
}
