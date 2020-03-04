package ru.lavrov.tm.repository;

import ru.lavrov.tm.api.ProjectRepository;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.exception.project.ProjectExistsException;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractProjectRepository implements ProjectRepository<Project> {
    protected final Map<String, Project> projects = new HashMap();

    @Override
    public void persist(final Project project) throws RuntimeException{
        if (project == null)
            throw new ProjectNotExistsException();
        String id = project.getId();
        if (projects.containsKey(id))
            throw new ProjectExistsException();
        projects.put(id, project);
    }

    @Override
    public void merge(final Project project){
        projects.put(project.getId(), project);
    }

    @Override
    public void remove(final String projectId, final String userId){
        if (projectId == null)
            throw new ProjectNotExistsException();
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        Project project = projects.get(projectId);
        if (!project.getUserId().equals(userId))
            throw new ProjectNotExistsException();
        projects.remove(projectId);
    }

    @Override
    public void removeAll(final String userId){
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        for (Project project : findAllByUser(userId)) {
            remove(project.getId(), userId);
        }
    }

    @Override
    public Project findEntityByName(final String projectName, final String userId){
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        Project currentProject = null;
        for (Project project: projects.values()) {
            if (projectName.equals(project.getName()) && project.getUserId().equals(userId)) {
                currentProject = project;
                break;
            }
        }
        return currentProject;
    }

    @Override
    public Collection<Project> findAllByUser(final String userId) {
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        Collection<Project> list = new ArrayList<>();
        for (Project project : projects.values()) {
            if (project.getUserId().equals(userId))
                list.add(project);
        }
        return list;
    }
}
