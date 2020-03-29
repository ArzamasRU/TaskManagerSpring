package ru.lavrov.tm.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.*;
import ru.lavrov.tm.constant.SerializationConstant;
import ru.lavrov.tm.entity.*;
import ru.lavrov.tm.util.JAXBUtil;
import ru.lavrov.tm.util.SerializationUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static ru.lavrov.tm.constant.SerializationConstant.*;
import static ru.lavrov.tm.util.JAXBUtil.readFromJSONByJAXB;
import static ru.lavrov.tm.util.JAXBUtil.writeToJSONByJAXB;

@NoArgsConstructor
@WebService(endpointInterface = "ru.lavrov.tm.endpoint.GeneralCommandEndpoint")
public final class GeneralCommandEndpoint extends AbstractEndpoint{

    @NotNull public static final String URL = "http://localhost:8090/GeneralCommandEndpoint?wsdl";

    public GeneralCommandEndpoint(final IServiceLocator bootstrap) {
        this.bootstrap = bootstrap;
    }

    @WebMethod
    public boolean serialize(@Nullable final Session session) {
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return false;
        @NotNull final IUserService userService = bootstrap.getUserService();
        @NotNull final User user = userService.findOne(session.getUserId());
        SerializationUtil.write(Arrays.asList(user), SerializationConstant.USERS_FILE_PATH);
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Collection<Project> projectList = projectService.findAll(session.getUserId());
        SerializationUtil.write(projectList, SerializationConstant.PROJECTS_FILE_PATH);
        @Nullable final ITaskService taskService = bootstrap.getTaskService();
        @Nullable final Collection<Task> taskList = taskService.findAll(session.getUserId());
        SerializationUtil.write(taskList, SerializationConstant.TASKS_FILE_PATH);
        return true;
    }

    @WebMethod
    public boolean deserialize(@Nullable final Session session) {
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return false;
        @Nullable final Collection<Project> projectList = SerializationUtil.read(SerializationConstant.PROJECTS_FILE_PATH);
        @Nullable final IProjectService projectService = bootstrap.getProjectService();
        if (projectList != null)
            for (@Nullable final Project project : projectList) {
                if (project != null)
                    projectService.persist(project);
            }
        @Nullable final Collection<Task> taskList = SerializationUtil.read(SerializationConstant.TASKS_FILE_PATH);
        @Nullable final ITaskService taskService = bootstrap.getTaskService();
        if (taskList != null)
            for (@Nullable final Task task : taskList) {
                if (task != null)
                    taskService.persist(task);
            }
        @Nullable final Collection<User> userList = SerializationUtil.read(SerializationConstant.USERS_FILE_PATH);
        @Nullable final IUserService userService = bootstrap.getUserService();
        if (userList != null)
            for (@Nullable final User user : userList) {
                if (user != null)
                    userService.persist(user);
            }
        return true;
    }

    @WebMethod
    public boolean dataToXMLByJAXB(@Nullable final Session session) {
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return false;
        @NotNull final IUserService userService = bootstrap.getUserService();
        @Nullable final User user = userService.findOne(session.getUserId());
        if (user == null)
            return false;
        JAXBUtil.writeToXMLByJAXB(Arrays.asList(user), SerializationConstant.EXTERNALIZATION_DIR_PATH);
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @Nullable final Collection<Project> projectList = projectService.findAll(session.getUserId());
        if (projectList == null)
            return false;
        JAXBUtil.writeToXMLByJAXB(projectList, SerializationConstant.EXTERNALIZATION_DIR_PATH);
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        @Nullable final Collection<Task> taskList = taskService.findAll(session.getUserId());
        if (taskList == null)
            return false;
        JAXBUtil.writeToXMLByJAXB(taskList, SerializationConstant.EXTERNALIZATION_DIR_PATH);
        return true;
    }

