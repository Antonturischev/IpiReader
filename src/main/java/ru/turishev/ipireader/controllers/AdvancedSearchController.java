package ru.turishev.ipireader.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.turishev.ipireader.forms.SearchForm;
import ru.turishev.ipireader.utils.SearchParameter;

import java.util.List;
import java.util.Map;

@Controller
public class AdvancedSearchController {
	@GetMapping("/search")
	public String getSearchResult(@RequestParam(name = "number",required = false) String number,
								  @RequestParam(name = "author",required = false) String author,
								  @RequestParam(name = "theme",required = false) String theme,
								  @RequestParam(name = "description",required = false) String description,
								  @RequestParam(name = "comment",required = false) String comment,
								  Model model) {
		SearchForm searchForm = SearchForm
				.builder()
					.number(number)
					.author(author)
					.theme(theme)
					.description(description)
					.comment(comment)
				.build();
		List<SearchParameter> selectedParams = searchForm.toList();
		if(selectedParams.size()>0) {
			model.addAttribute("selectedParams", selectedParams);
		}
		return "search";	
	}
}
