package ru.lavrov.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashUtil {
    @Nullable
    public static String md5Simple(@NotNull String string) {
        @Nullable MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(string.getBytes());
        @Nullable final byte[] digest = md.digest();
        @Nullable final String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;
    }

    @Nullable
    public static String md5Hard(@Nullable final String md5) {
        if (md5 == null)
            return null;
        try {
            @NotNull final java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            @NotNull final byte[] array = md.digest(md5.getBytes());
            @NotNull final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i)
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
