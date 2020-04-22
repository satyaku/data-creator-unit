package com.entry.data.data.creator.unit.service.interfaces;

import com.entry.data.data.creator.unit.model.DataEntryInput;
import com.entry.data.data.creator.unit.model.EntryResponse;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IFileDataHandlingDomainService {

    public EntryResponse execute(String user, DataEntryInput input) throws IOException;

}
