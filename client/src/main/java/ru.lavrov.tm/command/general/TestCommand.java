package ru.lavrov.tm.command.general;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.endpoint.Calculate;
import ru.lavrov.tm.endpoint.TestEndpointService;
import ru.lavrov.tm.enumerate.Role;

import java.util.Collection;

@NoArgsConstructor
public final class TestCommand extends AbstractCommand {
    private static final boolean SAFE = true;
    @Nullable
    private static final Collection<Role> ROLES = null;
    @NotNull
    private static final String COMMAND = "test";
    @NotNull
    private static final String DESCRIPTION = "Test.";

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
        System.out.println("[Test]");
//        Calculate calculate = new Calculate();
        TestEndpointService testEndpointService = new TestEndpointService();
        System.out.println(testEndpointService.getTestEndpointPort().calculate(1, 2));
        System.out.println("[ok]");
        System.out.println();
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
