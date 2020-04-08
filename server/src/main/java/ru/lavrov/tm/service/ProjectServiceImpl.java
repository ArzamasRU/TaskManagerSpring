package ru.lavrov.tm.service;

import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectRepository;
import ru.lavrov.tm.api.IProjectService;
import ru.lavrov.tm.api.ITaskRepository;
import ru.lavrov.tm.api.IUserRepository;
import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.common.DescriptionIsInvalidException;
import ru.lavrov.tm.exception.common.NameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNameExistsException;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
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
    public void createByProjectName(@NotNull final Project project) {
        if (project == null)
            throw new ProjectNotExistsException();
        @NotNull final EntityManager entityManager = bootstrap.getEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepositoryImpl(entityManager);
        try {
            entityManager.getTransaction().begin();
            projectRepository.persist(project);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public @Nullable Project findProjectByName(@Nullable final String userId, @Nullable final String projectName) {
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
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void removeProjectByName(@Nullable final String userId, @Nullable final String projectName) {
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
        } finally {
            entityManager.close();
        }
    }
//
//    @Override
//    public void removeProject(@Nullable final String userId, @Nullable final String projectId) {
//        if (projectId == null || projectId.isEmpty())
//            throw new ProjectNameIsInvalidException();
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        @Nullable final Connection connection = getConnection();
//        if (connection == null)
//            throw new ConnectionPendingException();
//        @NotNull final SqlSession sqlSession = Bootstrap.getSqlSessionFactory().openSession();
//        @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
//        @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
//        try {
//            projectRepository.removeProject(userId, projectId);
//            taskRepository.removeProjectTasks(userId, projectId);
//            sqlSession.commit();
//        } catch (Exception e) {
//            sqlSession.rollback();
//        } finally {
//            sqlSession.close();
//        }
//    }
//
//    @Nullable
//    @Override
//    public Collection<Task> getProjectTasks(@Nullable final String userId, @Nullable final String projectName) {
//        if (projectName == null || projectName.isEmpty())
//            throw new ProjectNameIsInvalidException();
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        @Nullable final Connection connection = getConnection();
//        if (connection == null)
//            throw new ConnectionPendingException();
//        @NotNull final SqlSession sqlSession = Bootstrap.getSqlSessionFactory().openSession();
//        @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
//        @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
//        @Nullable final Project project = projectRepository.findEntityByName(userId, projectName);
//        if (project == null)
//            throw new ProjectNotExistsException();
//        if (!project.getUserId().equals(userId))
//            throw new ProjectNotExistsException();
//        @Nullable final Collection<Task> collection = taskRepository.getProjectTasks(userId, project.getId());
//        return collection;
//    }
//
//    @Nullable
//    @Override
//    public void renameProject(
//            @Nullable final String userId, @Nullable final String oldName, @Nullable final String newName
//    )  {
//        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
//            throw new ProjectNameIsInvalidException();
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        @Nullable final Connection connection = getConnection();
//        if (connection == null)
//            throw new ConnectionPendingException();
//        @NotNull final SqlSession sqlSession = Bootstrap.getSqlSessionFactory().openSession();
//        @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
//        try {
//            projectRepository.renameProject(userId, oldName, newName);
//            sqlSession.commit();
//        } catch (Exception e) {
//            sqlSession.rollback();
//        } finally {
//            sqlSession.close();
//        }
//    }
//
//    @Nullable
//    @Override
//    public Collection<Project> findAllByNamePart(@Nullable final String userId, @Nullable final String name) {
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        if (name == null || name.isEmpty())
//            throw new NameIsInvalidException();
//        @Nullable final Connection connection = getConnection();
//        if (connection == null)
//            throw new ConnectionPendingException();
//        @NotNull final SqlSession sqlSession = Bootstrap.getSqlSessionFactory().openSession();
//        @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
//        @Nullable final Collection<Project> collection =
//                projectRepository.findAllByNamePart(userId, "%" + name + "%");
//        return collection;
//    }
//
//    @Nullable
//    @Override
//    public Collection<Project> findAllByDescPart(@Nullable final String userId, @Nullable final String description) {
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        if (description == null || description.isEmpty())
//            throw new DescriptionIsInvalidException();
//        @Nullable final Connection connection = getConnection();
//        if (connection == null)
//            throw new ConnectionPendingException();
//        @NotNull final SqlSession sqlSession = Bootstrap.getSqlSessionFactory().openSession();
//        @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
//        @Nullable final Collection<Project> collection =
//                projectRepository.findAllByDescPart(userId, "%" + description + "%");
//        return collection;
//    }
//
//    @Nullable
//    @Override
//    public Collection<Project> findAll(@Nullable final String userId, @Nullable final Comparator<Project> comparator) {
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        @Nullable Collection<Project> list;
//        @Nullable final Connection connection = getConnection();
//        if (connection == null)
//            throw new ConnectionPendingException();
//        @NotNull final SqlSession sqlSession = Bootstrap.getSqlSessionFactory().openSession();
//        @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
//        list = projectRepository.findAll(userId);
//        if (comparator == null)
//            return list;
//        ((ArrayList<Project>) list).sort(comparator);
//        return list;
//    }
//
//    @Override
//    public void persist(@Nullable final Project entity) {
//        if (entity == null)
//            throw new ProjectNotExistsException();
//        @Nullable final Connection connection = getConnection();
//        if (connection == null)
//            throw new ConnectionPendingException();
//        @NotNull final SqlSession sqlSession = Bootstrap.getSqlSessionFactory().openSession();
//        @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
//        try {
//            projectRepository.persist(entity);
//            sqlSession.commit();
//        } catch (Exception e) {
//            sqlSession.rollback();
//        } finally {
//            sqlSession.close();
//        }
//    }
//
//    @Override
//    public void merge(@Nullable final Project entity) {
//        if (entity == null)
//            throw new ProjectNotExistsException();
//        @Nullable final Connection connection = getConnection();
//        if (connection == null)
//            throw new ConnectionPendingException();
//        @NotNull final SqlSession sqlSession = Bootstrap.getSqlSessionFactory().openSession();
//        @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
//        try {
//            projectRepository.persist(entity);
//            sqlSession.commit();
//        } catch (Exception e) {
//            sqlSession.rollback();
//        } finally {
//            sqlSession.close();
//        }
//    }
//
//    @Override
//    public void removeAll(@Nullable final String userId) {
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        @Nullable final Connection connection = getConnection();
//        if (connection == null)
//            throw new ConnectionPendingException();
//        @NotNull final SqlSession sqlSession = Bootstrap.getSqlSessionFactory().openSession();
//        @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
//        try {
//            projectRepository.removeAll(userId);
//            sqlSession.commit();
//        } catch (Exception e) {
//            sqlSession.rollback();
//        } finally {
//            sqlSession.close();
//        }
//    }
//
//    @Nullable
//    @Override
//    public Collection<Project> findAll(@Nullable final String userId) {
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        @Nullable final Connection connection = getConnection();
//        if (connection == null)
//            throw new ConnectionPendingException();
//        @NotNull final SqlSession sqlSession = Bootstrap.getSqlSessionFactory().openSession();
//        @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
//        return projectRepository.findAll(userId);
//    }
//
//    @Nullable
//    @Override
//    public Project findOne(@Nullable final String userId, @Nullable String entityID) {
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        if (entityID == null || entityID.isEmpty())
//            throw new ProjectNotExistsException();
//        @Nullable final Connection connection = getConnection();
//        if (connection == null)
//            throw new ConnectionPendingException();
//        @NotNull final SqlSession sqlSession = Bootstrap.getSqlSessionFactory().openSession();
//        @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
//        return projectRepository.findOne(userId, entityID);
//    }
}

