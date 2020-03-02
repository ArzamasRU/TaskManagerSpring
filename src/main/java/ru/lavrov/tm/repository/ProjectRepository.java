package ru.lavrov.tm.repository;

import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.exception.project.ProjectExistsException;
import ru.lavrov.tm.exception.project.ProjectNameExistsException;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProjectRepository {
    private Map<String, Project> projects = new HashMap();

//    public Collection<Project> findAll(){
//        return projects.values();
//    }

    public Collection<Project> findAllByUser(String userId) {
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        Collection<Project> list = new ArrayList<>();
        for (Project project : projects.values()) {
            if (project.getUserId().equals(userId))
                list.add(project);
        }
        return list;
    }

//    public Project findOne(String projectId){
//        if (projectId == null)
//            throw new UserIsNotAuthorizedException();
//        return projects.get(projectId);
//    }

    public void persist(Project project) throws RuntimeException{
        if (project == null)
            throw new ProjectNotExistsException();
        String id = project.getId();
        if (projects.containsKey(id))
            throw new ProjectExistsException();
        projects.put(id, project);
    }

    public void merge(Project project){
        projects.put(project.getId(), project);
    }

    public void remove(String projectId, String userId){
        if (projectId == null)
            throw new ProjectNotExistsException();
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        Project project = projects.get(projectId);
        if (!project.getUserId().equals(userId))
            throw new ProjectNotExistsException();
        projects.remove(projectId);
    }

    public void removeAll(String userId){
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        for (Project project : findAllByUser(userId)) {
            remove(project.getId(), userId);
        }
    }

    public Project findProjectByName(String projectName, String userId){
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

    public void renameProject(String oldName, String newName, String userId) throws RuntimeException {
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        Project project = findProjectByName(newName, userId);
        if (project != null)
            throw new ProjectNameExistsException();
        project = findProjectByName(oldName, userId);
        if (!project.getUserId().equals(userId))
            throw new ProjectNotExistsException();
        project.setName(newName);
    }
}
