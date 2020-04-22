package com.entry.data.data.creator.unit.service.implementation;

import com.entry.data.data.creator.unit.model.EntryResponse;
import com.entry.data.data.creator.unit.model.SchemaCreationInput;
import com.entry.data.data.creator.unit.service.interfaces.ISchemaCreationApplicationService;
import com.entry.data.data.creator.unit.service.interfaces.ISchemaCreationDomainService;
import com.entry.data.data.creator.unit.service.validators.interfaces.ISchemaCreationInputValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class SchemaCreationApplicationService implements ISchemaCreationApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchemaCreationApplicationService.class);

    @Inject
    private ISchemaCreationInputValidationService schemaCreationInputValidationService;

    @Inject
    private ISchemaCreationDomainService schemaCreationDomainService;

    @Override
    public EntryResponse createSchemaByQuery(String user, SchemaCreationInput input) {

        long startTime = System.currentTimeMillis();
        LOGGER.info("In SchemaCreationApplicationService with input : {}",input);
        schemaCreationInputValidationService.validate(input);
        EntryResponse response = schemaCreationDomainService.execute(user,input);
        long endTime = System.currentTimeMillis();
        LOGGER.info("Total time taken for execution in millis : {}", (endTime-startTime));
        return response;
    }
}
