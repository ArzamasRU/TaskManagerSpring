package ru.lavrov.tm.api;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Test;

public interface ITestRepository {
    @Insert("INSERT INTO app_test (id, str) VALUES (#{id}, #{str})")
    void persist(@Nullable final Test test);

    @Insert("INSERT INTO app_test (id, str) VALUES (#{id}, #{str})")
    void persist2(@Param("id") @Nullable String id, @Param("str") @Nullable String  str);
}
