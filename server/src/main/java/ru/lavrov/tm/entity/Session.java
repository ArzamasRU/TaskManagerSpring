package ru.lavrov.tm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.enumerate.Role;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "session")
public final class Session {

    @NotNull
    private String id = UUID.randomUUID().toString();
    @Nullable
    private String userId;
    @Nullable
    private Role role;
    private long timeStamp;
    @Nullable
    private String sign;

    public Session(@Nullable String userId, @Nullable Role role, long timeStamp) {
        this.userId = userId;
        this.role = role;
        this.timeStamp = timeStamp;
    }
}
