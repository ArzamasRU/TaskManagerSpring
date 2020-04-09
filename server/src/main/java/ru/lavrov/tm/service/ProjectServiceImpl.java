package ru.lavrov.tm.service;

import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectRepository;
import ru.lavrov.tm.api.IProjectService;
import ru.lavrov.tm.api.ITaskRepository;
import ru.lavrov.tm.api.IUserRepository;
import ru.lavrov.tm.bootstrap.Bootstrap;
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
import ru.lavrov.tm.repository.ProjectRepositoryImpl;
import ru.lavrov.tm.repository.TaskRepositoryImpl;
import ru.lavrov.tm.repository.UserRepositoryImpl;

import javax.persistence.EntityManager;
import java.nio.channels.ConnectionPendingException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public final class ProjectServiceImpl extends AbstractService implements IProjectService {

    public ProjectServiceImpl(@NotNull Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public void createByProjectName(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(entityManager);
        @NotNull final IUserRepository userRepository = new UserRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            @Nullable final User user = userRepository.findOne(userId);
            if (user == null)
                throw new UserNotExistsException();
            projectRepository.persist(new Project(projectName, user));
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void removeProjectByName(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(entityManager);
        @NotNull final ITaskRepository taskRepository = new TaskRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            @Nullable final Project project = projectRepository.findEntityByName(userId, projectName);
            if (project == null)
                throw new ProjectNotExistsException();
            projectRepository.removeProject(userId, project.getId());
            taskRepository.removeProjectTasks(userId, project.getId());
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void removeProject(@NotNull final String userId, @NotNull final String projectId) {
        if (projectId == null || projectId.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(entityManager);
        @NotNull final ITaskRepository taskRepository = new TaskRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            projectRepository.removeProject(userId, projectId);
            taskRepository.removeProjectTasks(userId, projectId);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
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
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            projectRepository.renameProject(userId, oldName, newName);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void removeAll(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            projectRepository.removeAll(userId);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void persist(@NotNull final Project entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            projectRepository.persist(entity);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void merge(@NotNull final Project entity) {
        if (entity == null)
            throw new ProjectNotExistsException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            projectRepository.merge(entity);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public @Nullable Collection<Task> getProjectTasks(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(entityManager);
        @NotNull final ITaskRepository taskRepository = new TaskRepositoryImpl(entityManager);
        try {
            @Nullable final Project project = projectRepository.findEntityByName(userId, projectName);
            if (project == null)
                throw new ProjectNotExistsException();
            return taskRepository.getProjectTasks(userId, project.getId());
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public @Nullable Collection<Project> findAllByNamePart(@NotNull final String userId, @NotNull final String name) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (name == null || name.isEmpty())
            throw new NameIsInvalidException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(entityManager);
        try {
            return projectRepository.findAllByNamePart(userId, "%" + name + "%");
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public @Nullable Collection<Project> findAllByDescPart(
            @NotNull final String userId, @NotNull final String description
    ) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (description == null || description.isEmpty())
            throw new DescriptionIsInvalidException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(entityManager);
        try {
            return projectRepository.findAllByDescPart(userId, "%" + description + "%");
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public @Nullable Collection<Project> findAll(
            @NotNull final String userId, @Nullable final Comparator<Project> comparator
    ) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @Nullable Collection<Project> list;
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(entityManager);
        try {
             list = projectRepository.findAll(userId);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
        if (comparator != null)
            ((ArrayList<Project>) list).sort(comparator);
        return list;
    }

    @Override
    public @Nullable Project findProjectByName(@NotNull final String userId, @NotNull final String projectName) {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(entityManager);
        try {
            return projectRepository.findEntityByName(userId, projectName);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public @Nullable Collection<Project> findAll(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(entityManager);
        try {
            return projectRepository.findAll(userId);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public @Nullable Project findOne(@NotNull final String userId, @NotNull String entityId) {
        if (userId == null || userId.isEmpty())
            throw new UserIsNotAuthorizedException();
        if (entityId == null || entityId.isEmpty())
            throw new ProjectNotExistsException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(entityManager);
        try {
            return projectRepository.findOne(userId, entityId);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RequestIsFailedException();
        } finally {
            entityManager.close();
        }
    }
}

