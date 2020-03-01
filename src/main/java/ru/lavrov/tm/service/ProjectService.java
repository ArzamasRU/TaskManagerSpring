package ru.lavrov.tm.service;

import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.exception.project.ProjectNameExistsException;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.task.TaskNameIsInvalidException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
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

    public void createByName(String projectName) throws RuntimeException {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (projectRepository.findProjectByName(projectName) != null)
            throw new ProjectNameExistsException();
        persist(new Project(projectName));
    }

    public void persist(Project project) throws RuntimeException {
        if (project == null)
            throw new ProjectNotExistsException();
        projectRepository.persist(project);
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

    public Collection<Project> findAllByUser(String userId){
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        return projectRepository.findAllByUser(userId);
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

    public Collection<Task> getProjectTasksByUserId(String projectName, String userId) {
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        Project project = projectRepository.findProjectByName(projectName);
        if (project == null)
            throw new ProjectNotExistsException();
        Collection<Task> collection = taskRepository.getProjectTasksByUser(project, userId);
        return collection;
    }

    public void attachProjectToUser(String projectName, String userId) throws RuntimeException {
        if (projectName == null || projectName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        Project project = projectRepository.findProjectByName(projectName);
        if (project == null)
            throw new ProjectNotExistsException();
        projectRepository.attachProjectToUser(project.getId(), userId);
    }

    public void attachProjectToUserLogin(String projectName, String login) throws RuntimeException {
        attachProjectToUser(projectName, userRepository.findUserByLogin(login));
    }

    public void detachProjectFromUser(String projectName, String userId){
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        Project project = projectRepository.findProjectByName(projectName);
        if (project == null)
            throw new ProjectNotExistsException();
        project.setUserId(null);
    }

    public void detachProjectFromUserByLogin(String projectName, String login){
        detachProjectFromUser(projectName, userRepository.findUserByLogin(login));
    }

    public void renameProject(String oldName, String newName) throws RuntimeException {
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new ProjectNameIsInvalidException();
        projectRepository.renameProject(oldName, newName);
    }
}

