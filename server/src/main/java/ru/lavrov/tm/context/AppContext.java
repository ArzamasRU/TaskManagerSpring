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

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
//@PropertySource("application.properties")
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

//    @Bean
//    public DataSource dataSource(
//            @Value("${datasource.driver}") final String dataSourceDriver,
//            @Value("${datasource.url}") final String dataSourceUrl,
//            @Value("${datasource.user}") final String dataSourceUser,
//            @Value("${datasource.password}") final String dataSourcePassword
//    ) {
//        final DriverManagerDataSource dataSource =
//                new DriverManagerDataSource();
//        dataSource.setDriverClassName(dataSourceDriver);
//        dataSource.setUrl(dataSourceUrl);
//        dataSource.setUsername(dataSourceUser);
//        dataSource.setPassword(dataSourcePassword);
//        return dataSource;
//    }
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            final DataSource dataSource,
//            @Value("${hibernate.show_sql}") final boolean showSql,
//            @Value("${hibernate.hbm2ddl.auto}") final String tableStrategy,
//            @Value("${hibernate.dialect}") final String dialect
//    ) {
//        final LocalContainerEntityManagerFactoryBean factoryBean;
//        factoryBean = new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setDataSource(dataSource);
//        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        factoryBean.setPackagesToScan("ru.volnenko.spring.model");
//        final Properties properties = new Properties();
//        properties.put("hibernate.show_sql", showSql);
//        properties.put("hibernate.hbm2ddl.auto", tableStrategy);
//        properties.put("hibernate.dialect", dialect);
//        factoryBean.setJpaProperties(properties);
//        return factoryBean;
//    }
}
