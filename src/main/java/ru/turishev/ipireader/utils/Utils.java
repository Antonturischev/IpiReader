package ru.turishev.ipireader.utils;

import ru.turishev.ipireader.dto.TasksDto;
import ru.turishev.ipireader.forms.SearchForm;
import ru.turishev.ipireader.model.Task;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

public class Utils {

	public static String convertTimestampToString(Timestamp time) {
		Date date = new Date(time.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy HH:mm");
		return sdf.format(date);
	}

	public static TasksDto convertToTasksDto(Task task) {
		return TasksDto.from(task);
	}

	public static String getUrlbySearchForm(SearchForm searchForm) {
		StringJoiner sj = new StringJoiner("&","?","");
		if(searchForm.getAuthor()!=null) sj.add("author="+searchForm.getAuthor());
		if(searchForm.getTheme()!=null) sj.add("theme="+searchForm.getTheme());
		if(searchForm.getDescription()!=null) sj.add("description="+searchForm.getDescription());
		if(searchForm.getComment()!=null) sj.add("comment="+searchForm.getComment());
		if(searchForm.getResponsible()!=null) sj.add("responsible="+searchForm.getResponsible());
		return sj.toString();
	}
}
