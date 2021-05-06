package com.aped.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuração do Datasource SQL Server para instancia np01
 *
 */
@Profile("!test")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(//
        basePackages = "com.aped", //
        entityManagerFactoryRef = "sql-np01-em", //
        transactionManagerRef = "sql-np01-tm")
public class BDConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    /**
     * Factory para criação do Data Source
     *
     * @return
     */
    @Bean(name = "sql-np01-ds")
    public DataSource np01DataSourceFactory() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        dataSourceBuilder.url(url);
        return dataSourceBuilder.build();
    }

    /**
     * Factory para criação do Entity Manager
     *
     * @param builder
     * @return
     */
    @PersistenceContext(unitName = "np01")
    @Bean(name = "sql-np01-em")
    public LocalContainerEntityManagerFactoryBean sqlServernp01EntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder.dataSource(np01DataSourceFactory()).persistenceUnit("np01").properties(jpaProperties())
                .packages("com.aped.entity").build();
    }

    /**
     * Factory para criação do Transaction Manager
     *
     * @param em
     * @return
     */
    @Bean(name = "sql-np01-tm")
    public PlatformTransactionManager np01TransactionManagerFactory(@Qualifier("sql-np01-em") EntityManagerFactory em) {
        return new JpaTransactionManager(em);
    }

    /**
     * Propriedades do Persistence Unity
     *
     * @return
     */
//	@Bean(name = "spring.jpa.properties.hibernate.dialect")
    private Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return props;
    }
}
