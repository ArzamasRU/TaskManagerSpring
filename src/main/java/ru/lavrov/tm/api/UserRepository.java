package ru.lavrov.tm.api;

public interface UserRepository<T> extends Repository<T>{
    void updatePassword(String userId, String newPassword);
    void updateLogin(String userId, String newLogin);
}