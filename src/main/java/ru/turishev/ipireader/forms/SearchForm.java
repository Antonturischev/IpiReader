package ru.turishev.ipireader.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.turishev.ipireader.utils.SearchParameter;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchForm {
    private String author;
    private String theme;
    private String description;
    private String comment;
    private String responsible;
    private String datecreatedd;
    private String datecreatedu;

    public List<SearchParameter> toList() {
        List<SearchParameter> list = new ArrayList<>();
        list.add(new SearchParameter(1,"author","Автор",(author!=null)?author:"",(author!=null)?"disabled":"", "text"));
        list.add(new SearchParameter(2,"theme","Тема",(theme!=null)?theme:"",(theme!=null)?"disabled":"", "text"));
        list.add(new SearchParameter(3,"description","Описание",(description!=null)?description:"",(description!=null)?"disabled":"", "text"));
        list.add(new SearchParameter(4,"comment","Комментарий",(comment!=null)?comment:"",(comment!=null)?"disabled":"", "text"));
        list.add(new SearchParameter(5,"responsible","Ответственный",(responsible!=null)?responsible:"",(responsible!=null)?"disabled":"", "text"));
        list.add(new SearchParameter(6,"datecreatedd","Дата создания больше",(datecreatedd!=null)?datecreatedd:"1990-01-01",(datecreatedd!=null)?"disabled":"", "date"));
        list.add(new SearchParameter(7,"datecreatedu","Дата создания меньше",(datecreatedu!=null)?datecreatedu:"2030-01-01",(datecreatedu!=null)?"disabled":"", "date"));
        return list;
    }


}
