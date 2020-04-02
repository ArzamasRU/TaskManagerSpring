package ru.lavrov.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.exception.util.AESUtilException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class AESUtil {

    private static SecretKeySpec secretKey;
    private static byte[] key;

    public static void setKey(String myKey) {
        @Nullable MessageDigest sha;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
        } catch (Exception e) {
            throw new AESUtilException();
        }
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        secretKey = new SecretKeySpec(key, "AES");
    }

    public static String encrypt(String strToEncrypt, String secret) {
        setKey(secret);
        try {
            @NotNull final Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            final byte[] data = cipher.doFinal(strToEncrypt.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(data);
        } catch (Exception e) {
            throw new AESUtilException();
        }
    }

    public static String decrypt(String strToDecrypt, String secret) {
        setKey(secret);
        try {
            @NotNull final Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            final byte[] data = Base64.getDecoder().decode(strToDecrypt);
            return new String(cipher.doFinal(data));
        } catch (Exception e) {
            throw new AESUtilException();
        }
    }
}

