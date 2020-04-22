package com.entry.data.data.creator.unit.service.interfaces;

import com.entry.data.data.creator.unit.model.DataEntryInput;
import com.entry.data.data.creator.unit.model.EntryResponse;

import java.io.IOException;

public interface IDataEntryApplicationService {

    public EntryResponse createEntries(String user, DataEntryInput input) throws IOException;

}
