package com.entry.data.data.creator.unit.datalayer.implementation;

import com.entry.data.data.creator.unit.constants.Constants;
import com.entry.data.data.creator.unit.datalayer.interfaces.IDatabaseCreationRepository;
import com.entry.data.data.creator.unit.model.EntryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseCreationRepository implements IDatabaseCreationRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseCreationRepository.class);

    @Inject
    Provider<EntryResponse> entryResponseProvider;

    @Override
    public EntryResponse createDatabaseByQuery(String query, Connection connection) {

        LOGGER.info("DatabaseCreationRepository invoked");
        EntryResponse response = entryResponseProvider.get();
        boolean performed = false;

        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.executeUpdate();
            performed = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(performed){
                response.setStatus(Constants.SUCCESS_STRING);
                response.setReason("Database created with query : "+query);
            }else{
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                response.setStatus(Constants.FAILURE_STRING);
                response.setReason("Execution aborted due to some exception");
            }
        }
        LOGGER.info("DatabaseCreationRepository executed successfully");
        return response;
    }
}
