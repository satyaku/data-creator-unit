package com.entry.data.data.creator.unit.service.implementation;

import com.entry.data.data.creator.unit.datalayer.interfaces.IInsertEntryRepository;
import com.entry.data.data.creator.unit.model.DBConnectionDetails;
import com.entry.data.data.creator.unit.model.DataEntryInput;
import com.entry.data.data.creator.unit.model.EntryResponse;
import com.entry.data.data.creator.unit.model.FieldDetails;
import com.entry.data.data.creator.unit.service.interfaces.IFileDataHandlingDomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class DataEntryDomainService implements IFileDataHandlingDomainService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataEntryDomainService.class);

    /*private static final String DATE_FIELD_STRING = "date_field";
    private static final String INPUT_DATE_FORMAT_STRING = "input_date_format";
    private static final String DESIRED_DATE_FORMAT_STRING = "desired_date_format";*/

    @Inject
    private IInsertEntryRepository insertEntryRepository;

    @Override
    public EntryResponse execute(String user, DataEntryInput input) throws IOException {

        LOGGER.info("DataEntryDomainService invoked");
        File file = new File(input.getFileDetails().getFileLocation()+input.getFileDetails().getFileName());
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        Connection conn = getDatabaseConnection(input.getDbConnDtls());

        Map<String,String> dataMap = new HashMap<>();
        Queue<String> queryQueue = new PriorityQueue<>();
        EntryResponse response = null;

        String line = reader.readLine();
        while(line != null && line.length()>0){
            dataMap.clear();
            int length = line.length();
            for(FieldDetails fldDtls : input.getFieldDtls()){
                if(fldDtls.getLastIndex()>line.length())
                    fldDtls.setLastIndex(line.length());
                if(fldDtls.getDateField()){
                    dataMap.put(fldDtls.getFieldName(),line.substring(fldDtls.getStartIndex(),fldDtls.getLastIndex()).trim());
                    /*dataMap.put(fldDtls.getFieldName(),DATE_FIELD_STRING);
                    dataMap.put(DATE_FIELD_STRING,line.substring(fldDtls.getStartIndex(),fldDtls.getLastIndex()));
                    dataMap.put(INPUT_DATE_FORMAT_STRING,fldDtls.getInputDateFormat());
                    dataMap.put(DESIRED_DATE_FORMAT_STRING,fldDtls.getDesiredDateFormat());*/
                }else{
                    dataMap.put(fldDtls.getFieldName(),line.substring(fldDtls.getStartIndex(),fldDtls.getLastIndex()).trim());
                }
            }
            queryQueue.offer(getInsertQuery(input.getTableName(),dataMap));
            line = reader.readLine();
        }

        response = insertEntryRepository.performInsert(queryQueue,conn);
        cleanup(fileReader, reader, conn);
        LOGGER.info("DataEntryDomainService executed successfully");
        return response;
    }

    private Connection getDatabaseConnection(DBConnectionDetails dbConnDtls) {
        Connection connection = null;
        try {
            Class.forName(dbConnDtls.getDriverClass());
            connection = DriverManager.getConnection(dbConnDtls.getDbConnUrl(),
                    dbConnDtls.getDbUser(),
                    dbConnDtls.getDbPassword());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    private static String getInsertQuery(String tableName, Map<String, String> dataMap) {
        String basicQueryString = "INSERT INTO "+tableName+" (";
        String valuesString = ") VALUES (";
        Set<Map.Entry<String,String>> dataEntrySet = dataMap.entrySet();
        for(Map.Entry<String,String> entry : dataEntrySet){
            basicQueryString = basicQueryString + entry.getKey() + ",";
            valuesString = String.format("%s'%s',", valuesString, entry.getValue());
        }
        return basicQueryString.substring(0,basicQueryString.length()-1)+valuesString.substring(0,valuesString.length()-1)+");";
    }


    private void cleanup(FileReader fileReader, BufferedReader reader, Connection conn) {

        try {
            fileReader.close();
            reader.close();
            conn.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
