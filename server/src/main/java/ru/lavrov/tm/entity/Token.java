package ru.lavrov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.Session;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public final class Token {

    @NotNull
    protected String id = UUID.randomUUID().toString();
    @NotNull
    private Session session;
    @Nullable
    private String sign;

    public Token(@NotNull Session session) {
        this.session = session;
    }
}
