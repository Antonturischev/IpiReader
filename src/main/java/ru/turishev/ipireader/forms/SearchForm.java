package ru.turishev.ipireader.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchForm {
    private String number;
    private String author;
    private String theme;
    private String description;
    private String comment;

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        if(number!=null) map.put("number",number);
        if(author!=null) map.put("author",author);
        if(theme!=null) map.put("theme",theme);
        if(description!=null) map.put("description",description);
        if(comment!=null) map.put("comment",comment);
        return map;
    }
}
