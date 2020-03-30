package ru.lavrov.tm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.enumerate.Role;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "session")
public final class Session {

    @NotNull public static final String ID = "id";
    @NotNull public static final String SIGNATURE = "signature";
    @NotNull public static final String USER_ID = "user_Id";
    @NotNull public static final String TIMESTAMP = "timestamp";

    @Nullable
    private String userId;
    @Nullable
    private Role role;
    private long timeStamp;
    @JsonIgnore
    @Nullable
    private String sign;

    public Session(@Nullable String userId, @Nullable Role role, long timeStamp) {
        this.userId = userId;
        this.role = role;
        this.timeStamp = timeStamp;
    }
}
