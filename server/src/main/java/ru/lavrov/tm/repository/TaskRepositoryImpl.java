package ru.lavrov.tm.repository;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.ITaskRepository;
import ru.lavrov.tm.entity.Task;

import javax.persistence.EntityManager;
import java.util.Collection;

public class TaskRepositoryImpl extends AbstractRepository implements ITaskRepository {

    public TaskRepositoryImpl(@Nullable final EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void removeTask(@Nullable final Task task) {
        entityManager.remove(task);
    }

    @Override
    public void removeAll(@Nullable final Collection<Task> taskList) {
        if (taskList == null)
            return;
        for (@Nullable final Task task : taskList) {
            entityManager.remove(task);
        }
    }

    @Override
    public @Nullable Collection<Task> getProjectTasks(@Nullable final String userId,
                                                      @Nullable final String projectId) {
        return entityManager
                .createQuery("FROM Task WHERE user_id = :userId AND project_id = :projectId", Task.class)
                .setParameter("userId", userId)
                .setParameter("projectId", projectId)
                .getResultList();
    }

    @Override
    public @Nullable Task findProjectTaskByName(@Nullable final String userId,
                                                @Nullable final String name,
                                                @Nullable final String projectId) {
        return entityManager
                .createQuery("FROM Task " +
                        "WHERE user_id = :userId AND name = :name AND project_id = :projectId", Task.class)
                .setParameter("userId", userId)
                .setParameter("name", name)
                .setParameter("projectId", projectId)
                .getSingleResult();
    }

    @Override
    public void renameTask(@Nullable final String userId,
                           @Nullable final String projectId,
                           @Nullable final String oldName,
                           @Nullable final String newName) {
        entityManager
                .createQuery("UPDATE Task SET name = :newName " +
                        "WHERE name = :oldName AND user_id = :userId AND project_id = :projectId")
                .setParameter("userId", userId)
                .setParameter("projectId", projectId)
                .setParameter("oldName", oldName)
                .setParameter("newName", newName)
                .executeUpdate();
    }

    @Override
    public @Nullable Collection<Task> findAll(@Nullable final String userId) {
        return entityManager
                .createQuery("FROM Task WHERE user_id = :userId", Task.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public @Nullable Collection<Task> findAllByNamePart(@Nullable final String userId,
                                                        @Nullable final String pattern) {
        return entityManager
                .createQuery("FROM Task WHERE user_id = :userId AND name LIKE :pattern", Task.class)
                .setParameter("userId", userId)
                .setParameter("pattern", pattern)
                .getResultList();
    }

    @Override
    public @Nullable Collection<Task> findAllByDescPart(@Nullable final String userId,
                                                        @Nullable final String pattern) {
        return entityManager
                .createQuery("FROM Task WHERE user_id = :userId AND description LIKE :pattern", Task.class)
                .setParameter("userId", userId)
                .setParameter("pattern", pattern)
                .getResultList();
    }

    @Override
    public @Nullable Task findOne(@Nullable final String userId,
                                  @Nullable final String id) {
        return entityManager
                .createQuery("FROM Task WHERE user_id = :userId AND id = :id", Task.class)
                .setParameter("userId", userId)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void persist(@Nullable final Task task) {
        entityManager.persist(task);
    }

    @Override
    public void merge(@Nullable final Task task) {
        entityManager.merge(task);
    }

    @Override
    public @Nullable Task findEntityByName(@Nullable final String userId,
                                           @Nullable final String name) {
        return entityManager
                .createQuery("FROM Task WHERE user_id = :userId AND name = :name", Task.class)
                .setParameter("userId", userId)
                .setParameter("name", name)
                .getSingleResult();
    }
}
