package com.remlists.list.write;

import org.axonframework.springboot.autoconfig.JdbcAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaEventStoreAutoConfiguration;
import org.axonframework.springboot.autoconfig.TransactionAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(
        exclude = {
                DataSourceAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class,
                DataSourceTransactionManagerAutoConfiguration.class,
                TransactionAutoConfiguration.class,
                JdbcAutoConfiguration.class,
                JpaAutoConfiguration.class,
                JpaEventStoreAutoConfiguration.class
                })
//@SpringBootApplication
public class RemlistsModuleListWriteApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemlistsModuleListWriteApplication.class, args);
    }

}
