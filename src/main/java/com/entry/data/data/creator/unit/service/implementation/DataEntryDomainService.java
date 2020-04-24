package com.entry.data.data.creator.unit.service.implementation;

import com.entry.data.data.creator.unit.datalayer.interfaces.IEntityInsertionRepository;
import com.entry.data.data.creator.unit.model.DataEntryInput;
import com.entry.data.data.creator.unit.model.EntryResponse;
import com.entry.data.data.creator.unit.model.FieldDetails;
import com.entry.data.data.creator.unit.service.interfaces.IFileDataHandlingDomainService;
import com.entry.data.data.creator.unit.utility.interfaces.IConnectionUtils;
import com.entry.data.data.creator.unit.utility.interfaces.IDateCreatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.*;
import java.sql.Connection;
import java.util.*;

public class DataEntryDomainService implements IFileDataHandlingDomainService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataEntryDomainService.class);

    @Inject
    private IEntityInsertionRepository entityInsertionRepository;

    @Inject
    private IDateCreatorUtils dateCreatorUtils;

    @Inject
    private IConnectionUtils connectionUtils;

    @Override
    public EntryResponse execute(String user, DataEntryInput input) throws IOException {

        LOGGER.info("DataEntryDomainService invoked");
        File file = new File(input.getFileDetails().getFileLocation()+input.getFileDetails().getFileName());
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        Connection conn = connectionUtils.getDBConnection(input.getDbConnDtls());

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
                    String date = dateCreatorUtils.getFormattedDate(line.substring(fldDtls.getStartIndex(),fldDtls.getLastIndex()).trim(),fldDtls);
                    dataMap.put(fldDtls.getFieldName(),date);
                }else{
                    dataMap.put(fldDtls.getFieldName(),line.substring(fldDtls.getStartIndex(),fldDtls.getLastIndex()).trim());
                }
            }

            queryQueue.offer(getInsertQuery(input.getTableName(),dataMap));
            line = reader.readLine();
        }

        response = entityInsertionRepository.performInsert(queryQueue,conn);
        connectionUtils.cleanUp(fileReader, reader, conn);
        LOGGER.info("DataEntryDomainService executed successfully");
        return response;
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
}
