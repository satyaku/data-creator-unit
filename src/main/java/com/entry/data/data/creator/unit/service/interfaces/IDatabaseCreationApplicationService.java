package com.entry.data.data.creator.unit.service.interfaces;

import com.entry.data.data.creator.unit.model.DatabaseCreationInput;
import com.entry.data.data.creator.unit.model.EntryResponse;

public interface IDatabaseCreationApplicationService {

    public EntryResponse createDatabaseByQuery(String user, DatabaseCreationInput input);

}
