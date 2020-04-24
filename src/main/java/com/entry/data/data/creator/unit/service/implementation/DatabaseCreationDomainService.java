package com.entry.data.data.creator.unit.service.implementation;

import com.entry.data.data.creator.unit.datalayer.interfaces.IDatabaseCreationRepository;
import com.entry.data.data.creator.unit.model.DatabaseCreationInput;
import com.entry.data.data.creator.unit.model.EntryResponse;
import com.entry.data.data.creator.unit.service.interfaces.IDatabaseCreationDomainService;
import com.entry.data.data.creator.unit.utility.interfaces.IConnectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.sql.Connection;

public class DatabaseCreationDomainService implements IDatabaseCreationDomainService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseCreationDomainService.class);

    @Inject
    private IConnectionUtils connectionUtils;

    @Inject
    private IDatabaseCreationRepository databaseCreationRepository;

    @Override
    public EntryResponse execute(String user, DatabaseCreationInput input) {

        LOGGER.info("DatabaseCreationDomainService invoked");
        Connection connection = connectionUtils.getDBConnection(input.getDbConnDtls());

        EntryResponse response = databaseCreationRepository.createDatabaseByQuery(input.getQuery(), connection);
        connectionUtils.cleanUp(null, null,connection);
        LOGGER.info("DatabaseCreationDomainService executed successfully");
        return response;
    }
}
