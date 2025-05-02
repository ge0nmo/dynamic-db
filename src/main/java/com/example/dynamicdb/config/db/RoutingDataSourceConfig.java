package com.example.dynamicdb.config.db;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import static com.example.dynamicdb.config.db.AppConfig.INIT_DB;

@Configuration
public class RoutingDataSourceConfig
{
    private final DataSource mainDataSource;

    public RoutingDataSourceConfig(@Qualifier("mainDataSource")DataSource mainDataSource)
    {
        this.mainDataSource = mainDataSource;
    }

    @Bean(name = "branchDataSource")
    public DynamicRoutingDataSource dynamicRoutingDataSource()
    {
        DynamicRoutingDataSource routingDataSource = new DynamicRoutingDataSource();
        routingDataSource.addDataSource(INIT_DB, mainDataSource);
        return routingDataSource;
    }

    @PostConstruct
    public void setInitialContext()
    {
        DatabaseContextHolder.setBranch(INIT_DB);
    }

}
