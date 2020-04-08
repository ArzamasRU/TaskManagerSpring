package ru.lavrov.tm.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectRepository;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.User;

import javax.persistence.EntityManager;
import java.util.Collection;

public class ProjectRepositoryImpl extends AbstractRepository implements IProjectRepository {

    public ProjectRepositoryImpl(@Nullable final EntityManager entityManager) {
        super(entityManager);
    }

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

    public @Nullable Project findEntityByName(@Nullable final String userId,
                                              @Nullable final String name){
        return entityManager
                .createQuery("FROM Project WHERE user_id = :userId AND name = :name", Project.class)
                .setParameter("userId", userId)
                .setParameter("name", name)
                .getSingleResult();
    }

    public void persist(@Nullable final Project project){
        entityManager.persist(project);
    }

    public void merge(@Nullable final Project project){
        entityManager.merge(project);
    }

    public void removeAll(@Nullable final String userId) {
        entityManager
                .createQuery("DELETE FROM Project WHERE user_id = :userId")
                .setParameter("userId", userId)
                .executeUpdate();
    }

    public void removeProject(@Nullable final String userId,
                              @Nullable final String id) {
        entityManager
                .createQuery("DELETE FROM Project WHERE user_id = :userId AND id = :id")
                .setParameter("userId", userId)
                .setParameter("id", id)
                .executeUpdate();
    }

    public @Nullable Project findOne(@Nullable final String userId,
                                     @Nullable final String id) {
        return entityManager
                .createQuery("FROM Project WHERE user_id = :userId AND id = :id", Project.class)
                .setParameter("userId", userId)
                .setParameter("id", id)
                .getSingleResult();
    }

    public @Nullable Collection<Project> findAllByDescPart(@Nullable final String userId,
                                                           @Nullable final String pattern) {
        return entityManager
                .createQuery("FROM Project " +
                        "WHERE user_id = :userId AND description LIKE :pattern", Project.class)
                .setParameter("userId", userId)
                .setParameter("pattern", pattern)
                .getResultList();
    }

    public @Nullable Collection<Project> findAllByNamePart(@Nullable final String userId,
                                                           @Nullable final String pattern) {
        return entityManager
                .createQuery("FROM Project WHERE user_id = :userId AND name LIKE :pattern", Project.class)
                .setParameter("userId", userId)
                .setParameter("pattern", pattern)
                .getResultList();
    }

    public @Nullable Collection<Project> findAll(@Nullable final String userId) {
        return entityManager
                .createQuery("FROM Project WHERE user_id = :userId", Project.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
