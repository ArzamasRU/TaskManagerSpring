package ru.lavrov.tm.role;

import org.jetbrains.annotations.NotNull;

public enum Role {
    User,
    Admin;

    @NotNull
    public String displayName(){
        return name();
    }
}
