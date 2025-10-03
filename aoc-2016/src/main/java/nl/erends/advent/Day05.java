package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.MD5;
import nl.erends.advent.util.Util;

public class Day05 extends AbstractProblem<String, String> {

    void main() {
        new Day05().setAndSolve(Util.readLine(2016, 5));
    }

    @Override
    public String solve1() {
        StringBuilder password = new StringBuilder();
        int nonce = 0;
        while (password.length() < 8) {
            String hash = MD5.getHash(input + nonce);
            if (hash.startsWith("00000")) {
                password.append(hash.charAt(5));
            }
            nonce++;
        }
        return password.toString();
    }

    @Override
    public String solve2() {
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
                        // LOG.info(new String(output))
                    }
                }
            }
            nonce++;
        }
        return new String(output);
    }
}
