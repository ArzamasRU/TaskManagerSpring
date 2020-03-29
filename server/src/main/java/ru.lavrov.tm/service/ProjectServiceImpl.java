package ru.lavrov.tm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectRepository;
import ru.lavrov.tm.api.IProjectService;
import ru.lavrov.tm.api.ITaskRepository;
import ru.lavrov.tm.api.IUserRepository;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.exception.general.DescriptionIsInvalidException;
import ru.lavrov.tm.exception.general.NameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNameExistsException;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.entity.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import static ru.lavrov.tm.constant.SerializationConstant.PROJECTS_FILE_PATH;

public final class ProjectServiceImpl extends AbstractService<Project> implements IProjectService {
    @NotNull
    protected final IProjectRepository projectRepository;
    @NotNull
    protected final ITaskRepository taskRepository;
    @NotNull
    protected final IUserRepository userRepository;

    public ProjectServiceImpl(final IProjectRepository projectRepository, final ITaskRepository taskRepository,
                              final IUserRepository userRepository) {
        super(projectRepository);
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createByProjectName(@Nullable final String userId, @Nullable final String projectName) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (projectRepository.findEntityByName(userId, projectName) != null)
            throw new ProjectNameExistsException();
        persist(new Project(projectName, userId));
    }

    @Override
    public void removeProjectByName(@Nullable final String userId, @Nullable final String projectName) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Project project = projectRepository.findEntityByName(userId, projectName);
        if (project != null)
            throw new ProjectNameExistsException();
        removeProject(userId, project.getId());
        taskRepository.removeProjectTasks(userId, project.getId());
    }

    @Override
    public void removeProject(@Nullable final String userId, @Nullable final String projectId) {
        if (projectId == null || projectId.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        removeProject(userId, projectId);
        taskRepository.removeProjectTasks(userId, projectId);
    }

    @Nullable
    @Override
    public Collection<Task> getProjectTasks(@Nullable final String userId, @Nullable final String projectName) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable final Project project = projectRepository.findEntityByName(userId, projectName);
        if (project == null)
            throw new ProjectNotExistsException();
        if (!project.getUserId().equals(userId))
            throw new ProjectNotExistsException();
        @Nullable final Collection<Task> collection = taskRepository.getProjectTasks(userId, project.getId());
        return collection;
    }

    @Nullable
    @Override
    public void renameProject(@Nullable final String userId, @Nullable final String oldName,
                              @Nullable final String newName) {
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        projectRepository.renameProject(userId, oldName, newName);
    }

    @Nullable
    @Override
    public Collection<Project> findAllByNamePart(@Nullable final String userId, @Nullable final String name) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (name == null || name.isEmpty())
            throw new NameIsInvalidException();
        @Nullable final Collection<Project> collection = projectRepository.findAllByNamePart(userId, name);
        return collection;
    }

    @Nullable
    @Override
    public Collection<Project> findAllByDescPart(@Nullable final String userId, @Nullable final String description) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (description == null || description.isEmpty())
            throw new DescriptionIsInvalidException();
        @Nullable final Collection<Project> collection = projectRepository.findAllByDescPart(userId, description);
        return collection;
    }

    @Nullable
    @Override
    public Collection<Project> findAll(@Nullable final String userId, @Nullable final Comparator<Project> comparator) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        return projectRepository.findAll(userId, comparator);
    }
}

