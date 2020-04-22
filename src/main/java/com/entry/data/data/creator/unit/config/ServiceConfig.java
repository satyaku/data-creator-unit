package com.entry.data.data.creator.unit.config;

import com.entry.data.data.creator.unit.service.implementation.DataEntryApplicationService;
import com.entry.data.data.creator.unit.service.implementation.DataEntryDomainService;
import com.entry.data.data.creator.unit.service.implementation.SchemaCreationApplicationService;
import com.entry.data.data.creator.unit.service.implementation.SchemaCreationDomainService;
import com.entry.data.data.creator.unit.service.interfaces.ISchemaCreationDomainService;
import com.entry.data.data.creator.unit.service.validators.implementation.SchemaCreationInputValidationService;
import com.entry.data.data.creator.unit.service.interfaces.IDataEntryApplicationService;
import com.entry.data.data.creator.unit.service.interfaces.IFileDataHandlingDomainService;
import com.entry.data.data.creator.unit.service.interfaces.ISchemaCreationApplicationService;
import com.entry.data.data.creator.unit.service.validators.interfaces.ISchemaCreationInputValidationService;
import com.entry.data.data.creator.unit.service.validators.implementation.DataEntryDataEnrtyInputValidationService;
import com.entry.data.data.creator.unit.service.validators.interfaces.IDataEnrtyInputValidationService;
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
}
