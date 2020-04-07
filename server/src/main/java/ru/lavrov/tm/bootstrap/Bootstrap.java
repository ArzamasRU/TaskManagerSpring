package ru.lavrov.tm.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.*;
import ru.lavrov.tm.endpoint.*;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.service.*;

import javax.sql.DataSource;
import javax.xml.ws.Endpoint;

import static ru.lavrov.tm.service.AppPropertyServiceImpl.appProperties;

@Getter
@NoArgsConstructor
public final class Bootstrap implements IServiceLocator {
    @NotNull
    private final IProjectService projectService = new ProjectServiceImpl();
    @NotNull
    private final ITaskService taskService = new TaskServiceImpl();
    @NotNull
    private final IUserService userService = new UserServiceImpl();
    @NotNull
    private final ISessionService sessionService = new SessionServiceImpl();
    @NotNull
    private final ITokenService tokenService = new TokenServiceImpl(this);
    @NotNull
    private final IAppPropertyService propertyService = new AppPropertyServiceImpl();
    @NotNull
    private final UserEndpoint userEndpoint = new UserEndpoint(this);
    @NotNull
    private final TokenEndpoint tokenEndpoint = new TokenEndpoint(this);
    @NotNull
    private final ProjectEndpoint projectEndpoint = new ProjectEndpoint(this);
    @NotNull
    private final TaskEndpoint taskEndpoint = new TaskEndpoint(this);
    @NotNull
    private final GeneralCommandEndpoint generalCommandEndpoint = new GeneralCommandEndpoint(this);

    public void init() {
        initProperties();
        initUsers();
        initEndpoints();
        System.out.println("*** SERVER STARTED ***");
    }

    private void initEndpoints() {
        Endpoint.publish(UserEndpoint.URL, userEndpoint);
        Endpoint.publish(TokenEndpoint.URL, tokenEndpoint);
        Endpoint.publish(ProjectEndpoint.URL, projectEndpoint);
        Endpoint.publish(TaskEndpoint.URL, taskEndpoint);
        Endpoint.publish(GeneralCommandEndpoint.URL, generalCommandEndpoint);
    }

    private void initUsers() {
        if (userService.findUserByLogin("user") == null)
            userService.createByLogin("user", "user", Role.USER.getRole());
        if (userService.findUserByLogin("admin") == null)
            userService.createByLogin("admin", "admin", Role.ADMIN.getRole());
    }

    private void initProperties() {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        @Nullable final String user = appProperties.getProperty("login");
        @Nullable final String password = appProperties.getProperty("password");
        @Nullable final String url = appProperties.getProperty("url");
        @Nullable final String driver = appProperties.getProperty("driver");
        final DataSource dataSource = new PooledDataSource(driver, url, user, password);
        final TransactionFactory transactionFactory =
                new JdbcTransactionFactory();
        final Environment environment =
                new Environment("development", transactionFactory, dataSource);
        final Configuration configuration = new Configuration(environment);
        configuration.addMapper(IUserRepository.class);
        configuration.addMapper(IProjectRepository.class);
        configuration.addMapper(ITaskRepository.class);
        return new SqlSessionFactoryBuilder().build(configuration);
    }

//    private EntityManagerFactory factory() {
//        final Map<String, String> settings = new HashMap<>();
//        settings.put(Environment.DRIVER, appProperties.getProperty("driver"));
//        settings.put(Environment.URL, appProperties.getProperty("url"));
//        settings.put(Environment.USER, appProperties.getProperty("login"));
//        settings.put(Environment.PASS, appProperties.getProperty("password"));
//        settings.put(Environment.DIALECT,
//                "org.hibernate.dialect.MySQL5InnoDBDialect");
//        settings.put(Environment.HBM2DDL_AUTO, "update");
//        settings.put(Environment.SHOW_SQL, "true");
//        final StandardServiceRegistryBuilder registryBuilder
//                = new StandardServiceRegistryBuilder();
//        registryBuilder.applySettings(settings);
//        final StandardServiceRegistry registry = registryBuilder.build();
//        final MetadataSources sources = new MetadataSources(registry);
//        sources.addAnnotatedClass(Task.class);
//        sources.addAnnotatedClass(Project.class);
//        sources.addAnnotatedClass(User.class);
//        sources.addAnnotatedClass(Session.class);
//        sources.addAnnotatedClass(Cat.class);
//        final Metadata metadata = sources.getMetadataBuilder().build();
//        return metadata.getSessionFactoryBuilder().build();
//    }
}

