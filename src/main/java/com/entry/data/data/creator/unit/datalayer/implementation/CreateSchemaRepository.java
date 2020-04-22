package com.entry.data.data.creator.unit.datalayer.implementation;

import com.entry.data.data.creator.unit.datalayer.interfaces.ICreateSchemaRepository;
import com.entry.data.data.creator.unit.model.EntryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateSchemaRepository implements ICreateSchemaRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateSchemaRepository.class);

    @Inject
    Provider<EntryResponse> entryResponseProvider;

    @Override
    public EntryResponse createSchemaByQuery(String query, Connection conn) {
        LOGGER.info("CreateSchemaRepository invoked to execute Query : {}",query);
        EntryResponse response = entryResponseProvider.get();
        boolean performed = false;
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.executeUpdate();
            performed = true;
        } catch (SQLException e) {

            e.printStackTrace();
        }finally {
            if(!performed){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                response.setStatus("FAILURE");
                response.setReason("Schema creation Query execution failed due to SQLException.");
            }else{
                response.setStatus("SUCCESS");
                response.setReason("Schema creation Query executed successfully.");
            }
        }
        LOGGER.info("CreateSchemaRepository executed successfully");
        return response;
    }
}
