package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;

public interface IEntity {
    @Nullable String getName();
    void setName(@Nullable String name);
    @Nullable String getId();
    void setId(@Nullable String id);
    @Nullable String getUserId();
    void setUserId(@Nullable String userId);
    @Nullable String toString();
}
