package ru.turishev.ipireader.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.turishev.ipireader.dto.TasksDto;
import ru.turishev.ipireader.forms.SearchForm;
import ru.turishev.ipireader.services.SearchService;
import ru.turishev.ipireader.utils.SearchParameter;
import ru.turishev.ipireader.utils.Utils;

import java.util.List;

@Controller
public class AdvancedSearchController {
    @Autowired
    private SearchService searchService;

	@GetMapping("/search")
	public String getSearchResult(@RequestParam(name = "author",required = false) String author,
								  @RequestParam(name = "theme",required = false) String theme,
								  @RequestParam(name = "description",required = false) String description,
								  @RequestParam(name = "comment",required = false) String comment,
								  @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
								  Model model) {
		SearchForm searchForm = SearchForm
				.builder()
					.author(author)
					.theme(theme)
					.description(description)
					.comment(comment)
				.build();
		Page<TasksDto> tasks = searchService.getTasksBySearchParameters(searchForm.toList(),pageable);
		List<SearchParameter> selectedParams = searchForm.toList();
		String url = "/search"+Utils.getUrlbySearchForm(searchForm);
		model.addAttribute("selectedParams", selectedParams);
		if(tasks.getTotalPages()!=0){
			model.addAttribute("page", tasks);
		}
		model.addAttribute("url",url);
		return "search";	
	}
}
