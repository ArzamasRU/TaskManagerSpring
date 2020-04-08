package ru.lavrov.tm.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectRepository;
import ru.lavrov.tm.entity.Project;

import javax.persistence.EntityManager;
import java.util.Collection;

public class ProjectRepositoryImpl extends AbstractRepository implements IProjectRepository {

    public ProjectRepositoryImpl(@NotNull final EntityManager entityManager) {
        super(entityManager);
    }

    public void renameProject(@NotNull final String userId,
                       @NotNull final String oldName,
                       @NotNull final String newName) {
        entityManager
                .createQuery("UPDATE app_project SET name = :newName " +
                        "WHERE name = :oldName AND user_id = :userId", Project.class)
                .setParameter("userId", userId)
                .setParameter("oldName", oldName)
                .setParameter("newName", newName)
                .getResultList();
    }

    public @Nullable Collection<Project> findEntityByName(@NotNull String userId,
                                                         @NotNull String name){
        return entityManager
                .createQuery("SELECT p FROM Project p " +
                        "WHERE p.user_id = :userId AND name = :name", Project.class)
                .setParameter("userId", userId)
                .setParameter("name", name)
                .getResultList();
    }

    public void persist(@NotNull Project project){
        entityManager.persist(project);
    }
}
