package ru.lavrov.tm.api;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;

import java.util.Collection;
import java.util.Comparator;

public interface ITaskRepository {
    @Delete("DELETE FROM app_task WHERE user_id = #{userId} AND name = #{name}")
    void removeTaskByName(@Param("userId") @Nullable String userId, 
                          @Param("name") @Nullable String name);

    @Delete("DELETE FROM app_task WHERE user_id = #{userId} AND id = #{id}")
    void removeTask(@Param("userId") @Nullable String userId,
                    @Param("userId") @Nullable String id);

    @Select("SELECT * FROM app_task WHERE user_id = #{userId} AND project_id = #{projectId}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "finishDate", column = "dateEnd"),
            @Result(property = "projectId", column = "project_id")
    })
    Collection<Task> getProjectTasks(@Param("userId") @Nullable String userId,
                                     @Param("projectId") @Nullable String projectId);

    @Delete("DELETE FROM app_task WHERE user_id = #{userId} AND project_id = #{projectId}")
    void removeProjectTasks(@Param("userId") @Nullable String userId,
                            @Param("projectId") @Nullable String projectId);

    @Select("SELECT * FROM app_task WHERE user_id = #{userId} AND name = #{name} AND project_id = #{projectId}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "finishDate", column = "dateEnd"),
            @Result(property = "projectId", column = "project_id")
    })
    Task findProjectTaskByName(@Param("userId") @Nullable String userId,
                               @Param("name") @Nullable String name,
                               @Param("projectId") @Nullable String projectId);

    @Update("UPDATE app_task SET name = #{newName} " +
            "WHERE name = #{oldName} AND user_id = #{userId} AND project_id = #{projectId}")
    void renameTask(@Param("userId") @Nullable String userId,
                    @Param("projectId") @Nullable String projectId,
                    @Param("oldName") @Nullable String oldName,
                    @Param("newName") @Nullable String newName
    );

    @Select("SELECT * FROM app_task WHERE user_id = #{userId}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "finishDate", column = "dateEnd"),
            @Result(property = "projectId", column = "project_id")
    })
    Collection<Task> findAll(@Param("userId") @Nullable String userId);

    @Select("SELECT * FROM app_task WHERE user_id = #{userId} AND name LIKE #{name}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "finishDate", column = "dateEnd"),
            @Result(property = "projectId", column = "project_id")
    })
    Collection<Task> findAllByNamePart(@Param("userId") @Nullable String userId,
                                       @Param("name") @Nullable String name);

    @Select("SELECT * FROM app_task WHERE user_id = #{userId} AND description LIKE #{description}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "finishDate", column = "dateEnd"),
            @Result(property = "projectId", column = "project_id")
    })
    Collection<Task> findAllByDescPart(@Param("userId") @Nullable String userId,
                                       @Param("description") @Nullable String description);

    @Select("SELECT * FROM app_task WHERE user_id = #{userId} AND id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "finishDate", column = "dateEnd"),
            @Result(property = "projectId", column = "project_id")
    })
    Task findOne(@Param("userId") @NotNull String userId,
                 @Param("id") @NotNull String id);

    @Insert("INSERT INTO app_task (id, user_id, name, description, dateBegin, dateEnd, project_id) " +
            "VALUES (#{id}, #{userId}, #{name}, #{description}, #{startDate}, #{finishDate}, #{projectId})")
    void persist(@Nullable Task entity);

    @Update("UPDATE app_task SET user_id = #{userId}, name = #{name}, description = #{description}, " +
            "dateBegin = #{startDate}, dateEnd = #{finishDate}, project_id = #{projectId} WHERE id = #{id}")
    void merge(@Nullable Task entity);

    @Delete("DELETE FROM app_task WHERE user_id = #{userId}")
    void removeAll(@Param("userId") @Nullable String userId);
}
