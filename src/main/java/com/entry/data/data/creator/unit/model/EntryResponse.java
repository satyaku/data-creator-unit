package com.entry.data.data.creator.unit.model;

import lombok.*;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class EntryResponse {

    private String status;
    private String reason;

}
