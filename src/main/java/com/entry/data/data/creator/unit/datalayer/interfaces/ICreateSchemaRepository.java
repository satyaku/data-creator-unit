package com.entry.data.data.creator.unit.datalayer.interfaces;

import com.entry.data.data.creator.unit.model.EntryResponse;

import java.sql.Connection;

public interface ICreateSchemaRepository {

    public EntryResponse createSchemaByQuery(String query, Connection conn);

}
