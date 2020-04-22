package com.entry.data.data.creator.unit.service.interfaces;

import com.entry.data.data.creator.unit.model.EntryResponse;
import com.entry.data.data.creator.unit.model.SchemaCreationInput;

public interface ISchemaCreationDomainService {

    public EntryResponse execute(String user, SchemaCreationInput input);
}
