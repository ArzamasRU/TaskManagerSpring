package ru.lavrov.tm.api;

public interface ProjectRepository<T> extends Repository<T>{
    void renameProject(String oldName, String newName, String userId);
}
