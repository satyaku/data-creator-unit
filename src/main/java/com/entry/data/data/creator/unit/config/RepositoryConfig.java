package com.entry.data.data.creator.unit.config;

import com.entry.data.data.creator.unit.datalayer.implementation.CreateSchemaRepository;
import com.entry.data.data.creator.unit.datalayer.implementation.InsertEntryRepository;
import com.entry.data.data.creator.unit.datalayer.interfaces.ICreateSchemaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public ICreateSchemaRepository getCreateSchemaRepository(){
        return new CreateSchemaRepository();
    }

    @Bean
    public InsertEntryRepository getInsertEntryRepository(){
        return new InsertEntryRepository();
    }
}
