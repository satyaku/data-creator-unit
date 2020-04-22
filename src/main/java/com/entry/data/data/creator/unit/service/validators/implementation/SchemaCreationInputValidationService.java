package com.entry.data.data.creator.unit.service.validators.implementation;

import com.entry.data.data.creator.unit.exception.BadRequestException;
import com.entry.data.data.creator.unit.model.SchemaCreationInput;
import com.entry.data.data.creator.unit.service.validators.interfaces.ISchemaCreationInputValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;

public class SchemaCreationInputValidationService implements ISchemaCreationInputValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchemaCreationInputValidationService.class);

    @Override
    public void validate(SchemaCreationInput input) {

        LOGGER.info("SchemaCreationInputValidationService invoked");

        if(StringUtils.isEmpty(input.getQuery()))
            throw new BadRequestException(BadRequestException.MISSING_MANDATORY_FIELD + "query");

        LOGGER.info("SchemaCreationInputValidationService executed successfully");
    }
}
