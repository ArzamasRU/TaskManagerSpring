package ru.lavrov.tm.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.*;
import ru.lavrov.tm.endpoint.*;
import ru.lavrov.tm.entity.*;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.service.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.xml.ws.Endpoint;

import java.util.HashMap;
import java.util.Map;

import static ru.lavrov.tm.service.AppPropertyServiceImpl.appProperties;
import static ru.lavrov.tm.util.HashUtil.md5Hard;

@Getter
@NoArgsConstructor
public final class Bootstrap implements IServiceLocator {
    @NotNull
    private final IProjectService projectService = new ProjectServiceImpl(this);
    @NotNull
    private final ITaskService taskService = new TaskServiceImpl(this);
    @NotNull
    private final IUserService userService = new UserServiceImpl(this);
    @NotNull
    private final ISessionService sessionService = new SessionServiceImpl(this);
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
    @NotNull
    private final EntityManagerFactory entityManagerFactory = getEntityManagerFactory();

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
            userService.createByLogin("user", md5Hard("user"), Role.USER.name());
        if (userService.findUserByLogin("admin") == null)
            userService.createByLogin("admin", md5Hard("admin"), Role.ADMIN.name());
        Project prj = null;
        Project.getProjectDTO(prj);
    }

    private void initProperties() {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
    }

    public @NotNull EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    private static EntityManagerFactory getEntityManagerFactory() {
        @NotNull final Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, appProperties.getProperty("driver"));
        settings.put(Environment.URL, appProperties.getProperty("url"));
        settings.put(Environment.USER, appProperties.getProperty("login"));
        settings.put(Environment.PASS, appProperties.getProperty("password"));
        settings.put(Environment.DIALECT, appProperties.getProperty("dialect"));
        settings.put(Environment.HBM2DDL_AUTO, appProperties.getProperty("HBM2DDL_AUTO"));
        settings.put(Environment.SHOW_SQL, appProperties.getProperty("SHOW_SQL"));
        @NotNull final StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        registryBuilder.applySettings(settings);
        @NotNull final StandardServiceRegistry registry = registryBuilder.build();
        @NotNull final MetadataSources sources = new MetadataSources(registry);
        sources.addAnnotatedClass(Task.class);
        sources.addAnnotatedClass(Project.class);
        sources.addAnnotatedClass(User.class);
        sources.addAnnotatedClass(Session.class);
        sources.addAnnotatedClass(Test.class);
        @NotNull final Metadata metadata = sources.getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }
}

