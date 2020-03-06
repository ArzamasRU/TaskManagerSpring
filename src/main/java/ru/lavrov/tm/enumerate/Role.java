package ru.lavrov.tm.enumerate;

import org.jetbrains.annotations.NotNull;

public enum Role {
    ADMIN("admin"),
    USER("user");

    @NotNull private final String role;

    Role(String role) {
        this.role = role;
    }

    @NotNull
    public String getRole() {
        return role;
    }

    @NotNull
    public String displayName(){
        return name();
    }
}
