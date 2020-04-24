package com.entry.data.data.creator.unit.service.validators.implementation;

import com.entry.data.data.creator.unit.model.DatabaseCreationInput;
import com.entry.data.data.creator.unit.service.validators.interfaces.IDatabaseCreationInputValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseCreationInputValidationService implements IDatabaseCreationInputValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseCreationInputValidationService.class);

    @Override
    public void validate(DatabaseCreationInput input) {

        LOGGER.info("DatabaseCreationInputValidationService invoked");


        LOGGER.info("DatabaseCreationInputValidationService executed successfully");
    }
}
