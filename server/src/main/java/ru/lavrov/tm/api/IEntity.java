package ru.lavrov.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

public interface IEntity extends Serializable {
    String getId();
    void setId(@NotNull String id);
}
