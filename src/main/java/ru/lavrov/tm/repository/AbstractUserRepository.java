//package ru.lavrov.tm.repository;
//
//import ru.lavrov.tm.api.IUserRepository;
//import ru.lavrov.tm.entity.User;
//import ru.lavrov.tm.exception.user.UserExistsException;
//import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
//import ru.lavrov.tm.exception.user.UserLoginIsInvalidException;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//public abstract class AbstractUserRepository implements IUserRepository<User> {
//    protected final Map<String, User> users = new HashMap();
//
//    @Override
//    public void persist(final User user) {
//        if (user == null)
//            throw new UserIsNotAuthorizedException();
//        final String id = user.getId();
//        if (users.containsKey(id))
//            throw new UserExistsException();
//        users.put(id, user);
//    }
//
//    @Override
//    public void merge(final User user){
//        if (user == null)
//            throw new UserIsNotAuthorizedException();
//        users.put(user.getLogin(), user);
//    }
//
//    @Override
//    public void remove(final String entityId, final String userId) {
//        if (entityId == null || entityId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        users.remove(entityId);
//    }
//
//    @Override
//    public void removeAll(final String userId){
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        users.remove(userId);
//    }
//
//    @Override
//    public Collection<User> findAll(final String userId) {
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        final Collection<User> list = new ArrayList<>();
//        list.add(users.get(userId));
//        return list;
//    }
//
//    @Override
//    public User findEntityByName(final String login, final String userId){
//        if (login == null || login.isEmpty())
//            throw new UserLoginIsInvalidException();
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        User currentUser = null;
//        for (final User user: users.values()) {
//            if (user == null)
//                continue;
//            if (login.equals(user.getLogin())) {
//                currentUser = user;
//                break;
//            }
//        }
//        return currentUser;
//    }
//
////    public void remove(String entityId) {
////        if (entityId == null)
////            throw new UserIsNotAuthorizedException();
////        users.remove(entityId);
////    }
//}
