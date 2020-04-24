package com.entry.data.data.creator.unit.service.implementation;

import com.entry.data.data.creator.unit.model.DatabaseCreationInput;
import com.entry.data.data.creator.unit.model.EntryResponse;
import com.entry.data.data.creator.unit.service.interfaces.IDatabaseCreationApplicationService;
import com.entry.data.data.creator.unit.service.interfaces.IDatabaseCreationDomainService;
import com.entry.data.data.creator.unit.service.validators.interfaces.IDatabaseCreationInputValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class DatabaseCreationApplicationService implements IDatabaseCreationApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseCreationApplicationService.class);

    @Inject
    private IDatabaseCreationInputValidationService databaseCreationInputValidationService;

    @Inject
    private IDatabaseCreationDomainService databaseCreationDomainService;

    @Override
    public EntryResponse createDatabaseByQuery(String user, DatabaseCreationInput input) {

        long startTime = System.currentTimeMillis();
        LOGGER.info("In DatabaseCreationApplicationService with input : {}",input);
        databaseCreationInputValidationService.validate(input);
        EntryResponse response = databaseCreationDomainService.execute(user,input);
        long endTime = System.currentTimeMillis();
        LOGGER.info("Total time taken for execution in millis : {}", (endTime-startTime));
        return response;
    }
}
