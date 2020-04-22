package com.entry.data.data.creator.unit.api;

import com.entry.data.data.creator.unit.model.DataEntryInput;
import com.entry.data.data.creator.unit.model.EntryResponse;
import com.entry.data.data.creator.unit.service.interfaces.IDataEntryApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.IOException;

@RestController
@RequestMapping("/dataCreation/v1")
public class DataEntryApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataEntryApi.class);

    @Inject
    private IDataEntryApplicationService dataEntryApplicationService;

    @PostMapping(value="/data")
    public EntryResponse createEntries(@RequestParam("user") String user,
                                     @RequestBody DataEntryInput input) throws IOException {
        LOGGER.info("Call Reached to RestController : DataEntryApi, createEntries(), called by USER : +user");
        return dataEntryApplicationService.createEntries(user, input);
    }
}
