package ru.turishev.ipireader.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdvancedSearchController {
	@GetMapping("/search")
	public String getSearchResult(Model model) {
		return "search";	
	}
}