    @WebMethod
    public boolean dataToXMLByFasterXML(@Nullable final Session session) {
        @NotNull final XmlMapper mapper = new XmlMapper();
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return false;
        @NotNull final IUserService userService = bootstrap.getUserService();
        @Nullable final User user = userService.findOne(session.getUserId());
        if (user == null)
            return false;
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        @Nullable final Collection<Project> projectList = projectService.findAll(session.getUserId());
        if (projectList == null)
            return false;
        @Nullable final Collection<Task> taskList = taskService.findAll(session.getUserId());
        if (taskList == null)
            return false;
        try{
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(SerializationConstant.USERS_FILE_PATH + ".xml"), Arrays.asList(user));
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(SerializationConstant.PROJECTS_FILE_PATH + ".xml"), projectList);
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(SerializationConstant.TASKS_FILE_PATH + ".xml"), taskList);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @WebMethod
    public boolean dataToJSONByJAXB(@Nullable final Session session) {
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return false;
        @NotNull final IUserService userService = bootstrap.getUserService();
        @Nullable final User user = userService.findOne(session.getUserId());
        if (user == null)
            return false;
        @NotNull final ExternalizationStorage storage = new ExternalizationStorage();
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final Collection<Project> projectList = projectService.findAll(session.getUserId());
        if (projectList == null)
            return false;
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        @NotNull final Collection<Task> taskList = taskService.findAll(session.getUserId());
        if (taskList == null)
            return false;
        storage.setProjectList(projectList);
        storage.setTaskList(taskList);
        storage.setUserList(Arrays.asList(user));
        @NotNull final String filePath =
                SerializationConstant.EXTERNALIZATION_DIR_PATH + ExternalizationStorage.class.getSimpleName() + ".json";
        writeToJSONByJAXB(storage, filePath);
        return true;
    }

    @WebMethod
    public boolean dataToJSONByFasterXML(@Nullable final Session session) {
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return false;
        @NotNull final IUserService userService = bootstrap.getUserService();
        @Nullable final User user = userService.findOne(session.getUserId());
        if (user == null)
            return false;
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        @Nullable final Collection<Project> projectList = projectService.findAll(session.getUserId());
        if (projectList == null)
            return false;
        @Nullable final Collection<Task> taskList = taskService.findAll(session.getUserId());
        if (taskList == null)
            return false;
        try{
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(SerializationConstant.USERS_FILE_PATH + ".json"), user);
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(SerializationConstant.PROJECTS_FILE_PATH + ".json"), projectList);
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(SerializationConstant.TASKS_FILE_PATH + ".json"), taskList);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @WebMethod
    public boolean dataFromXMLByJAXB(@Nullable final Session session) {
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return false;
        @Nullable final Collection<Project> projectList =
                JAXBUtil.readFromXMLByJAXB(Project.class, SerializationConstant.EXTERNALIZATION_DIR_PATH);
        @Nullable final IProjectService projectService = bootstrap.getProjectService();
        if (projectList != null)
            for (@Nullable final Project project : projectList) {
                if (project != null)
                    projectService.persist(project);
            }
        @Nullable final Collection<Task> taskList =
                JAXBUtil.readFromXMLByJAXB(Task.class, SerializationConstant.EXTERNALIZATION_DIR_PATH);
        @Nullable final ITaskService taskService = bootstrap.getTaskService();
        if (taskList != null)
            for (@Nullable final Task task : taskList) {
                if (task != null)
                    taskService.persist(task);
            }
        @Nullable final Collection<User> userList =
                JAXBUtil.readFromXMLByJAXB(User.class, SerializationConstant.EXTERNALIZATION_DIR_PATH);
        @Nullable final IUserService userService = bootstrap.getUserService();
        if (userList != null)
            for (@Nullable final User user : userList) {
                if (user != null)
                    userService.persist(user);
            }
        return true;
    }

    @WebMethod
    public boolean dataFromXMLByFasterXML(@Nullable final Session session) {
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return false;
        @Nullable final XmlMapper xmlMapper = new XmlMapper();
        @Nullable final Collection<Project> projectList;
        @Nullable final Collection<Task> taskList;
        @Nullable final Collection<User> userList;
        @Nullable final IProjectService projectService = bootstrap.getProjectService();
        @Nullable final ITaskService taskService = bootstrap.getTaskService();
        @Nullable final IUserService userService = bootstrap.getUserService();
        try {
            projectList = Arrays.asList(xmlMapper
                    .readValue(new File(SerializationConstant.PROJECTS_FILE_PATH + ".xml"), Project[].class));
            taskList = Arrays.asList(xmlMapper
                    .readValue(new File(SerializationConstant.TASKS_FILE_PATH + ".xml"), Task[].class));
            userList = Arrays.asList(xmlMapper
                    .readValue(new File(SerializationConstant.USERS_FILE_PATH + ".xml"), User[].class));
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
    public boolean dataFromJSONByJAXB(@Nullable final Session session) {
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return false;
        @NotNull final String filePath =
                SerializationConstant.EXTERNALIZATION_DIR_PATH + ExternalizationStorage.class.getSimpleName() + ".json";
        @Nullable final ExternalizationStorage storage = readFromJSONByJAXB(ExternalizationStorage.class , filePath);
        if (storage == null)
            return false;
        @Nullable final IProjectService projectService = bootstrap.getProjectService();
        @Nullable final Collection<Project> projectList = storage.getProjectList();
        if (projectList != null)
            for (@Nullable final Project project : projectList) {
                if (project != null)
                    projectService.persist(project);
            }
        @Nullable final ITaskService taskService = bootstrap.getTaskService();
        @Nullable final Collection<Task> taskList = storage.getTaskList();
        if (taskList != null)
            for (@Nullable final Task task : taskList) {
                if (task != null)
                    taskService.persist(task);
            }
        @Nullable final IUserService userService = bootstrap.getUserService();
        @Nullable final Collection<User> userList = storage.getUserList();
        if (userList != null)
            for (@Nullable final User user : userList) {
                if (user != null)
                    userService.persist(user);
            }
        return true;
    }

    @WebMethod
    public boolean dataFromJSONByFasterXML(@Nullable final Session session) {
        @NotNull final ISessionService sessionService = bootstrap.getSessionService();
        if (!sessionService.isSessionValid(session))
            return false;
        @Nullable final ObjectMapper objectMapper = new ObjectMapper();
        @Nullable final Collection<Project> projectList;
        @Nullable final Collection<Task> taskList;
        @Nullable final Collection<User> userList;
        @NotNull final IProjectService projectService = bootstrap.getProjectService();
        @NotNull final ITaskService taskService = bootstrap.getTaskService();
        @NotNull final IUserService userService = bootstrap.getUserService();
        try {
            projectList = Arrays.asList(objectMapper
                    .readValue(new File(PROJECTS_FILE_PATH + ".json"), Project[].class));
            taskList = Arrays.asList(objectMapper
                    .readValue(new File(TASKS_FILE_PATH + ".json"), Task[].class));
            userList = Arrays.asList(objectMapper
                    .readValue(new File(USERS_FILE_PATH + ".json"), User[].class));
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
