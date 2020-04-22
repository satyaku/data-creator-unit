package com.entry.data.data.creator.unit.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class FieldDetails {

    private String fieldName;
    private Boolean dateField;
    private String inputDateFormat;
    private String desiredDateFormat;
    private Integer startIndex;
    private Integer lastIndex;

}
