package ru.turishev.ipireader.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SearchParameter {
    private Integer index;
    private String parameter;
    private String value;
    private String selectedValue;
    private String isSelected;
    private String type;
}