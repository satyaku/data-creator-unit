package com.entry.data.data.creator.unit.datalayer.interfaces;

import com.entry.data.data.creator.unit.model.EntryResponse;

import java.sql.Connection;

public interface IDatabaseCreationRepository {

    public EntryResponse createDatabaseByQuery(String query, Connection connection);

}
