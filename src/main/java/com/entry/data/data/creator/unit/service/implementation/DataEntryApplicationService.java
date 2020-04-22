package com.entry.data.data.creator.unit.service.implementation;

import com.entry.data.data.creator.unit.model.DataEntryInput;
import com.entry.data.data.creator.unit.model.EntryResponse;
import com.entry.data.data.creator.unit.service.interfaces.IDataEntryApplicationService;
import com.entry.data.data.creator.unit.service.interfaces.IFileDataHandlingDomainService;
import com.entry.data.data.creator.unit.service.validators.interfaces.IDataEnrtyInputValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;

public class DataEntryApplicationService implements IDataEntryApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataEntryApplicationService.class);

    @Inject
    private IDataEnrtyInputValidationService inputValidationService;

    @Inject
    private IFileDataHandlingDomainService fileDataHandlingDomainService;

    @Override
    public EntryResponse createEntries(String user, DataEntryInput input) throws IOException {

        long startTime = System.currentTimeMillis();
        LOGGER.info("DataEntryApplicationService invoked with input : {}",input);
        inputValidationService.validate(input);
        EntryResponse response =  fileDataHandlingDomainService.execute(user, input);
        long endTime = System.currentTimeMillis();
        LOGGER.info("Total time taken for execution in millis : {}", (endTime-startTime));

        return response;
    }
}
