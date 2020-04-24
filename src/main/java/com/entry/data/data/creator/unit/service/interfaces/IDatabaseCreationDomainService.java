package com.entry.data.data.creator.unit.service.interfaces;

import com.entry.data.data.creator.unit.model.DatabaseCreationInput;
import com.entry.data.data.creator.unit.model.EntryResponse;

public interface IDatabaseCreationDomainService {

    public EntryResponse execute(String user, DatabaseCreationInput input);

}
