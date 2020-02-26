package ru.lavrov.tm.service;

import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.exception.projectException.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.projectException.ProjectNameExistsException;
import ru.lavrov.tm.exception.projectException.ProjectNotExistsException;
import ru.lavrov.tm.repository.ProjectRepository;
import ru.lavrov.tm.repository.TaskRepository;

import java.util.Collection;

public class ProjectService {
    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectService(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
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

    public void renameProject(String oldName, String newName) throws RuntimeException {
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new ProjectNameIsInvalidException();
        projectRepository.renameProject(oldName, newName);
    }
}

