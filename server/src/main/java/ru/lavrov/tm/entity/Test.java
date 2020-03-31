package ru.lavrov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "test")
public class Test {
    @NotNull
    private String id = UUID.randomUUID().toString();
    @Nullable
    private String str;

    public Test(@Nullable String str) {
        this.str = str;
    }
}
