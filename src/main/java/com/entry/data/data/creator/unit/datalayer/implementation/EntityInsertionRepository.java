package com.entry.data.data.creator.unit.datalayer.implementation;

import com.entry.data.data.creator.unit.datalayer.interfaces.IEntityInsertionRepository;
import com.entry.data.data.creator.unit.model.EntryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Queue;

public class EntityInsertionRepository implements IEntityInsertionRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityInsertionRepository.class);

    @Inject
    Provider<EntryResponse> entryResponseProvider;

    @Override
    public EntryResponse performInsert(Queue<String> queryQueue, Connection conn) {

        LOGGER.info("InsertEntryRepository invoked");
        EntryResponse response = entryResponseProvider.get();
        boolean performed = false;
        String query = "";
        Integer count = 0;

        while(queryQueue.size()>0){
            performed = false;
            query = queryQueue.poll();

            try {
                PreparedStatement pst = conn.prepareStatement(query);
                pst.executeUpdate();
                performed = true;
                count++;
                LOGGER.info("Query Executed Successfully : {}",query);
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
                    response.setReason("Insert query failed while Executing query : "+query);
                }
            }
            if(!performed)
                break;
        }

        if(performed){
            response.setStatus("SUCCESS");
            response.setReason("Insert queries Executed successfully, Count : "+count);
        }

        LOGGER.info("InsertEntryRepository executed successfully");
        return response;
    }
}
