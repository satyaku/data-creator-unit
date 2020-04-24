package com.entry.data.data.creator.unit.config;

import com.entry.data.data.creator.unit.datalayer.implementation.*;
import com.entry.data.data.creator.unit.datalayer.interfaces.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public ISchemaCreationRepository getSchemaCreationRepository(){
        return new SchemaCreationRepository();
    }

    @Bean
    public IEntityInsertionRepository getEntityInsertionRepository(){
        return new EntityInsertionRepository();
    }

    @Bean
    public IDatabaseCreationRepository getDatabaseCreationRepository(){
        return new DatabaseCreationRepository();
    }
}
