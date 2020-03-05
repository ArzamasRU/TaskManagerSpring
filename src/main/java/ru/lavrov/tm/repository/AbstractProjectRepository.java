//package ru.lavrov.tm.repository;
//
//import ru.lavrov.tm.api.IProjectRepository;
//import ru.lavrov.tm.entity.Project;
//import ru.lavrov.tm.exception.project.ProjectExistsException;
//import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
//import ru.lavrov.tm.exception.project.ProjectNotExistsException;
//import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//public abstract class AbstractProjectRepository implements IProjectRepository<Project> {
//    protected final Map<String, Project> projects = new HashMap();
//
//    @Override
//    public void persist(final Project project) throws RuntimeException{
//        if (project == null)
//            throw new ProjectNotExistsException();
//        final String id = project.getId();
//        if (projects.containsKey(id))
//            throw new ProjectExistsException();
//        projects.put(id, project);
//    }
//
//    @Override
//    public void merge(final Project project){
//        if (project == null)
//            throw new ProjectNotExistsException();
//        projects.put(project.getId(), project);
//    }
//
//    @Override
//    public void remove(final String projectId, final String userId){
//        if (projectId == null || projectId.isEmpty())
//            throw new ProjectNotExistsException();
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        final Project project = projects.get(projectId);
//        if (!project.getUserId().equals(userId))
//            throw new ProjectNotExistsException();
//        projects.remove(projectId);
//    }
//
//    @Override
//    public void removeAll(final String userId){
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        final Collection<Project> list = findAll(userId);
//        if (list == null)
//            return;
//        for (final Project project : list) {
//            if (project == null)
//                continue;
//            remove(project.getId(), userId);
//        }
//    }
//
//    @Override
//    public Project findEntityByName(final String projectName, final String userId){
//        if (projectName == null || projectName.isEmpty())
//            throw new ProjectNameIsInvalidException();
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        Project currentProject = null;
//        for (final Project project: projects.values()) {
//            if (project == null)
//                continue;
//            boolean isProjectNameEquals = projectName.equals(project.getName());
//            boolean isProjectUserIdEquals = project.getUserId().equals(userId);
//            if (isProjectNameEquals && isProjectUserIdEquals) {
//                currentProject = project;
//                break;
//            }
//        }
//        return currentProject;
//    }
//
//    @Override
//    public Collection<Project> findAll(final String userId) {
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        final Collection<Project> list = new ArrayList<>();
//        for (final Project project : projects.values()) {
//            if (project == null)
//                continue;
//            if (project.getUserId().equals(userId))
//                list.add(project);
//        }
//        return list;
//    }
//}
