package com.example.dynamicdb.config.db;

import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static com.example.dynamicdb.config.db.AppConfig.MAIN_DB_PACKAGE;

@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        transactionManagerRef = "mainTransactionManager",
        entityManagerFactoryRef = "mainEntityManagerFactory",
        basePackages = {
                MAIN_DB_PACKAGE
        }
)
public class MainDbConfig
{
    @Primary
    @Bean(name = "mainDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.main")
    public DataSource dataSource()
    {
        log.info("MainDbConfig.dataSource()");
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "mainEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("mainDataSource") DataSource dataSource)
    {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        return builder.dataSource(dataSource)
                .properties(properties)
                .packages(MAIN_DB_PACKAGE)
                .persistenceUnit("main")
                .build();
    }

    @Primary
    @Bean(name = "mainTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("mainEntityManagerFactory") EntityManagerFactory entityManagerFactory)
    {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
