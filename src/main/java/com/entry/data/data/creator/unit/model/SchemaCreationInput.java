package com.entry.data.data.creator.unit.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class SchemaCreationInput {

    @JsonProperty("query")
    private String query;

    @JsonProperty("dbConnDtls")
    private DBConnectionDetails dbConnDtls;

}
