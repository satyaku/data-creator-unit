package com.entry.data.data.creator.unit.config;

import com.entry.data.data.creator.unit.utility.implementations.ConnectionUtils;
import com.entry.data.data.creator.unit.utility.implementations.DateCreatorUtils;
import com.entry.data.data.creator.unit.utility.interfaces.IConnectionUtils;
import com.entry.data.data.creator.unit.utility.interfaces.IDateCreatorUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilityConfig {

    @Bean
    public IDateCreatorUtils getDatecreatorUtils(){
        return new DateCreatorUtils();
    }

    @Bean
    public IConnectionUtils getConnectionUtils(){
        return new ConnectionUtils();
    }
}
