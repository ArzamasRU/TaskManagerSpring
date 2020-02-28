package ru.lavrov.tm.service;

import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.projectException.ProjectNameExistsException;
import ru.lavrov.tm.exception.projectException.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.projectException.ProjectNotExistsException;
import ru.lavrov.tm.exception.taskException.TaskNameIsInvalidException;
import ru.lavrov.tm.exception.userException.UserIsNotAuthorizedException;
import ru.lavrov.tm.repository.ProjectRepository;
import ru.lavrov.tm.repository.TaskRepository;
import ru.lavrov.tm.repository.UserRepository;

import java.util.Collection;

public class ProjectService {
    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectService(ProjectRepository projectRepository, TaskRepository taskRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public void persist(String projectName) throws RuntimeException {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (projectRepository.findProjectByName(projectName) != null)
            throw new ProjectNameExistsException();
        projectRepository.persist(new Project(projectName));
    }

    public void merge(String projectName) throws RuntimeException {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        projectRepository.merge(new Project(projectName));
    }

    public void removeAll() {
        projectRepository.removeAll();
    }

    public Collection<Project> findAll(){
        return projectRepository.findAll();
    }

    public Collection<Project> findAllByUser(User sessionUser){
        if (sessionUser == null)
            throw new UserIsNotAuthorizedException();
        return projectRepository.findAllByUser(sessionUser);
    }

    public void removeProject(String projectName) throws RuntimeException {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        Project project = projectRepository.findProjectByName(projectName);
        if (project == null)
            throw new ProjectNotExistsException();
        projectRepository.remove(project.getId());
        taskRepository.removeProjectTasks(project);
    }

    public Collection<Task> getProjectTasks(String projectName) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        Project project = projectRepository.findProjectByName(projectName);
        if (project == null)
            throw new ProjectNotExistsException();
        Collection<Task> collection = taskRepository.getProjectTasks(project);
        return collection;
    }

    public Collection<Task> getProjectTasksByUser(String projectName, User sessionUser) {
        if (sessionUser == null)
            throw new UserIsNotAuthorizedException();
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        Project project = projectRepository.findProjectByName(projectName);
        if (project == null)
            throw new ProjectNotExistsException();
        Collection<Task> collection = taskRepository.getProjectTasksByUser(project, sessionUser);
        return collection;
    }

    public void attachProjectToUser(String projectName, User sessionUser) throws RuntimeException {
        if (projectName == null || projectName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (sessionUser == null)
            throw new UserIsNotAuthorizedException();
        Project project = projectRepository.findProjectByName(projectName);
        if (project == null)
            throw new ProjectNotExistsException();
        projectRepository.attachProjectToUser(project, sessionUser);
    }

    public void attachProjectToUser(String projectName, String login) throws RuntimeException {
        attachProjectToUser(projectName, userRepository.findUserByLogin(login));
    }

    public void detachProjectfromUser(User sessionUser, String projectName){
        if (sessionUser == null)
            throw new UserIsNotAuthorizedException();
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        Project project = projectRepository.findProjectByName(projectName);
        if (project == null)
            throw new ProjectNotExistsException();
        project.setUserId(null);
    }

    public void detachProjectfromUser(String login, String projectName){
        detachProjectfromUser(userRepository.findUserByLogin(login), projectName);
    }

    public void renameProject(String oldName, String newName) throws RuntimeException {
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new ProjectNameIsInvalidException();
        projectRepository.renameProject(oldName, newName);
    }
}

