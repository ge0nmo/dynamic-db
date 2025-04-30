package com.example.dynamicdb.config.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource
{
    private final Map<Object, Object> targetDataSources = new ConcurrentHashMap<>();
    private final Set<String> keyMap = ConcurrentHashMap.newKeySet();

    @Override
    protected Object determineCurrentLookupKey()
    {
        log.info("DynamicRoutingDataSource.determineCurrentLookupKey()");
        String key = DatabaseContextHolder.getBranch();
        log.info("Routing to database key = {}", key);
        return key;
    }

    public void addDataSource(String key, DataSource dataSource)
    {
        targetDataSources.put(key, dataSource);
        keyMap.add(key);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    public boolean hasDataSource(String key)
    {
        return keyMap.contains(key);
    }
}
