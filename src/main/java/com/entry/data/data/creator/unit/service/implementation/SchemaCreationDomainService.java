package com.entry.data.data.creator.unit.service.implementation;

import com.entry.data.data.creator.unit.datalayer.interfaces.ICreateSchemaRepository;
import com.entry.data.data.creator.unit.model.DBConnectionDetails;
import com.entry.data.data.creator.unit.model.EntryResponse;
import com.entry.data.data.creator.unit.model.SchemaCreationInput;
import com.entry.data.data.creator.unit.service.interfaces.ISchemaCreationDomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SchemaCreationDomainService implements ISchemaCreationDomainService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchemaCreationDomainService.class);

    @Inject
    private ICreateSchemaRepository createSchemaRepository;

    @Value("${spring.datasource.url}")
    private String dbConnUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    @Override
    public EntryResponse execute(String user, SchemaCreationInput input) {

        LOGGER.info("SchemaCreationDomainService invoked");
        Connection connection = getDBConnection(input.getDbConnDtls());

        EntryResponse response = createSchemaRepository.createSchemaByQuery(input.getQuery(), connection);
        cleanUp(connection);
        LOGGER.info("SchemaCreationDomainService executed Successfully");
        return response;
    }

    private Connection getDBConnection(DBConnectionDetails dbConnDtls) {
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

    private void cleanUp(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
