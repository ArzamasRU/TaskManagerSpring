package ru.lavrov.tm.role;

public enum Role {
    User,
    Admin;

    public String displayName(){
        return name();
    }
}
