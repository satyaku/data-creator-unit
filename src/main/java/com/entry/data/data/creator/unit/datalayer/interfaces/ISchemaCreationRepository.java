package com.entry.data.data.creator.unit.datalayer.interfaces;

import com.entry.data.data.creator.unit.model.EntryResponse;

import java.sql.Connection;

public interface ISchemaCreationRepository {

    public EntryResponse createSchemaByQuery(String query, Connection conn);

}
