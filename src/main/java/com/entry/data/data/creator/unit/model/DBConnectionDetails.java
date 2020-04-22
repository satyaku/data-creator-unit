package com.entry.data.data.creator.unit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class DBConnectionDetails {

    @JsonProperty("dbConnUrl")
    private String dbConnUrl;

    @JsonProperty("dbUser")
    private String dbUser;

    @JsonProperty("dbPassword")
    private String dbPassword;

    @JsonProperty("driverClass")
    private String driverClass;

}
