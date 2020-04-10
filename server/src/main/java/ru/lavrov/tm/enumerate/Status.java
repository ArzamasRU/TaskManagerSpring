package ru.lavrov.tm.enumerate;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    @Nullable
    public static Status getByStatus(@NotNull final String status) {
        if (status == null || status.isEmpty())
            return null;
        Status[] list = Status.values();
        for (@Nullable final Status currentStatus: list) {
            if (status.equals(currentStatus.getStatus()))
                return currentStatus;
        }
        try {
            return Status.valueOf(status);
        } catch(IllegalArgumentException e) {
            return null;
        }
    }
}
