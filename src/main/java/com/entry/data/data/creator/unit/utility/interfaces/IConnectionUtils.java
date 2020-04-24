package com.entry.data.data.creator.unit.utility.interfaces;

import com.entry.data.data.creator.unit.model.DBConnectionDetails;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;

public interface IConnectionUtils {

    public Connection getDBConnection(DBConnectionDetails dbConnDtls);
    public void cleanUp(FileReader fileReader, BufferedReader reader, Connection conn);

}
