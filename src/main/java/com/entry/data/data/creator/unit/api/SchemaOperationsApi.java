package com.entry.data.data.creator.unit.api;

import com.entry.data.data.creator.unit.model.DatabaseCreationInput;
import com.entry.data.data.creator.unit.model.EntryResponse;
import com.entry.data.data.creator.unit.model.SchemaCreationInput;
import com.entry.data.data.creator.unit.service.interfaces.IDatabaseCreationApplicationService;
import com.entry.data.data.creator.unit.service.interfaces.ISchemaCreationApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("/dataCreation/v1")
public class SchemaOperationsApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchemaOperationsApi.class);

    @Inject
    private ISchemaCreationApplicationService schemaCreationApplicationService;

    @Inject
    private IDatabaseCreationApplicationService databaseCreationApplicationService;

    @PostMapping(value = "/schema")
    public EntryResponse createSchemaByQuery(@RequestParam("user") String user, @RequestBody SchemaCreationInput input){

        LOGGER.info("Call Reached to RestController : SchemaOperationsApi, createSchemaByQuery(), called by USER : "+user);
        return schemaCreationApplicationService.createSchemaByQuery(user,input);
    }

    @PostMapping(value = "/database")
    public EntryResponse createDatabaseWithQuery(@RequestParam("user") String user, @RequestBody DatabaseCreationInput input){

        LOGGER.info("Call Reached to RestController : SchemaOperationsApi, createDatabaseByQuery(), called by USER : "+user);
        return databaseCreationApplicationService.createDatabaseByQuery(user,input);
    }
}
