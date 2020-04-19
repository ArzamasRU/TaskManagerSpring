package ru.lavrov.tm.context;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.lavrov.tm.api.service.IUserService;
import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.endpoint.*;

import javax.sql.DataSource;
import javax.xml.ws.Endpoint;
import java.util.Properties;

@Configuration
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
@EnableTransactionManagement
@EnableJpaRepositories("ru.lavrov.tm.api.repository")
@ComponentScan("ru.lavrov.tm")
public class AppContext {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        @NotNull final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("driver"));
        dataSource.setUrl(environment.getProperty("url"));
        dataSource.setUsername(environment.getProperty("login"));
        dataSource.setPassword(environment.getProperty("password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@NotNull final DataSource dataSource) {
        @NotNull final LocalContainerEntityManagerFactoryBean factoryBean;
        factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setPackagesToScan("ru.lavrov.tm");
        @NotNull final Properties properties = new Properties();
        properties.put("hibernate.show_sql", environment.getProperty("SHOW_SQL"));
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("HBM2DDL_AUTO"));
        properties.put("hibernate.dialect", environment.getProperty("dialect"));
        factoryBean.setJpaProperties(properties);
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(@NotNull final LocalContainerEntityManagerFactoryBean emf) {
        @NotNull final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf.getObject());
        return transactionManager;
    }
}
