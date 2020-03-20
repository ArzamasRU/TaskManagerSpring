package ru.lavrov.tm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.enumerate.Role;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "session")
public final class Session {
    @Nullable
    private String userId;
    @Nullable
    private Role role;
    private long timeLimit;
    @Nullable
    private String sign;
}
