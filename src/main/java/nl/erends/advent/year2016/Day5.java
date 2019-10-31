package nl.erends.advent.year2016;

import nl.erends.advent.util.MD5;

public class Day5 {

    public static void main(String[] args) {
        String input = "cxdnnyjw";
        Day5.solve1(input);
        Day5.solve2(input);

    }

    public static String solve1(String input) {
        StringBuilder password = new StringBuilder();
        int nonce = 0;
        while (password.length() < 8) {
            String hash = MD5.getHash(input + nonce);
            if (hash.startsWith("00000")) {
                password.append(hash.charAt(5));
                System.out.println(password);
            }
            nonce++;
        }
        return password.toString();
    }

    public static String solve2(String input) {
        char[] output = "________".toCharArray();
        int charsFound = 0;
        int nonce = 0;
        while (charsFound < 8) {
            String hash = MD5.getHash(input + nonce);
            if (hash.startsWith("00000")) {
                char positionChar = hash.charAt(5);
                if (positionChar >= '0' && positionChar <= '7') {
                    int position = Integer.parseInt("" + positionChar);
                    if (output[position] == '_') {
                        output[position] = hash.charAt(6);
                        charsFound++;
                        System.out.println(new String(output));
                    }
                }
            }
            nonce++;
        }
        return new String(output);
    }
}
