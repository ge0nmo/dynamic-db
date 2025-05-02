package com.example.dynamicdb.config.db;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static com.example.dynamicdb.config.db.AppConfig.BRANCH_DB_PACKAGE;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        transactionManagerRef = "branchTransactionManager",
        entityManagerFactoryRef = "branchEntityManagerFactory",
        basePackages = {
                BRANCH_DB_PACKAGE
        }
)
public class BranchDbConfig
{
    @Bean(name = "branchEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean branchEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                             @Qualifier("branchDataSource") DataSource dataSource)
    {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        return builder
                .dataSource(dataSource)
                .properties(properties)
                .packages(BRANCH_DB_PACKAGE)
                .persistenceUnit("branch")
                .build();
    }

    @Bean(name = "branchTransactionManager")
    public PlatformTransactionManager branchTransactionManager(@Qualifier("branchEntityManagerFactory") EntityManagerFactory entityManagerFactory)
    {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
