package com.entry.data.data.creator.unit.service.validators.implementation;

import com.entry.data.data.creator.unit.exception.BadRequestException;
import com.entry.data.data.creator.unit.model.DBConnectionDetails;
import com.entry.data.data.creator.unit.model.DataEntryInput;
import com.entry.data.data.creator.unit.model.FileDetails;
import com.entry.data.data.creator.unit.service.validators.interfaces.IDataEnrtyInputValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataEntryDataEnrtyInputValidationService implements IDataEnrtyInputValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataEntryDataEnrtyInputValidationService.class);

    @Override
    public void validate(DataEntryInput input) {

        LOGGER.info("InputValidationService invoked");
        if(input.getTableName().isEmpty())
            throwBadRequestException("TableName");

        DBConnectionDetails dbConnDtls = input.getDbConnDtls();
        if(dbConnDtls!=null){
            if(dbConnDtls.getDbConnUrl().isEmpty())
                throwBadRequestException("Database Connection URL");
            if(dbConnDtls.getDbUser().isEmpty())
                throwBadRequestException("Database user");
            if(dbConnDtls.getDbPassword().isEmpty())
                throwBadRequestException("Database password");
            if(dbConnDtls.getDriverClass().isEmpty())
                throwBadRequestException("Driver class");
        }

        if(input.getFieldDtls().size() < 1)
            throwBadRequestException("Field details");

        FileDetails fileDtls = input.getFileDetails();
        if(fileDtls.getFileName().isEmpty())
            throwBadRequestException("File name");
        if(fileDtls.getFileLocation().isEmpty())
            throwBadRequestException("File location");

        LOGGER.info("InputValidationService executed successfully");
    }

    private void throwBadRequestException(String fieldName) {
        throw new BadRequestException(BadRequestException.MISSING_MANDATORY_FIELD + fieldName);
    }
}
