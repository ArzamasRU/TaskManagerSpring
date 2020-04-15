package ru.lavrov.tm.api.repository;

import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lavrov.tm.entity.Task;

import java.util.Collection;
import java.util.List;

@Repository
public interface ITaskRepository extends JpaRepository<Task, String> {

    @Nullable Collection<Task> findByUserIdAndProjectId(@Nullable String userId,
                                                        @Nullable String projectId);

    @Transactional
    @Modifying
    @Query("UPDATE Task SET name = :newName WHERE name = :oldName AND user_id = :userId AND project_id = :projectId")
    void renameTask(@Param("userId") @Nullable String userId,
                    @Param("projectId") @Nullable String projectId,
                    @Param("oldName") @Nullable String oldName,
                    @Param("newName") @Nullable String newName);

    @Nullable Collection<Task> findAllByUserId(@Nullable String userId);

    @Nullable Collection<Task> findByUserIdAndNameLike(@Nullable String userId,
                                                       @Nullable String name);

    @Nullable Collection<Task> findByUserIdAndDescriptionLike(@Nullable String userId,
                                                              @Nullable String description);

    @Nullable Task findByUserIdAndName(@Nullable String userId,
                                       @Nullable String name);

    @Query("FROM Task WHERE user_id = :userId AND id = :id")
    @Nullable Task findByUserIdAndId(@Param("userId") @Nullable String userId,
                                     @Param("id") @Nullable String id);
}
