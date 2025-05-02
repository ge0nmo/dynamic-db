package com.example.dynamicdb.config.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource
{
    private final Map<Object, Object> dataSourceMap = new ConcurrentHashMap<>();
    private final Set<String> keyMap = ConcurrentHashMap.newKeySet();

    public DynamicRoutingDataSource()
    {
        setLenientFallback(false);
        setTargetDataSources(Collections.unmodifiableMap(dataSourceMap));
        afterPropertiesSet();
    }


    @Override
    protected Object determineCurrentLookupKey()
    {
        log.info("DynamicRoutingDataSource.determineCurrentLookupKey()");
        String key = DatabaseContextHolder.getBranch();
        log.info("Routing to database key = {}", key);
        if(key == null){
            throw new IllegalStateException("No branch key found in context");
        }
        return key;
    }

    public synchronized void addDataSource(String key, DataSource dataSource)
    {
        dataSourceMap.put(key, dataSource);
        keyMap.add(key);
        setTargetDataSources(Collections.unmodifiableMap(dataSourceMap));
        afterPropertiesSet();
    }

    public boolean hasDataSource(String key)
    {
        return keyMap.contains(key);
    }
}
