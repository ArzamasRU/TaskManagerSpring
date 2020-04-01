package ru.lavrov.tm.api;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Project;

import java.util.Collection;
import java.util.Comparator;

public interface IProjectRepository {
    @Update("UPDATE app_project SET name = #{newName} WHERE name = #{oldName} AND user_id = #{userId}")
    void renameProject(@Param("userId") @Nullable String userId,
                       @Param("oldName") @Nullable String oldName,
                       @Param("newName") @Nullable String newName);

    @Select("SELECT * FROM app_project WHERE user_id = #{userId} AND name = #{name}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "finishDate", column = "dateEnd")
    })
    Project findEntityByName(@Param("userId") @Nullable String userId,
                             @Param("name") @Nullable String name);

    @Select("SELECT * FROM app_project WHERE user_id = #{userId}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "finishDate", column = "dateEnd")
    })
    Collection<Project> findAll(@Param("userId") @Nullable String userId);

    @Select("SELECT * FROM app_project WHERE user_id = #{userId} AND name LIKE #{name}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "finishDate", column = "dateEnd")
    })
    Collection<Project> findAllByNamePart(@Param("userId") @Nullable String userId,
                                          @Param("name")  @Nullable String name);

    @Select("SELECT * FROM app_project WHERE user_id = #{userId} AND description LIKE #{description}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "finishDate", column = "dateEnd")
    })
    Collection<Project> findAllByDescPart(@Param("userId") @Nullable String userId,
                                          @Param("description") @Nullable String description);

    @Select("SELECT * FROM app_project WHERE user_id = #{userId} AND id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "finishDate", column = "dateEnd")
    })
    Project findOne(@Param("userId") @NotNull String userId,
                    @Param("id") @NotNull String id);

    @Delete("DELETE FROM app_project WHERE user_id = #{userId} AND id = #{id}")
    void removeProject(@Param("userId") @Nullable final String userId,
                       @Param("id") @Nullable final String id);

    @Insert("INSERT INTO app_project (id, user_id, name, description, dateBegin, dateEnd) " +
            "VALUES (#{id}, #{userId}, #{name}, #{description}, #{startDate}, #{finishDate})")
    void persist(@Nullable Project project);

    @Update("UPDATE app_project SET user_id = #{userId}, name = #{name}, " +
            "description = #{description}, dateBegin = #{startDate}, dateEnd = #{finishDate}, WHERE id = #{id}")
    void merge(@Nullable Project entity);

    @Delete("DELETE FROM app_project WHERE user_id = #{userId}")
    void removeAll(@Param("userId") @Nullable String userId);
}
