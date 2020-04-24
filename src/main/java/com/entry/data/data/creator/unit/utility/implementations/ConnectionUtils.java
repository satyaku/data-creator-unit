package com.entry.data.data.creator.unit.utility.implementations;

import com.entry.data.data.creator.unit.model.DBConnectionDetails;
import com.entry.data.data.creator.unit.utility.interfaces.IConnectionUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils implements IConnectionUtils {

    @Value("${spring.datasource.url}")
    private String dbConnUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    @Override
    public Connection getDBConnection(DBConnectionDetails dbConnDtls) {
        Connection connection = null;
        if(dbConnDtls==null){
            try {
                Class.forName(driverClass);
                connection = DriverManager.getConnection(dbConnUrl,
                        dbUser, dbPassword);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }else{
            try {
                Class.forName(dbConnDtls.getDriverClass());
                connection = DriverManager.getConnection(dbConnDtls.getDbConnUrl(),
                        dbConnDtls.getDbUser(),
                        dbConnDtls.getDbPassword());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

    @Override
    public void cleanUp(FileReader fileReader, BufferedReader reader, Connection conn) {

        try {
            if(fileReader!=null)
                fileReader.close();
            if(reader!=null)
                reader.close();
            if(conn!=null)
                conn.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }
}
