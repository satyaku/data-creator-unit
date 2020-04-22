package com.entry.data.data.creator.unit.service.interfaces;

import com.entry.data.data.creator.unit.model.EntryResponse;
import com.entry.data.data.creator.unit.model.SchemaCreationInput;

public interface ISchemaCreationApplicationService {

    public EntryResponse createSchemaByQuery(String user, SchemaCreationInput input);
}
