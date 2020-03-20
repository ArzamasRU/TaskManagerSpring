package ru.lavrov.tm.enumerate;

import org.jetbrains.annotations.NotNull;

public enum Status {
    PLANNED("planned"),
    IN_PROCESS("in_process"),
    DONE("done");

    @NotNull
    private final String status;

    Status(final String status) {
        this.status = status;
    }

    @NotNull
    public String getStatus() {
        return status;
    }

    @NotNull
    public String displayName() {
        return name();
    }
}
