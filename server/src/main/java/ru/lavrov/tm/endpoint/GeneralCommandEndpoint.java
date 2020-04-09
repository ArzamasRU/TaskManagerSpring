package ru.lavrov.tm.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.*;
import ru.lavrov.tm.dto.*;
import ru.lavrov.tm.entity.Session;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.util.JAXBUtil;
import ru.lavrov.tm.util.SerializationUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static ru.lavrov.tm.service.AppPropertyServiceImpl.appProperties;
import static ru.lavrov.tm.util.JAXBUtil.readFromJSONByJAXB;
import static ru.lavrov.tm.util.JAXBUtil.writeToJSONByJAXB;

@NoArgsConstructor
@WebService(endpointInterface = "ru.lavrov.tm.endpoint.GeneralCommandEndpoint")
public final class GeneralCommandEndpoint extends AbstractEndpoint{

    @NotNull public static final String URL = "http://localhost:8090/GeneralCommandEndpoint?wsdl";

    public GeneralCommandEndpoint(final IServiceLocator bootstrap) {
        super(bootstrap);
    }

//    @WebMethod
//    public boolean serialize(@Nullable final String token) {
//        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
//        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
//        tokenService.validate(token, roles);
//        @Nullable final Session session = tokenService.decryptToken(token).getSession();
//        @NotNull final IUserService userService = bootstrap.getUserService();
//        @NotNull final User user = userService.findOne(session.getUserId());
//        SerializationUtil.write(Arrays.asList(user), appProperties.getProperty("users_file_path"));
//        @NotNull final IProjectService projectService = bootstrap.getProjectService();
//        @NotNull final Collection<Project> projectList = projectService.findAll(session.getUserId());
//        SerializationUtil.write(projectList, appProperties.getProperty("projects_file_path"));
//        @Nullable final ITaskService taskService = bootstrap.getTaskService();
//        @Nullable final Collection<Task> taskList = taskService.findAll(session.getUserId());
//        SerializationUtil.write(taskList, appProperties.getProperty("tasks_file_path"));
//        return true;
//    }
//
//    @WebMethod
//    public boolean deserialize(@Nullable final String token) {
//        @Nullable final Collection<Role> roles = Arrays.asList(Role.ADMIN);
//        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
//        tokenService.validate(token, roles);
//        @Nullable final Collection<Project> projectList =
//                SerializationUtil.read(appProperties.getProperty("projects_file_path"));
//        @Nullable final IProjectService projectService = bootstrap.getProjectService();
//        if (projectList != null)
//            for (@Nullable final Project project : projectList) {
//                if (project != null)
//                    projectService.persist(project);
//            }
//        @Nullable final Collection<Task> taskList =
//                SerializationUtil.read(appProperties.getProperty("tasks_file_path"));
//        @Nullable final ITaskService taskService = bootstrap.getTaskService();
//        if (taskList != null)
//            for (@Nullable final Task task : taskList) {
//                if (task != null)
//                    taskService.persist(task);
//            }
//        @Nullable final Collection<User> userList =
//                SerializationUtil.read(appProperties.getProperty("users_file_path"));
//        @Nullable final IUserService userService = bootstrap.getUserService();
//        if (userList != null)
//            for (@Nullable final User user : userList) {
//                if (user != null)
//                    userService.persist(user);
//            }
//        return true;
//    }
//
//    @WebMethod
//    public boolean dataToXMLByJAXB(@Nullable final String token) {
//        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
//        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
//        tokenService.validate(token, roles);
//        @Nullable final Session session = tokenService.decryptToken(token).getSession();
//        @NotNull final IUserService userService = bootstrap.getUserService();
//        @Nullable final User user = userService.findOne(session.getUserId());
//        if (user == null)
//            return false;
//        JAXBUtil.writeToXMLByJAXB(Arrays.asList(user), appProperties.getProperty("externalization_dir_path"));
//        @NotNull final IProjectService projectService = bootstrap.getProjectService();
//        @Nullable final Collection<Project> projectList = projectService.findAll(session.getUserId());
//        if (projectList == null)
//            return false;
//        JAXBUtil.writeToXMLByJAXB(projectList, appProperties.getProperty("externalization_dir_path"));
//        @NotNull final ITaskService taskService = bootstrap.getTaskService();
//        @Nullable final Collection<Task> taskList = taskService.findAll(session.getUserId());
//        if (taskList == null)
//            return false;
//        JAXBUtil.writeToXMLByJAXB(taskList, appProperties.getProperty("externalization_dir_path"));
//        return true;
//    }
//
//    @WebMethod
//    public boolean dataToXMLByFasterXML(@Nullable final String token) {
//        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
//        @NotNull final XmlMapper mapper = new XmlMapper();
//        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
//        tokenService.validate(token, roles);
//        @Nullable final Session session = tokenService.decryptToken(token).getSession();
//        @NotNull final IUserService userService = bootstrap.getUserService();
//        @Nullable final User user = userService.findOne(session.getUserId());
//        if (user == null)
//            return false;
//        @NotNull final IProjectService projectService = bootstrap.getProjectService();
//        @NotNull final ITaskService taskService = bootstrap.getTaskService();
//        @Nullable final Collection<Project> projectList = projectService.findAll(session.getUserId());
//        if (projectList == null)
//            return false;
//        @Nullable final Collection<Task> taskList = taskService.findAll(session.getUserId());
//        if (taskList == null)
//            return false;
//        try{
//            mapper.writerWithDefaultPrettyPrinter().writeValue(
//                    new File(appProperties.getProperty("users_file_path") + ".xml"), Arrays.asList(user));
//            mapper.writerWithDefaultPrettyPrinter().writeValue(
//                    new File(appProperties.getProperty("projects_file_path") + ".xml"), projectList);
//            mapper.writerWithDefaultPrettyPrinter().writeValue(
//                    new File(appProperties.getProperty("tasks_file_path") + ".xml"), taskList);
//        } catch (IOException e) {
//            return false;
//        }
//        return true;
//    }
//
//    @WebMethod
//    public boolean dataToJSONByJAXB(@Nullable final String token) {
//        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
//        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
//        tokenService.validate(token, roles);
//        @Nullable final Session session = tokenService.decryptToken(token).getSession();
//        @NotNull final IUserService userService = bootstrap.getUserService();
//        @Nullable final User user = userService.findOne(session.getUserId());
//        if (user == null)
//            return false;
//        @NotNull final ExternalizationStorage storage = new ExternalizationStorage();
//        @NotNull final IProjectService projectService = bootstrap.getProjectService();
//        @NotNull final Collection<Project> projectList = projectService.findAll(session.getUserId());
//        if (projectList == null)
//            return false;
//        @NotNull final ITaskService taskService = bootstrap.getTaskService();
//        @NotNull final Collection<Task> taskList = taskService.findAll(session.getUserId());
//        if (taskList == null)
//            return false;
//        storage.setProjectList(projectList);
//        storage.setTaskList(taskList);
//        storage.setUserList(Arrays.asList(user));
//        @NotNull final String filePath =
//                appProperties.getProperty("externalization_dir_path")
//                        + ExternalizationStorage.class.getSimpleName() + ".json";
//        writeToJSONByJAXB(storage, filePath);
//        return true;
//    }
//
//    @WebMethod
//    public boolean dataToJSONByFasterXML(@Nullable final String token) {
//        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
//        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
//        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
//        tokenService.validate(token, roles);
//        @Nullable final Session session = tokenService.decryptToken(token).getSession();
//        @NotNull final IUserService userService = bootstrap.getUserService();
//        @Nullable final User user = userService.findOne(session.getUserId());
//        if (user == null)
//            return false;
//        @NotNull final IProjectService projectService = bootstrap.getProjectService();
//        @NotNull final ITaskService taskService = bootstrap.getTaskService();
//        @Nullable final Collection<Project> projectList = projectService.findAll(session.getUserId());
//        if (projectList == null)
//            return false;
//        @Nullable final Collection<Task> taskList = taskService.findAll(session.getUserId());
//        if (taskList == null)
//            return false;
//        try{
//            objectMapper.writerWithDefaultPrettyPrinter().writeValue(
//                    new File(appProperties.getProperty("users_file_path") + ".json"), user);
//            objectMapper.writerWithDefaultPrettyPrinter().writeValue(
//                    new File(appProperties.getProperty("projects_file_path") + ".json"), projectList);
//            objectMapper.writerWithDefaultPrettyPrinter().writeValue(
//                    new File(appProperties.getProperty("tasks_file_path") + ".json"), taskList);
//        } catch (IOException e) {
//            return false;
//        }
//        return true;
//    }
//
//    @WebMethod
//    public boolean dataFromXMLByJAXB(@Nullable final String token) {
//        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
//        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
//        tokenService.validate(token, roles);
//        @Nullable final Collection<Project> projectList =
//                JAXBUtil.readFromXMLByJAXB(Project.class, appProperties.getProperty("externalization_dir_path"));
//        @Nullable final IProjectService projectService = bootstrap.getProjectService();
//        if (projectList != null)
//            for (@Nullable final Project project : projectList) {
//                if (project != null)
//                    projectService.persist(project);
//            }
//        @Nullable final Collection<Task> taskList =
//                JAXBUtil.readFromXMLByJAXB(Task.class, appProperties.getProperty("externalization_dir_path"));
//        @Nullable final ITaskService taskService = bootstrap.getTaskService();
//        if (taskList != null)
//            for (@Nullable final Task task : taskList) {
//                if (task != null)
//                    taskService.persist(task);
//            }
//        @Nullable final Collection<User> userList =
//                JAXBUtil.readFromXMLByJAXB(User.class, appProperties.getProperty("externalization_dir_path"));
//        @Nullable final IUserService userService = bootstrap.getUserService();
//        if (userList != null)
//            for (@Nullable final User user : userList) {
//                if (user != null)
//                    userService.persist(user);
//            }
//        return true;
//    }
//
//    @WebMethod
//    public boolean dataFromXMLByFasterXML(@Nullable final String token) {
//        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
//        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
//        tokenService.validate(token, roles);
//        @Nullable final XmlMapper xmlMapper = new XmlMapper();
//        @Nullable final Collection<Project> projectList;
//        @Nullable final Collection<Task> taskList;
//        @Nullable final Collection<User> userList;
//        @Nullable final IProjectService projectService = bootstrap.getProjectService();
//        @Nullable final ITaskService taskService = bootstrap.getTaskService();
//        @Nullable final IUserService userService = bootstrap.getUserService();
//        try {
//            projectList = Arrays.asList(xmlMapper.readValue(
//                    new File(appProperties.getProperty("projects_file_path") + ".xml"), Project[].class));
//            taskList = Arrays.asList(xmlMapper.readValue(
//                    new File(appProperties.getProperty("tasks_file_path") + ".xml"), Task[].class));
//            userList = Arrays.asList(xmlMapper.readValue(
//                    new File(appProperties.getProperty("users_file_path") + ".xml"), User[].class));
//        } catch (IOException e) {
//            return false;
//        }
//        for (@Nullable final Project project : projectList) {
//            if (project != null)
//                projectService.persist(project);
//        }
//        for (@Nullable final Task task : taskList) {
//            if (task != null)
//                taskService.persist(task);
//        }
//        for (@Nullable final User user : userList) {
//            if (user != null)
//                userService.persist(user);
//        }
//        return true;
//    }
//
//    @WebMethod
//    public boolean dataFromJSONByJAXB(@Nullable final String token) {
//        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
//        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
//        tokenService.validate(token, roles);
//        @NotNull final String filePath =
//                appProperties.getProperty("externalization_dir_path")
//                        + ExternalizationStorage.class.getSimpleName() + ".json";
//        @Nullable final ExternalizationStorage storage = readFromJSONByJAXB(ExternalizationStorage.class , filePath);
//        if (storage == null)
//            return false;
//        @Nullable final IProjectService projectService = bootstrap.getProjectService();
//        @Nullable final Collection<Project> projectList = storage.getProjectList();
//        if (projectList != null)
//            for (@Nullable final Project project : projectList) {
//                if (project != null)
//                    projectService.persist(project);
//            }
//        @Nullable final ITaskService taskService = bootstrap.getTaskService();
//        @Nullable final Collection<Task> taskList = storage.getTaskList();
//        if (taskList != null)
//            for (@Nullable final Task task : taskList) {
//                if (task != null)
//                    taskService.persist(task);
//            }
//        @Nullable final IUserService userService = bootstrap.getUserService();
//        @Nullable final Collection<User> userList = storage.getUserList();
//        if (userList != null)
//            for (@Nullable final User user : userList) {
//                if (user != null)
//                    userService.persist(user);
//            }
//        return true;
//    }
//
//    @WebMethod
//    public boolean dataFromJSONByFasterXML(@Nullable final String token) {
//        @NotNull final Collection<Role> roles = Arrays.asList(Role.ADMIN, Role.USER);
//        @NotNull final ITokenService tokenService = bootstrap.getTokenService();
//        tokenService.validate(token, roles);
//        @Nullable final ObjectMapper objectMapper = new ObjectMapper();
//        @Nullable final Collection<Project> projectList;
//        @Nullable final Collection<Task> taskList;
//        @Nullable final Collection<User> userList;
//        @NotNull final IProjectService projectService = bootstrap.getProjectService();
//        @NotNull final ITaskService taskService = bootstrap.getTaskService();
//        @NotNull final IUserService userService = bootstrap.getUserService();
//        try {
//            projectList = Arrays.asList(objectMapper.readValue(
//                    new File(appProperties.getProperty("projects_file_path") + ".json"), Project[].class));
//            taskList = Arrays.asList(objectMapper.readValue(
//                    new File(appProperties.getProperty("tasks_file_path") + ".json"), Task[].class));
//            userList = Arrays.asList(objectMapper.readValue(
//                    new File(appProperties.getProperty("users_file_path") + ".json"), User[].class));
//        } catch (IOException e) {
//            return false;
//        }
//        for (@Nullable final Project project : projectList) {
//            if (project != null)
//                projectService.persist(project);
//        }
//        for (@Nullable final Task task : taskList) {
//            if (task != null)
//                taskService.persist(task);
//        }
//        for (@Nullable final User user : userList) {
//            if (user != null)
//                userService.persist(user);
//        }
//        return true;
//    }
}
