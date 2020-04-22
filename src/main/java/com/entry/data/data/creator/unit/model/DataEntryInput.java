package com.entry.data.data.creator.unit.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class DataEntryInput {

    private FileDetails fileDetails;
    private List<FieldDetails> fieldDtls;
    private DBConnectionDetails dbConnDtls;
    private String tableName;

}
