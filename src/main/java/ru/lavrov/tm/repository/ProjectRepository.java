package ru.lavrov.tm.repository;

import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.exception.ProjectExistsException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProjectRepository {
    private Map<String, Project> projects = new HashMap();

    public Collection<Project> findAll(){
        return projects.values();
    }

    public Project FindOne(String id){
        return projects.get(id);
    }

    public void persist(Project project) throws RuntimeException{
        String id = project.getId();
        if (projects.containsKey(id))
            throw new ProjectExistsException();
        else
            projects.put(id, project);
    }

    public void merge(Project project){
        projects.put(project.getId(), project);
    }

    public void remove(String id){
        projects.remove(id);
    }

    public void removeAll(){
        projects.clear();
    }

    public Project findProjectByName(String name){
        Project currentProject = null;
        for (Project project: findAll()) {
            currentProject = project;
            if (name.equals(project.getName()))
                break;
        }
        return currentProject;
    }

    public void renameProject(String oldName, String newName) throws RuntimeException {
        findProjectByName(oldName).setName(newName);
    }
}
