package ru.lavrov.tm.endpoint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.lavrov.tm.api.IServiceLocator;
import ru.lavrov.tm.bootstrap.Bootstrap;

@Getter
@Setter
@NoArgsConstructor
public class AbstractEndpoint {
    @NotNull
    protected IServiceLocator bootstrap;
}
