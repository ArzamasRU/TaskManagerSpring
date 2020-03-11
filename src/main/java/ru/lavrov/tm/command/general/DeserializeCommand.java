package ru.lavrov.tm.command.general;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IProjectService;
import ru.lavrov.tm.api.ITaskService;
import ru.lavrov.tm.api.IUserService;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collection;

//public class DeserializeCommand extends AbstractCommand {
//    private static final boolean SAFE = true;
//    @Nullable
//    private static final Collection<Role> ROLES = Arrays.asList(Role.ADMIN, Role.USER);
//    @NotNull
//    private static final String COMMAND = "serialize";
//    @NotNull
//    private static final String DESCRIPTION = "Serialize data.";
//
//    @NotNull
//    @Override
//    public String getCommand() {
//        return COMMAND;
//    }
//
//    @NotNull
//    @Override
//    public String getDescription() {
//        return DESCRIPTION;
//    }
//
//    @Override
//    public void execute() throws Exception {
//        System.out.println("--Deserializable data--");
//        try (
//                @NotNull final FileInputStream userInput = new FileInputStream("src/main/file/users");
//                @NotNull final FileInputStream projectInput = new FileInputStream("src/main/file/projects");
//                @NotNull final FileInputStream taskInput = new FileInputStream("src/main/file/tasks")
//        ) {
//            @NotNull final ObjectInputStream userInputStream = new ObjectInputStream(userInput);
//            @Nullable final List<User> users = (List<User>) userInputStream.readObject();
//            for (User user : users) serviceLocator.getUserService().merge(user);
//            @NotNull final ObjectInputStream projectInputStream = new ObjectInputStream(projectInput);
//            @Nullable final List<Project> projects = (List<Project>) projectInputStream.readObject();
//            for (Project project : projects) serviceLocator.getProjectService().merge(project);
//            @NotNull final ObjectInputStream taskInputStream = new ObjectInputStream(taskInput);
//            @Nullable final List<Task> tasks = (List<Task>) taskInputStream.readObject();
//            for (Task task : tasks) serviceLocator.getTaskService().merge(task);
//            System.out.println("[DESERIALIZABLE COMPLETED]");
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//
//    @Override
//    public boolean isSafe() {
//        return SAFE;
//    }
//
//    @Nullable
//    @Override
//    public Collection<Role> getRoles() {
//        return ROLES;
//    }
//}
