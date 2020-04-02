package ru.lavrov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public final class Token {

    @NotNull
    protected String id = UUID.randomUUID().toString();

    @Nullable
    private Session session;

    @Nullable
    private String signature;
}
