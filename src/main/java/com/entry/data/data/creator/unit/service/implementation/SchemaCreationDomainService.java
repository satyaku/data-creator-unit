package com.entry.data.data.creator.unit.service.implementation;

import com.entry.data.data.creator.unit.datalayer.interfaces.ISchemaCreationRepository;
import com.entry.data.data.creator.unit.model.EntryResponse;
import com.entry.data.data.creator.unit.model.SchemaCreationInput;
import com.entry.data.data.creator.unit.service.interfaces.ISchemaCreationDomainService;
import com.entry.data.data.creator.unit.utility.interfaces.IConnectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.sql.Connection;

public class SchemaCreationDomainService implements ISchemaCreationDomainService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchemaCreationDomainService.class);

    @Inject
    private ISchemaCreationRepository schemaCreationRepository;

    @Inject
    private IConnectionUtils connectionUtils;

    @Override
    public EntryResponse execute(String user, SchemaCreationInput input) {

        LOGGER.info("SchemaCreationDomainService invoked");
        Connection connection = connectionUtils.getDBConnection(input.getDbConnDtls());

        EntryResponse response = schemaCreationRepository.createSchemaByQuery(input.getQuery(), connection);
        connectionUtils.cleanUp(null, null,connection);
        LOGGER.info("SchemaCreationDomainService executed Successfully");
        return response;
    }
}
