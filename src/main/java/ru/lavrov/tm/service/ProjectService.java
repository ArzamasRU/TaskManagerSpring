package ru.lavrov.tm.service;

import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.exception.project.ProjectNameExistsException;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
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

    public void createByName(String projectName, String userId) throws RuntimeException {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        if (projectRepository.findProjectByName(projectName, userId) != null)
            throw new ProjectNameExistsException();
        persist(new Project(projectName, userId));
    }

    public void persist(Project project) throws RuntimeException {
        if (project == null)
            throw new ProjectNotExistsException();
        projectRepository.persist(project);
    }

    public void removeAll(String userId) {
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        projectRepository.removeAll(userId);
    }

//    public Collection<Project> findAll(){
//        return projectRepository.findAll();
//    }

    public Collection<Project> findAllByUser(String userId){
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        return projectRepository.findAllByUser(userId);
    }

    public void removeProjectByName(String projectName, String userId) throws RuntimeException {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        Project project = projectRepository.findProjectByName(projectName, userId);
        if (project != null)
            throw new ProjectNameExistsException();
        projectRepository.remove(project.getId(), userId);
        taskRepository.removeProjectTasks(project.getId(), userId);
    }

    public Collection<Task> getProjectTasks(String projectName, String userId) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        Project project = projectRepository.findProjectByName(projectName, userId);
        if (project == null)
            throw new ProjectNotExistsException();
        if (!project.getUserId().equals(userId))
            throw new ProjectNotExistsException();
        Collection<Task> collection = taskRepository.getProjectTasks(project.getId(), userId);
        return collection;
    }


    public void renameProject(String oldName, String newName, String userId) throws RuntimeException {
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        projectRepository.renameProject(oldName, newName, userId);
    }
}

