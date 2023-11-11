package com.example.shop.global.config.database;

import com.zaxxer.hikari.HikariDataSource;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.example.shop.domain.*"
)
public class DataSourceConfig {

    @Bean("writeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.write")
    public DataSource writeDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean("readDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.read")
    public DataSource readDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean("routingDataSource")
    @ConditionalOnBean(name = {"writeDataSource", "readDataSource"})
    public DataSource routingDataSource(
        @Qualifier("writeDataSource") DataSource writeDataSource,
        @Qualifier("readDataSource") DataSource readDataSource) {

        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setTargetDataSources(
            Map.of("write", writeDataSource, "read", readDataSource));
        routingDataSource.setDefaultTargetDataSource(writeDataSource);
        return routingDataSource;
    }

    @Primary
    @Bean(name = "currentDataSource")
    @ConditionalOnBean(name = "routingDataSource")
    public LazyConnectionDataSourceProxy currentDataSource(DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }
}
