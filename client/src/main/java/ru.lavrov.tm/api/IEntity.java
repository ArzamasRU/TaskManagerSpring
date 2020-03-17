package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

public interface IEntity extends Serializable {
    String getName();

    void setName(@Nullable String name);

    String getId();

    void setId(@Nullable String id);

    String getUserId();

    void setUserId(@Nullable String userId);
}
