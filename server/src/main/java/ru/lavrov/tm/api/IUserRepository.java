package ru.lavrov.tm.api;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.User;

import java.util.Collection;

public interface IUserRepository {
//    @Update("UPDATE app_user SET passwordHash = #{newPassword} WHERE id = #{userId}")
//    void updatePassword(@Param("userId") @Nullable String userId,
//                        @Param("newPassword") @Nullable String newPassword);
//
//    @Update("UPDATE app_user SET login = #{newLogin} WHERE id = #{userId}")
//    void updateLogin(@Param("userId") @Nullable String userId,
//                     @Param("newLogin") @Nullable String newLogin);
//
//    @Select("SELECT * FROM app_user WHERE login = #{login}")
//    @Results(value = {
//            @Result(property = "id", column = "id"),
//            @Result(property = "login", column = "login"),
//            @Result(property = "password", column = "passwordHash"),
//            @Result(property = "role", column = "role")
//    })
    User findUserByLogin(@Param("login") @Nullable String login);
//
//    @Select("SELECT * FROM app_user WHERE id = #{userId}")
//    @Results(value = {
//            @Result(property = "id", column = "id"),
//            @Result(property = "login", column = "login"),
//            @Result(property = "password", column = "passwordHash"),
//            @Result(property = "role", column = "role")
//    })
//    User findOne(@Param("userId") @Nullable String userId);
//
//    @Delete("DELETE FROM app_user WHERE id = #{userId}")
//    void removeUser(@Param("userId") @Nullable final String userId);
//
//    @Insert("INSERT INTO app_user (id, login, passwordHash, role) VALUES (#{id}, #{login}, #{password}, #{role})")
    void persist(@Nullable User user);
//
//    @Update("UPDATE app_user SET login = #{login}, passwordHash = #{password}, role = #{role} WHERE id = #{id}")
//    void merge(@Nullable User entity);
//
//    @Select("SELECT * FROM app_user WHERE id = #{userId}")
//    @Results(value = {
//            @Result(property = "id", column = "id"),
//            @Result(property = "login", column = "login"),
//            @Result(property = "password", column = "passwordHash"),
//            @Result(property = "role", column = "role")
//    })
//    Collection<User> findAll(@Param("userId") @Nullable String userId);
}