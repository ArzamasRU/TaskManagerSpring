package ru.lavrov.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashUtil {
    @Nullable
    public static String getHash(@NotNull String string) throws NoSuchAlgorithmException {
        @Nullable final MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(string.getBytes());
        @Nullable final byte[] digest = md.digest();
        @Nullable final String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;
    }
}
