package com.remlists.list.write.infrastructure.spring.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.HashMap;

import static com.remlists.list.write.infrastructure.spring.BeanNames.Infrastructure.Spring.entityManagerListWrite;
import static com.remlists.list.write.infrastructure.spring.BeanNames.Infrastructure.Spring.dataSourceListWrite;
import static com.remlists.list.write.infrastructure.spring.BeanNames.Infrastructure.Spring.transactionManagerListWrite;

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.remlists.list.write.infrastructure.spring.springData",
                        "com.remlists.list.write.infrastructure.jpa.impl"},
        entityManagerFactoryRef = entityManagerListWrite,
        transactionManagerRef = transactionManagerListWrite
)
@EnableTransactionManagement
@EntityScan({"com.remlists.list.write.infrastructure.jpa.entities",
            "com.remlists.list.write.infrastructure.jpa.valueObjects"})
public class ListWriteDataBaseConnectionConfig {

    @Value("${remlists.list.write.spring.jpa.properties.hibernate.dialect}")
    private String dialect;
    @Value("${remlists.list.write.spring.jpa.properties.hibernate.format_sql}")
    private String formatSql;
    @Value("${remlists.list.write.spring.jpa.properties.hibernate.default_schema}")
    private String schema;
    @Value("${remlists.list.write.spring.jpa.hibernate.ddl-auto}")
    private String ddlauto;
    @Value("${remlists.list.write.spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation}")
    private String lobNonConextualCreation;

    @Value("${remlists.list.write.spring.datasource.driver-class-name}")
    private String driverClass;
    @Value("${remlists.list.write.spring.datasource.url}")
    private String url;
    @Value("${remlists.list.write.spring.datasource.username}")
    private String username;
    @Value("${remlists.list.write.spring.datasource.password}")
    private String pass;
    @Value("${remlists.list.write.spring.datasource.hikari.connection-timeout}")
    private String connectionTimeout;
    @Value("${remlists.list.write.spring.datasource.hikari.idle-timeout}")
    private String idleTimeout;
    @Value("${remlists.list.write.spring.datasource.hikari.max-lifetime}")
    private String maxLifetime;
    @Value("${remlists.list.write.spring.datasource.hikari.maximum-pool-size}")
    private String maximunPoolSize;
    @Value("${remlists.list.write.spring.datasource.hikari.minimum-idle}")
    private String minimumIdle;


    @Value("${remlists.list.write.spring.jpa.show-sql}")
    private String showSql;
    @Value("${remlists.list.write.spring.jpa.database}")
    private String databaseType;
    @Value("${remlists.list.write.spring.jpa.generate-ddl}")
    private String generateDdl;
    @Value("${remlists.list.write.spring.datasource.platform}")
    private String platform;



    @Bean(dataSourceListWrite)
    public DataSource dataSource() {

        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClass);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(pass);
        config.setConnectionTimeout(Long.parseLong(connectionTimeout));
        config.setIdleTimeout(Long.parseLong(idleTimeout));
        config.setMaxLifetime(Long.parseLong(maxLifetime));
        config.setMaximumPoolSize(Integer.parseInt(maximunPoolSize));
        config.setMinimumIdle(Integer.parseInt(minimumIdle));

        DataSource ds = new HikariDataSource(config);

        return ds;
    }



    @Bean(entityManagerListWrite)
    @PersistenceContext(unitName = "remlists-list-write")
    public LocalContainerEntityManagerFactoryBean entityManager() {

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", ddlauto);
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.format_sql", formatSql);
        properties.put("hibernate.default_schema", schema);
        properties.put("hibernate.temp.use_jdbc_metadata_defaults", lobNonConextualCreation);


        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform(platform);
        vendorAdapter.setGenerateDdl(Boolean.parseBoolean(generateDdl));
        vendorAdapter.setShowSql(Boolean.parseBoolean(showSql));
        vendorAdapter.setDatabase(Database.valueOf(databaseType));

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPersistenceUnitName("remlists-list-write");
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaPropertyMap(properties);



        return em;
    }



    @Bean(transactionManagerListWrite)
//    @Primary
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManager().getObject());
        transactionManager.setPersistenceUnitName("remlists-list-write");
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    @Bean
    @Profile("test")
    public DataSourceInitializer dataSourceInitializerListWrite() {


        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScripts(new ClassPathResource("list-write-schema-h2.sql"),
                                     new ClassPathResource("list-write-data-h2.sql"));


        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource());
        dataSourceInitializer.setDatabasePopulator(databasePopulator);
        dataSourceInitializer.setEnabled(true);

        return dataSourceInitializer;
    }







}
