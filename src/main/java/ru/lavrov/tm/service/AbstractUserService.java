package ru.lavrov.tm.service;

import ru.lavrov.tm.api.UserRepository;
import ru.lavrov.tm.api.UserService;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.exception.user.UserNotExistsException;

import java.util.Collection;

public abstract class AbstractUserService<User> implements UserService<User> {
    protected final UserRepository userRepository;

    protected AbstractUserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void persist(final User user) {
        if (user == null)
            throw new UserNotExistsException();
        userRepository.persist(user);
    }

    @Override
    public void merge(final User user) {
        if (user == null)
            throw new UserNotExistsException();
        userRepository.merge(user);
    }

    @Override
    public void remove(final String entityId, final String userId) {
        if (entityId == null)
            throw new UserIsNotAuthorizedException();
        userRepository.remove(entityId, userId);
    }

    @Override
    public void removeAll(final String userId) {
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        userRepository.removeAll(userId);
    }

    @Override
    public Collection<User> findAllByUser(final String userId) {
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        return userRepository.findAllByUser(userId);
    }
}
