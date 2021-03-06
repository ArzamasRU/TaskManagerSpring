package ru.lavrov.tm.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ru.lavrov.tm.api.service.IProjectService;
import ru.lavrov.tm.api.service.ITaskService;
import ru.lavrov.tm.api.service.ITokenService;
import ru.lavrov.tm.api.service.IUserService;
import ru.lavrov.tm.entity.*;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.util.JAXBUtil;
import ru.lavrov.tm.util.SerializationUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static ru.lavrov.tm.util.JAXBUtil.readFromJSONByJAXB;
import static ru.lavrov.tm.util.JAXBUtil.writeToJSONByJAXB;

@NoArgsConstructor
@Component
@WebService(endpointInterface = "ru.lavrov.tm.endpoint.GeneralCommandEndpoint")
public final class GeneralCommandEndpoint {

    @NotNull public static final String URL = "http://localhost:8090/GeneralCommandEndpoint?wsdl";

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ITaskService taskService;

    @Autowired
    private Environment environment;

    @WebMethod
    public boolean serialize(@Nullable final String token) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        @Nullable final User user = userService.findOne(session.getUserId());
        SerializationUtil.write(Arrays.asList(user), environment.getProperty("users_file_path"));
        @Nullable final Collection<Project> projectList = projectService.findAll(session.getUserId());
        SerializationUtil.write(projectList, environment.getProperty("projects_file_path"));
        @Nullable final Collection<Task> taskList = taskService.findAll(session.getUserId());
        SerializationUtil.write(taskList, environment.getProperty("tasks_file_path"));
        return true;
    }

    @WebMethod
    public boolean deserialize(@Nullable final String token) {
        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN);
        tokenService.validate(token, roles);
        @Nullable final Collection<Project> projectList =
                SerializationUtil.read(environment.getProperty("projects_file_path"));
        if (projectList != null)
            for (@Nullable final Project project : projectList) {
                if (project != null)
                    projectService.persist(project);
            }
        @Nullable final Collection<Task> taskList =
                SerializationUtil.read(environment.getProperty("tasks_file_path"));
        if (taskList != null)
            for (@Nullable final Task task : taskList) {
                if (task != null)
                    taskService.persist(task);
            }
        @Nullable final Collection<User> userList =
                SerializationUtil.read(environment.getProperty("users_file_path"));
        if (userList != null)
            for (@Nullable final User user : userList) {
                if (user != null)
                    userService.persist(user);
            }
        return true;
    }

    @WebMethod
    public boolean dataToXMLByJAXB(@Nullable final String token) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        @Nullable final User user = userService.findOne(session.getUserId());
        if (user == null)
            return false;
        JAXBUtil.writeToXMLByJAXB(Arrays.asList(user), environment.getProperty("externalization_dir_path"));
        @Nullable final Collection<Project> projectList = projectService.findAll(session.getUserId());
        if (projectList == null)
            return false;
        JAXBUtil.writeToXMLByJAXB(projectList, environment.getProperty("externalization_dir_path"));
        @Nullable final Collection<Task> taskList = taskService.findAll(session.getUserId());
        if (taskList == null)
            return false;
        JAXBUtil.writeToXMLByJAXB(taskList, environment.getProperty("externalization_dir_path"));
        return true;
    }

    @WebMethod
    public boolean dataToXMLByFasterXML(@Nullable final String token) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final XmlMapper mapper = new XmlMapper();
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        @Nullable final User user = userService.findOne(session.getUserId());
        if (user == null)
            return false;
        @Nullable final Collection<Project> projectList = projectService.findAll(session.getUserId());
        if (projectList == null)
            return false;
        @Nullable final Collection<Task> taskList = taskService.findAll(session.getUserId());
        if (taskList == null)
            return false;
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(
                    new File(environment.getProperty("users_file_path") + ".xml"), Arrays.asList(user));
            mapper.writerWithDefaultPrettyPrinter().writeValue(
                    new File(environment.getProperty("projects_file_path") + ".xml"), projectList);
            mapper.writerWithDefaultPrettyPrinter().writeValue(
                    new File(environment.getProperty("tasks_file_path") + ".xml"), taskList);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @WebMethod
    public boolean dataToJSONByJAXB(@Nullable final String token) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        @Nullable final User user = userService.findOne(session.getUserId());
        if (user == null)
            return false;
        @NotNull final ExternalizationStorage storage = new ExternalizationStorage();
        @Nullable final Collection<Project> projectList = projectService.findAll(session.getUserId());
        if (projectList == null)
            return false;
        @Nullable final Collection<Task> taskList = taskService.findAll(session.getUserId());
        if (taskList == null)
            return false;
        storage.setProjectList(projectList);
        storage.setTaskList(taskList);
        storage.setUserList(Arrays.asList(user));
        @NotNull final String filePath =
                environment.getProperty("externalization_dir_path")
                        + ExternalizationStorage.class.getSimpleName() + ".json";
        writeToJSONByJAXB(storage, filePath);
        return true;
    }

    @WebMethod
    public boolean dataToJSONByFasterXML(@Nullable final String token) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        tokenService.validate(token, roles);
        @Nullable final Session session = tokenService.decryptToken(token).getSession();
        @Nullable final User user = userService.findOne(session.getUserId());
        if (user == null)
            return false;
        @Nullable final Collection<Project> projectList = projectService.findAll(session.getUserId());
        if (projectList == null)
            return false;
        @Nullable final Collection<Task> taskList = taskService.findAll(session.getUserId());
        if (taskList == null)
            return false;
        try{
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(
                    new File(environment.getProperty("users_file_path") + ".json"), user);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(
                    new File(environment.getProperty("projects_file_path") + ".json"), projectList);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(
                    new File(environment.getProperty("tasks_file_path") + ".json"), taskList);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @WebMethod
    public boolean dataFromXMLByJAXB(@Nullable final String token) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        tokenService.validate(token, roles);
        @Nullable final Collection<Project> projectList =
                JAXBUtil.readFromXMLByJAXB(Project.class, environment.getProperty("externalization_dir_path"));
        if (projectList != null)
            for (@Nullable final Project project : projectList) {
                if (project != null)
                    projectService.persist(project);
            }
        @Nullable final Collection<Task> taskList =
                JAXBUtil.readFromXMLByJAXB(Task.class, environment.getProperty("externalization_dir_path"));
        if (taskList != null)
            for (@Nullable final Task task : taskList) {
                if (task != null)
                    taskService.persist(task);
            }
        @Nullable final Collection<User> userList =
                JAXBUtil.readFromXMLByJAXB(User.class, environment.getProperty("externalization_dir_path"));
        if (userList != null)
            for (@Nullable final User user : userList) {
                if (user != null)
                    userService.persist(user);
            }
        return true;
    }

    @WebMethod
    public boolean dataFromXMLByFasterXML(@Nullable final String token) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        tokenService.validate(token, roles);
        @Nullable final XmlMapper xmlMapper = new XmlMapper();
        @Nullable final Collection<Project> projectList;
        @Nullable final Collection<Task> taskList;
        @Nullable final Collection<User> userList;
        try {
            projectList = Arrays.asList(xmlMapper.readValue(
                    new File(environment.getProperty("projects_file_path") + ".xml"), Project[].class));
            taskList = Arrays.asList(xmlMapper.readValue(
                    new File(environment.getProperty("tasks_file_path") + ".xml"), Task[].class));
            userList = Arrays.asList(xmlMapper.readValue(
                    new File(environment.getProperty("users_file_path") + ".xml"), User[].class));
        } catch (IOException e) {
            return false;
        }
        for (@Nullable final Project project : projectList) {
            if (project != null)
                projectService.persist(project);
        }
        for (@Nullable final Task task : taskList) {
            if (task != null)
                taskService.persist(task);
        }
        for (@Nullable final User user : userList) {
            if (user != null)
                userService.persist(user);
        }
        return true;
    }

    @WebMethod
    public boolean dataFromJSONByJAXB(@Nullable final String token) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        tokenService.validate(token, roles);
        @NotNull final String filePath =
                environment.getProperty("externalization_dir_path")
                        + ExternalizationStorage.class.getSimpleName() + ".json";
        @Nullable final ExternalizationStorage storage = readFromJSONByJAXB(ExternalizationStorage.class , filePath);
        if (storage == null)
            return false;
        @Nullable final Collection<Project> projectList = storage.getProjectList();
        if (projectList != null)
            for (@Nullable final Project project : projectList) {
                if (project != null)
                    projectService.persist(project);
            }
        @Nullable final Collection<Task> taskList = storage.getTaskList();
        if (taskList != null)
            for (@Nullable final Task task : taskList) {
                if (task != null)
                    taskService.persist(task);
            }
        @Nullable final Collection<User> userList = storage.getUserList();
        if (userList != null)
            for (@Nullable final User user : userList) {
                if (user != null)
                    userService.persist(user);
            }
        return true;
    }

    @WebMethod
    public boolean dataFromJSONByFasterXML(@Nullable final String token) {
        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
        tokenService.validate(token, roles);
        @Nullable final ObjectMapper objectMapper = new ObjectMapper();
        @Nullable final Collection<Project> projectList;
        @Nullable final Collection<Task> taskList;
        @Nullable final Collection<User> userList;
        try {
            projectList = Arrays.asList(objectMapper.readValue(
                    new File(environment.getProperty("projects_file_path") + ".json"), Project[].class));
            taskList = Arrays.asList(objectMapper.readValue(
                    new File(environment.getProperty("tasks_file_path") + ".json"), Task[].class));
            userList = Arrays.asList(objectMapper.readValue(
                    new File(environment.getProperty("users_file_path") + ".json"), User[].class));
        } catch (IOException e) {
            return false;
        }
        for (@Nullable final Project project : projectList) {
            if (project != null)
                projectService.persist(project);
        }
        for (@Nullable final Task task : taskList) {
            if (task != null)
                taskService.persist(task);
        }
        for (@Nullable final User user : userList) {
            if (user != null)
                userService.persist(user);
        }
        return true;
    }
}
