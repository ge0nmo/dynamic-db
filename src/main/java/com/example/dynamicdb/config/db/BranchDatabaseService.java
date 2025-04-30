package com.example.dynamicdb.config.db;

import com.example.dynamicdb.main.domain.MainMember;
import com.example.dynamicdb.main.domain.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.lang.reflect.Member;

@Slf4j
@Service
public class BranchDatabaseService
{
    private final DynamicRoutingDataSource routingDataSource;

    public BranchDatabaseService(@Qualifier("branchDataSource") DynamicRoutingDataSource routingDataSource)
    {
        this.routingDataSource = routingDataSource;
    }

    public void switchDatabase(MainMember member)
    {
        Team team = member.getTeam();
        String branchKey = getDataSourceKey(team.getName(), team.getDbUsername());
        log.info("BranchDatabaseService.switchDatabase={}", branchKey);

        if(!routingDataSource.hasDataSource(branchKey)){
            DataSource branchDataSource = DataSourceBuilder.create()
                    .url(team.getDbUrl())
                    .username(team.getDbUsername())
                    .password(team.getDbPassword())
                    .build();

            routingDataSource.addDataSource(branchKey, branchDataSource);
        }

        DatabaseContextHolder.setBranch(branchKey);
    }

    public void clear()
    {
        DatabaseContextHolder.clearBranch();
    }

    private String getDataSourceKey(String teamName, String dbUsername)
    {

        return teamName + "_" + dbUsername;
    }
}
