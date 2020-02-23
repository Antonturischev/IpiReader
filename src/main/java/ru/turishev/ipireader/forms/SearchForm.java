package ru.turishev.ipireader.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.turishev.ipireader.utils.SearchParameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchForm {
    private String author;
    private String theme;
    private String description;
    private String comment;

    public List<SearchParameter> toList() {
        List<SearchParameter> list = new ArrayList<>();
        list.add(new SearchParameter(1,"author","Автор",(author!=null)?author:"",(author!=null)?"disabled":""));
        list.add(new SearchParameter(2,"theme","Тема",(theme!=null)?theme:"",(theme!=null)?"disabled":""));
        list.add(new SearchParameter(3,"description","Описание",(description!=null)?description:"",(description!=null)?"disabled":""));
        list.add(new SearchParameter(4,"comment","Коммент",(comment!=null)?comment:"",(comment!=null)?"disabled":""));
        return list;
    }


}
