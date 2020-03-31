package ru.lavrov.tm.api;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Test;

public interface ITestRepository {
    @Insert("INSERT INTO app_test (id, str) VALUES (#{id}, #{string})")
    void persist(@Nullable Test test);

//    @Insert("INSERT INTO app_test (id, str) VALUES (#{id}, #{str})")
//    @Results(value = {
//            @Result(property = "id", column = "id"),
//            @Result(property = "password", column = "password_hash")
//    })
//    void persist2(@Param("id") @Nullable String id, @Param("str") @Nullable String  str);
}
