package com.entry.data.data.creator.unit.config;

import com.entry.data.data.creator.unit.service.implementation.*;
import com.entry.data.data.creator.unit.service.interfaces.*;
import com.entry.data.data.creator.unit.service.validators.implementation.*;
import com.entry.data.data.creator.unit.service.validators.interfaces.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Named;

@Configuration
public class ServiceConfig {

    @Bean
    public IDataEntryApplicationService getDataEntryApplicationService(){
        return new DataEntryApplicationService();
    }

    @Bean
    @Named("InputValidationService")
    public IDataEnrtyInputValidationService getvalidationService(){
        return new DataEntryDataEnrtyInputValidationService();
    }

    @Bean
    public IFileDataHandlingDomainService getFileDataHandlingDomainService(){
        return new DataEntryDomainService();
    }

    @Bean
    public ISchemaCreationApplicationService getSchemaCreationApplicationService(){
        return new SchemaCreationApplicationService();
    }

    @Bean
    public ISchemaCreationInputValidationService getSchemaCreationInputValidationService(){
        return new SchemaCreationInputValidationService();
    }

    @Bean
    public ISchemaCreationDomainService getSchemaCreationDomainService(){
        return new SchemaCreationDomainService();
    }

    @Bean
    public IDatabaseCreationApplicationService getDatabaseCreationApplicationService(){
        return new DatabaseCreationApplicationService();
    }

    @Bean
    public IDatabaseCreationInputValidationService getDatabaseCreationInputValidationService(){
        return new DatabaseCreationInputValidationService();
    }

    @Bean
    public IDatabaseCreationDomainService getDatabaseCreationDomainService(){
        return new DatabaseCreationDomainService();
    }
}
