package com.entry.data.data.creator.unit.config;

import com.entry.data.data.creator.unit.model.EntryResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ModelConfig {

    @Bean
    @Scope("prototype")
    public EntryResponse getEntryResponse(){
        return new EntryResponse();
    }
}
