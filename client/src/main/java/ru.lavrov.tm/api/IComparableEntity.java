package ru.lavrov.tm.api;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.enumerate.Status;

import java.util.Date;

public interface IComparableEntity {
    Status getStatus();

    void setStatus(@Nullable Status status);

    Date getStartDate();

    void setStartDate(@Nullable Date date);

    Date getFinishDate();

    void setFinishDate(@Nullable Date date);
}
