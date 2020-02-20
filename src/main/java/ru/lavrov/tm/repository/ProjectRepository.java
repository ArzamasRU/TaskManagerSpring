package ru.lavrov.tm.repository;

import ru.lavrov.tm.entity.Project;

import java.util.HashMap;
import java.util.Map;

public class ProjectRepository {
    Map<String, Project> projects = new HashMap();

    public Map<String, Project> findAll(){
        return projects;
    }

    public Project FindOne(String id){
        return projects.get(id);
    }

    public void persist(Project project){
        String id = project.getId();
        if (projects.containsKey(id))
            System.out.println("project already exists!");
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
}
