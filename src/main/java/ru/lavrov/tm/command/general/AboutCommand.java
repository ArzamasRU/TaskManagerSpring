package ru.lavrov.tm.command.general;

import com.jcabi.manifests.Manifests;
import lombok.NoArgsConstructor;
import org.apache.log4j.BasicConfigurator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.role.Role;

import java.util.Collection;

@NoArgsConstructor
public class AboutCommand extends AbstractCommand {
    private static final boolean SAFE = true;
    @Nullable
    private static final Collection<Role> ROLES = null;
    @NotNull
    private static final String COMMAND = "about";
    @NotNull
    private static final String DESCRIPTION = "About the program.";

    @NotNull
    @Override
    public String getCommand() {
        return COMMAND;
    }

    @NotNull
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public void execute() {
        BasicConfigurator.configure();
        System.out.println("Manifest-Version: " + Manifests.read("Manifest-Version"));
        System.out.println("Built-By: " + Manifests.read("Built-By"));
        System.out.println("Created-By: " + Manifests.read("Created-By"));
        System.out.println("Build-Jdk: " + Manifests.read("Build-Jdk"));
        System.out.println("Project-Version: " + Manifests.read("Project-Version"));
        System.out.println("Project-Name: " + Manifests.read("Project-Name"));
        System.out.println("Implementation-Build: " + Manifests.read("Implementation-Build"));
    }

    @Override
    public boolean isSafe() {
        return SAFE;
    }

    @Nullable
    @Override
    public Collection<Role> getRoles() {
        return ROLES;
    }
}
