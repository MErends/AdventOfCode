package nl.erends.advent.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    
    private MD5() {
    }

    public static String getHash(String input) {
        final MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5"); // NOSONAR
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
        byte[] digest = messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append("%02x".formatted(b & 0xff));
        }
        return sb.toString();
    }
}
