package ru.lavrov.tm.api.repository;

import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lavrov.tm.entity.Project;

import java.util.Collection;

@Repository
public interface IProjectRepository extends JpaRepository<Project, String> {

    @Transactional
    @Modifying
    @Query("UPDATE Project SET name = :newName WHERE name = :oldName AND user_id = :userId")
    void renameProject(@Param("userId") @Nullable String userId,
                       @Param("oldName") @Nullable String oldName,
                       @Param("newName") @Nullable String newName);

    @Nullable Project findByUserIdAndName(@Nullable String userId,
                                          @Nullable String name);

    @Nullable Collection<Project> findByUserIdAndNameLike(@Nullable String userId,
                                                          @Nullable String pattern);

    @Nullable Collection<Project> findByUserIdAndDescriptionLike(@Nullable String userId,
                                                                 @Nullable String pattern);

    @Query("FROM Project WHERE user_id = :userId AND id = :id")
    @Nullable Project findByUserIdAndId(@Param("userId") @Nullable String userId,
                                        @Param("id") @Nullable String id);

    @Nullable Collection<Project> findAllByUserId(@Nullable String userId);
}
