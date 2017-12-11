package nl.erends.advent.year2016;


import nl.erends.advent.util.MD5;

import java.util.HashMap;
import java.util.Map;

public class Day14 {

    private static Map<String, String> hashes = new HashMap<>();
    private static int hashLoops = 1;

    public static void main(String[] args) {
        String salt = "jlmsuwbz";

        int nonce = -1;
        int keysFound = 0;
        while (keysFound < 64) {
            nonce++;
            System.out.println(nonce);
            char tripleRepeat = getTripleChar(salt + nonce);
            if (tripleRepeat != '\0') {
                String fiveRepeat = new String(new char[]{tripleRepeat,tripleRepeat,tripleRepeat,tripleRepeat,tripleRepeat});
                for (int index = 1; index <= 1000; index++) {
                    if (getHash(salt + (nonce + index)).contains(fiveRepeat)) {
                        keysFound++;
                        break;
                    }
                }
            }
        }
        System.out.println(nonce);

        nonce = -1;
        keysFound = 0;
        hashes = new HashMap<>();
        hashLoops = 2017;
        while (keysFound < 64) {
            nonce++;
            System.out.println(nonce);
            char tripleRepeat = getTripleChar(salt + nonce);
            if (tripleRepeat != '\0') {
                String fiveRepeat = new String(new char[]{tripleRepeat,tripleRepeat,tripleRepeat,tripleRepeat,tripleRepeat});
                for (int index = 1; index <= 1000; index++) {
                    if (getHash(salt + (nonce + index)).contains(fiveRepeat)) {
                        keysFound++;
                        break;
                    }
                }
            }
        }
        System.out.println(nonce);

    }

    private static char getTripleChar(String input) {
        char[] hash = getHash(input).toCharArray();
        for (int index = 0; index < hash.length - 2; index++) {
            if (hash[index] == hash[index + 1] && hash[index] == hash[index + 2]) {
                return hash[index];
            }
        }
        return '\0';
    }

    private static String getHash(String input) {
        if (hashes.containsKey(input)) {
            return hashes.get(input);
        }
        String original = input;
        for (int i = 0; i < hashLoops; i++) {
            input = MD5.getHash(input);
        }
        hashes.put(original, input);
        return input;
    }
}
