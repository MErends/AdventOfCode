package nl.erends.advent.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    
    private static MessageDigest md5er;
    static {
        try {
            md5er = MessageDigest.getInstance("MD5"); // NOSONAR
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
    
    private MD5() {
    }

    public static String getHash(String input) {
        StringBuilder sb = new StringBuilder();
        byte[] digest = md5er.digest(input.getBytes(StandardCharsets.UTF_8));
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}
