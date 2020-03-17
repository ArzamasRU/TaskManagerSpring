package ru.lavrov.tm.comparator;

import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.exception.entity.EntityCanNotBeComparedException;
import ru.lavrov.tm.api.IComparableEntity;

import java.util.Comparator;

public final class FinishDateComparator implements Comparator<IComparableEntity> {
    @Override
    public int compare(@Nullable final IComparableEntity o1, @Nullable final IComparableEntity o2) {
        if (o1 == null || o2 == null)
            throw new EntityCanNotBeComparedException();
        return o1.getFinishDate().compareTo(o2.getFinishDate());
    }
}