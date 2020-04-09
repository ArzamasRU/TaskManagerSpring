package ru.lavrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectRepository;
import ru.lavrov.tm.entity.Project;

import javax.persistence.EntityManager;
import java.util.Collection;

public class ProjectRepositoryImpl extends AbstractRepository implements IProjectRepository {

    public ProjectRepositoryImpl(@NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void renameProject(@Nullable final String userId,
                              @Nullable final String oldName,
                              @Nullable final String newName) {
        entityManager
                .createQuery("UPDATE Project SET name = :newName " +
                        "WHERE name = :oldName AND user_id = :userId", Project.class)
                .setParameter("userId", userId)
                .setParameter("oldName", oldName)
                .setParameter("newName", newName)
                .getResultList();
    }

    @Override
    public @Nullable Project findEntityByName(@Nullable final String userId,
                                              @Nullable final String name){
        return entityManager
                .createQuery("FROM Project WHERE user_id = :userId AND name = :name", Project.class)
                .setParameter("userId", userId)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public void persist(@Nullable final Project project){
        entityManager.persist(project);
    }

    @Override
    public void merge(@Nullable final Project project){
        entityManager.merge(project);
    }

    @Override
    public void removeAll(@Nullable final String userId) {
        entityManager
                .createQuery("DELETE FROM Project WHERE user_id = :userId")
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void removeProject(@Nullable final String userId,
                              @Nullable final String id) {
        entityManager
                .createQuery("DELETE FROM Project WHERE user_id = :userId AND id = :id")
                .setParameter("userId", userId)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public @Nullable Project findOne(@Nullable final String userId,
                                     @Nullable final String id) {
        return entityManager
                .createQuery("FROM Project WHERE user_id = :userId AND id = :id", Project.class)
                .setParameter("userId", userId)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public @Nullable Collection<Project> findAllByDescPart(@Nullable final String userId,
                                                           @Nullable final String pattern) {
        return entityManager
                .createQuery("FROM Project " +
                        "WHERE user_id = :userId AND description LIKE :pattern", Project.class)
                .setParameter("userId", userId)
                .setParameter("pattern", pattern)
                .getResultList();
    }

    @Override
    public @Nullable Collection<Project> findAllByNamePart(@Nullable final String userId,
                                                           @Nullable final String pattern) {
        return entityManager
                .createQuery("FROM Project WHERE user_id = :userId AND name LIKE :pattern", Project.class)
                .setParameter("userId", userId)
                .setParameter("pattern", pattern)
                .getResultList();
    }

    @Override
    public @Nullable Collection<Project> findAll(@Nullable final String userId) {
        return entityManager
                .createQuery("FROM Project WHERE user_id = :userId", Project.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
