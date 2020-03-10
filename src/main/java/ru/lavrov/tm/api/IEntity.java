package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.enumerate.Status;

import java.util.Date;

public interface IEntity {
    String getName();
    void setName(@Nullable String name);
    String getId();
    void setId(@Nullable String id);
    String getUserId();
    void setUserId(@Nullable String userId);
}
