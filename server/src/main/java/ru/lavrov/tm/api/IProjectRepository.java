package ru.lavrov.tm.api;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Project;

import java.util.Collection;
import java.util.Comparator;

public interface IProjectRepository {
    @Update("UPDATE app_project SET  name = #{name} WHERE id = #{id} AND user_id = #{user_id}")
    void renameProject(@Nullable String userId, @Nullable String oldName, @Nullable String newName);

    @Select("SELECT * FROM app_project WHERE user_id = #{user_id} AND name = #{name}")
    Project findEntityByName(@Nullable String userId, @Nullable String entityName);

    @Select("SELECT * FROM app_project WHERE user_id = #{user_id}")
    Collection<Project> findAll(@Nullable String userId, @Nullable Comparator<Project> comparator);

    @Select("SELECT * FROM app_project WHERE user_id = #{user_id} AND name LIKE #{name}")
    Collection<Project> findAllByNamePart(@Nullable String userId, @Nullable String name);

    @Select("SELECT * FROM app_project WHERE user_id = #{user_id} AND description LIKE #{description}")
    Collection<Project> findAllByDescPart(@Nullable String userId, @Nullable String description);

    @Select("SELECT * FROM app_project WHERE user_id = #{user_id} AND id = #{id}")
    Project findOne(@NotNull String userId, @NotNull String projectId);

    @Delete("DELETE FROM app_project WHERE user_id = #{user_id} AND id = #{id}")
    void removeProject(@Nullable final String userId, @Nullable final String entityId);

    @Insert("INSERT INTO app_project (id, user_id, name, description, dateBegin, dateEnd) " +
            "VALUES (#{id}, #{userId}, #{name}, #{description}, #{startDate}, #{finishDate})")
    void persist(@Nullable Project project);

    @Update("UPDATE app_project SET user_id = #{user_id}, name = #{name}, " +
            "description = #{description}, dateBegin = #{dateBegin}, dateEnd = #{dateEnd}, WHERE id = #{id}")
    void merge(@Nullable Project entity);

    @Delete("DELETE FROM app_project WHERE user_id = #{user_id} AND id = #{id}")
    void removeAll(@Nullable String userId);

    @Select("SELECT * FROM app_project WHERE user_id = #{user_id}")
    Collection<Project> findAll(@Nullable String userId);
}
