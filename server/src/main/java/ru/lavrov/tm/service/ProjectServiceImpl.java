package ru.lavrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lavrov.tm.api.repository.IProjectRepository;
import ru.lavrov.tm.api.repository.ITaskRepository;
import ru.lavrov.tm.api.repository.IUserRepository;
import ru.lavrov.tm.api.service.IProjectService;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.common.DescriptionIsInvalidException;
import ru.lavrov.tm.exception.common.NameIsInvalidException;
import ru.lavrov.tm.exception.db.RequestIsFailedException;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.exception.user.UserNotExistsException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

@Service
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private ITaskRepository taskRepository;
    @Autowired
    private IProjectRepository projectRepository;
    @Autowired
    private IUserRepository userRepository;
    
    @Override
    public void createByProjectName(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable User user = null;
        try {
            user = userRepository.getOne(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (user == null)
                throw new UserNotExistsException();
            projectRepository.save(new Project(projectName, user));
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } 
    }

    @Override
    public void createProject(@NotNull final String userId, @NotNull final Project project) {
        if (project == null)
            throw new ProjectNotExistsException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable User user = null;
        try {
            user = userRepository.getOne(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (user == null)
                throw new UserNotExistsException();
            project.setUser(user);
            projectRepository.save(project);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        }
    }

    @Transactional
    @Override
    public void removeProjectByName(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        try {
            @Nullable final Project project = projectRepository.findByUserIdAndName(userId, projectName);
            projectRepository.delete(project);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } 
    }

    @Transactional
    @Override
    public void removeProject(@NotNull final String userId, @NotNull final String projectId) {
        if (projectId == null || projectId.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        try {
            @Nullable final Project project = projectRepository.findByUserIdAndId(userId, projectId);
            projectRepository.delete(project);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } 
    }

    @Transactional
    @Override
    public void removeAll(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        try {
            @Nullable final Collection<Project> projectList = projectRepository.findAllByUserId(userId);
            projectRepository.deleteAll(projectList);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } 
    }

    @Override
    public void renameProject(
            @NotNull final String userId, @NotNull final String oldName, @NotNull final String newName
    )  {
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        try {
            projectRepository.renameProject(userId, oldName, newName);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } 
    }

    @Override
    public void persist(@NotNull final Project entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        try {
            projectRepository.save(entity);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } 
    }

    @Override
    public void merge(@NotNull final Project entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        try {
            projectRepository.save(entity);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } 
    }

    @Override
    public @Nullable Collection<Task> getProjectTasks(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        try {
            @Nullable final Project project = projectRepository.findByUserIdAndName(userId, projectName);
            return taskRepository.findByUserIdAndProjectId(userId, project.getId());
        } catch (RuntimeException e) {
            e.printStackTrace();
        } 
        return null;
    }

    @Override
    public @Nullable Collection<Project> findAllByNamePart(@NotNull final String userId, @NotNull final String name) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (name == null || name.isEmpty())
            throw new NameIsInvalidException();
        try {
            return projectRepository.findByUserIdAndNameLike(userId, "%" + name + "%");
        } catch (RuntimeException e) {
            e.printStackTrace();
        } 
        return null;
    }

    @Override
    public @Nullable Collection<Project> findAllByDescPart(
            @NotNull final String userId, @NotNull final String description
    ) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (description == null || description.isEmpty())
            throw new DescriptionIsInvalidException();
        try {
            return projectRepository.findByUserIdAndDescriptionLike(userId, "%" + description + "%");
        } catch (RuntimeException e) {
            e.printStackTrace();
        } 
        return null;
    }

    @Override
    public @Nullable Collection<Project> findAll(
            @NotNull final String userId, @Nullable final Comparator<Project> comparator
    ) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable Collection<Project> list = null;
        try {
             list = projectRepository.findAllByUserId(userId);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } 
        if (list != null && comparator != null)
            ((ArrayList<Project>) list).sort(comparator);
        return list;
    }

    @Override
    public @Nullable Project findProjectByName(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        try {
            return projectRepository.findByUserIdAndName(userId, projectName);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } 
        return null;
    }

    @Override
    public @Nullable Collection<Project> findAll(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        try {
            return projectRepository.findAllByUserId(userId);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } 
        return null;
    }

    @Override
    public @Nullable Project findOne(@NotNull final String userId, @NotNull String entityId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (entityId == null || entityId.isEmpty())
            throw new ProjectNotExistsException();
        try {
            return projectRepository.findByUserIdAndId(userId, entityId);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } 
        return null;
    }
}

